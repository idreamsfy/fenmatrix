package org.quant.fenmatrix.asset;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.quant.fenmatrix.cashflow.CashFlow;
import org.quant.fenmatrix.cashflow.Event;
import org.quant.fenmatrix.convention.Compounding;
import org.quant.fenmatrix.convention.Currency;
import org.quant.fenmatrix.curve.Curve;
import org.quant.fenmatrix.curve.YieldCurve;
import org.quant.fenmatrix.time.BDRule;
import org.quant.fenmatrix.time.Date;
import org.quant.fenmatrix.time.Freq;
import org.quant.fenmatrix.time.TimeUtil;
import org.quant.fenmatrix.tools.FM;
import org.quant.fenmatrix.tools.FMException;
import org.quant.fenmatrix.tools.Status;

public class Asset implements Cloneable {

  private String Name;

  private String ID;

  private Date StartDate;

  private Date EndDate;

  private ArrayList<CashFlow> cashflows;

  private ArrayList<Event> events;

  private InterestRate Int;

  private Curve mktCurve;

  private double YTM = 0.0;

  private YieldCurve YC;

  private double FaceAmount;

  private BDRule DayRule;

  private double cleanprice;

  private double dirtyprice;

  private Date preCoupDate;

  private double IssuePrice;
  
  private Currency Ccy;

  public enum PorS {
    Purchase,
    Sale;
  }

  public PorS pors;

  protected Asset() {
  }

  public static Asset valueOf(String ID) {
    Asset asset = new Asset();
    asset.cashflows = new ArrayList<CashFlow>();
    return asset;
  }

  private void AssetYeild() {
    FM.require(cleanprice > 0, "Clean price must be setted!");

  }

  private void CalcCashflow() throws FMException {

    //for fixed income asset based class
    if (this.Int.getAssettype() == InterestRate.FixOrFloat.Fixed) {

      this.Int.getIntRate();

      FM.require(cashflows.size() > 0, "Coupon based asset class mush have cash flow sequence!");

      int term = cashflows.size() - 1;

      for (int i = 1; i < term; i++) {
        cashflows.get(i).Amount = this.FaceAmount * this.Int.getIntRate() / this.Int.getFreq().toInteger();
      }

      cashflows.get(term).Amount = this.FaceAmount * this.Int.getIntRate() / this.Int.getFreq().toInteger() + this.FaceAmount;

      if (YTM == 0)
        AssetYeild();

      for (int i = 1; i <= term; i++) {
        CashFlow flowlet = cashflows.get(i);
        flowlet.discRate = 1 / Math.pow(1 + YTM / this.Int.getFreq().toInteger(), i - 1);
        flowlet.NPV = flowlet.Amount * flowlet.discRate;
      }

      for (int i = 1; i <= term; i++) {
        cashflows.get(0).Amount += cashflows.get(i).Amount;
        cashflows.get(0).NPV += cashflows.get(i).NPV;
      }
    } else {
      //TODO: Floating rates
    }

  }

  private void GenerateAssetSequence() throws FMException {

    FM.require(EndDate.compareTo(StartDate) > 0, "Maturity date must greater than start!");

    Date cashflowdate;
    if (!TimeUtil.isBusDay(StartDate))
      cashflowdate = TimeUtil.NextBusDay(StartDate, this.Int.getCalendar(), this.DayRule);
    else
      cashflowdate = StartDate;

    Date end;
    if (!TimeUtil.isBusDay(EndDate))
      end = TimeUtil.NextBusDay(EndDate, this.Int.getCalendar(), this.DayRule);
    else
      end = EndDate;

    cashflows = new ArrayList<CashFlow>();

    while (cashflowdate.compareTo(end) < 0) {
      Date temp = new Date(cashflowdate);
      switch (this.Int.getFreq()) {
        case Annual:
          cashflowdate = TimeUtil.addYear(cashflowdate, 1, this.Int.getCalendar(), this.DayRule);
          break;
        case Monthly:
          cashflowdate = TimeUtil.addMonth(cashflowdate, 1, this.Int.getCalendar(), this.DayRule);
          break;
        case Semiannual:
          cashflowdate = TimeUtil.addMonth(cashflowdate, 6, this.Int.getCalendar(), this.DayRule);
          break;
        case Quarterly:
          cashflowdate = TimeUtil.addMonth(cashflowdate, 3, this.Int.getCalendar(), this.DayRule);
          break;
        case EveryFourthMonth:
          cashflowdate = TimeUtil.addMonth(cashflowdate, 4, this.Int.getCalendar(), this.DayRule);
          break;
        case Weekly:
          cashflowdate = TimeUtil.addWeek(cashflowdate, 1, this.Int.getCalendar(), this.DayRule);
          break;
        case Daily:
          cashflowdate = TimeUtil.addDays(cashflowdate, 1, this.Int.getCalendar(), this.DayRule);
          break;
        case Bimonthly:
          cashflowdate = TimeUtil.addMonth(cashflowdate, 2, this.Int.getCalendar(), this.DayRule);
        default:
          FM.warn("Unsupport frequency type!");
          throw new FMException("Unsupport frequency type"); // TODO: message

      }
      cashflows.add(CashFlow.valueOf(temp, cashflowdate));
    }

    cashflows.add(CashFlow.valueOf(cashflows.get(cashflows.size() - 1).getEndDate(), end));
    CalcCashflow();
  }

  private void GenerateAssetSequence(Date date) throws FMException {

    GenerateAssetSequence();
    Iterator<CashFlow> itr = cashflows.iterator();

    while (itr.hasNext()) {
      CashFlow flowlet = itr.next();
      if (flowlet.getStartDate().compareTo(date) < 0) {
        this.preCoupDate = flowlet.getStartDate();
        FM.debug(String.format("Precoupondate[%s]", this.preCoupDate.toString()));
        itr.remove();
      }
    }
    CalcCashflow();
  }

  @Override
  public Asset clone() {
    return null;

  }

  public ArrayList<CashFlow> ProjectCashFlow() {

    if (this.Int.getFreq() == Freq.None) {
      //TODO zero discounting 

      return cashflows;
    }

    if (this.Int.getFreq() == Freq.Zero) {

      return cashflows;
    }

    if (this.Int.getFreq() == Freq.OtherFrequency) {

      return cashflows;
    }

    if (!cashflows.isEmpty())
      return cashflows;
    try {
      GenerateAssetSequence();
    } catch (FMException e) {
      FM.error(String.format("ERROR: failed populate the asset's cashflows[%s]", e.getMessage()));
    }

    return cashflows;
  }

  //this only for floating rate based asset
  public ArrayList<CashFlow> CashFlow(Date AsOfDate) {

    if (!cashflows.isEmpty())
      return cashflows;
    try {
      GenerateAssetSequence(AsOfDate);
    } catch (FMException e) {
      FM.error(String.format("ERROR: failed populate the asset's cashflows[%s]", e.getMessage()));
    }

    return cashflows;
  }

  public void setLeg(ArrayList<CashFlow> leg) {
    cashflows = leg;
  };

  public Status ReCompileCashFlow() {
    if (!cashflows.isEmpty())
      cashflows.clear();
    return Status.SUCCESS;
  }

  public List<Event> GenEvents() {

    return events;
  }

  public InterestRate getInt() {
    return Int;
  }

  public Asset IntRate(InterestRate i) {
    Int = i;
    return this;
  }

  public Date getEndDate() {
    return EndDate;
  }

  public Asset EndDate(Date endDate) {
    this.EndDate = endDate;
    return this;
  }

  public Date getStartDate() {
    return StartDate;
  }

  public Asset StartDate(Date startDate) {
    this.StartDate = startDate;
    return this;
  }

  public Curve getMktCurve() {
    return mktCurve;
  }

  public Asset MktCurve(Curve mktCurve) {
    this.mktCurve = mktCurve;
    return this;
  }

  public YieldCurve getYC() {
    return YC;
  }

  public void setYC(YieldCurve yC) {
    YC = yC;
  }

  public double getNotional() {
    return FaceAmount;
  }

  public Asset Notional(double notional) {
    this.FaceAmount = notional;
    return this;
  }

  public BDRule getDayRule() {
    return DayRule;
  }

  public Asset DayRule(BDRule dayRule) {
    this.DayRule = dayRule;
    return this;
  }

  public double getYTM() {
    return YTM;
  }

  public Asset YTM(double yTM) {
    this.YTM = yTM;
    return this;
  }

  public double getDirtyprice() {
    return dirtyprice;
  }

  public Asset Dirtyprice(double dirtyprice) {
    this.dirtyprice = dirtyprice;
    return this;
  }

  public double getCleanprice() {
    return cleanprice;
  }

  public Asset Cleanprice(double cleanprice) {
    this.cleanprice = cleanprice;
    return this;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public String getID() {
    return ID;
  }

  public void setID(String iD) {
    ID = iD;
  }

  public Date getPreCoupDate(Date AsOfDate) {
    GenerateAssetSequence(AsOfDate);
    return preCoupDate;
  }

  public Asset PreCoupDate(Date preCoupDate) {
    this.preCoupDate = preCoupDate;
    return this;
  }

  public double getIssuePrice() {
    return IssuePrice;
  }

  public Asset IssuePrice(double issuePrice) {
    IssuePrice = issuePrice;
    return this;
  }

  public static void main(String[] args) {

    Asset asset = Asset.valueOf("TEST");

    InterestRate interest = InterestRate.valueOf(0.032800, InterestRate.FixOrFloat.Fixed, Compounding.Simple, Freq.valueOf(4), null, null);

    asset.StartDate(Date.valueOf("20170905")).EndDate(Date.valueOf("20220905")).IntRate(interest).YTM(0.0328).Notional(100);

    ArrayList<CashFlow> cashflows = asset.CashFlow(Date.valueOf("20190905"));

    for (CashFlow flowlet : cashflows) {

      FM.info(flowlet.toString());

    }

  }

  public Currency getCcy() {
    return Ccy;
  }

  public void setCcy(Currency ccy) {
    Ccy = ccy;
  }
}
