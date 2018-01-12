
package org.quant.fenmatrix.instruments;

import java.util.ArrayList;

import org.quant.fenmatrix.asset.Asset;
import org.quant.fenmatrix.asset.IntRate;
import org.quant.fenmatrix.cashflow.CashFlow;
import org.quant.fenmatrix.convention.Compounding;
import org.quant.fenmatrix.time.Calendar;
import org.quant.fenmatrix.time.Date;
import org.quant.fenmatrix.time.DayCount;
import org.quant.fenmatrix.time.Freq;
import org.quant.fenmatrix.tools.FM;
import org.quant.fenmatrix.tools.Status;

public class Bond implements Instrument {

  protected int settlementDays_;
  protected Calendar calendar_;
  protected double Notional;
  protected ArrayList<CashFlow> redemptions_; // the redemption
  protected ArrayList<CashFlow> cashflows_; // the redemption
  protected Date maturityDate_;
  protected Date issueDate_;
  protected double settlementValue_;
  protected Date SettlementDate;
  protected double price;
  protected double yeild;
  protected double accrual;
  protected Date asofdate;
  protected double accrualperiod;
  protected Asset asset;
  protected double Duration;
  protected double ModifyDurition;
  protected double Convxity;

  protected Bond() {
  }

  public static Bond valueOf(Asset sec) {
    Bond bond = new Bond();
    bond.asset = sec;
    return bond;
  }

  public Bond Sec(Asset asset) {
    this.asset = asset;
    return this;
  }

  public Bond setPrice(double price) {
    this.price = price;
    return this;
  }

  @Override
  public double NPV() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public boolean isExpired() {
    // TODO Auto-generated method stub
    return false;
  }

  public Status Price() {

    if (isExpired()) {
      FM.info(String.format("Bond[%s] is expired!", asset.getID()));
    }

    Date preCoupDate = asset.getPreCoupDate(SettlementDate);

    Date NextCoupDate = asset.CashFlow(SettlementDate).get(0).getStartDate();

    DayCount daycount = DayCount.valueOf(asset.getInt().getBasis());

    accrualperiod = daycount.DaysBetween(preCoupDate, SettlementDate) /
        daycount.DaysBetween(preCoupDate, NextCoupDate);

    FM.require(this.Notional > 0, "Notional mush be set!");

    this.accrual = daycount.YearFactor(daycount.DaysBetween(preCoupDate, SettlementDate) * asset.getInt().getIntRate()) *
        this.Notional;

    cashflows_ = asset.CashFlow(SettlementDate);

    for (int i = 1; i < cashflows_.size(); i++) {
      this.Duration +=
          (cashflows_.get(i).NPV / cashflows_.get(0).NPV) * (accrualperiod + i - 1) / asset.getInt().getFreq().toInteger();
    }

    this.ModifyDurition = this.Duration / (this.yeild / this.asset.getInt().getFreq().toInteger() + 1);

    return Status.SUCCESS;
  }

  public Status Yield() {

    return Status.SUCCESS;
  }

  public Bond setNotional(double notional) {
    this.Notional = notional;
    return this;
  }

  public Bond SettlementDate(Date settle) {
    this.SettlementDate = settle;
    return this;
  }

  public Bond setYield(double yield) {
    this.yeild = yield;
    return this;
  }

  @Override
  public void Valuation() {
  }

  public static void main(String[] args) {
    Asset asset = Asset.valueOf("TEST");

    IntRate interest = IntRate.valueOf(0.032800, IntRate.FixOrFloat.Fixed, Compounding.Simple, Freq.valueOf(4), null, null);

    asset.StartDate(Date.valueOf("20170905")).EndDate(Date.valueOf("20220905")).IntRate(interest).YTM(0.0328).Notional(100);

    Bond fixedbond = Bond.valueOf(asset);

    fixedbond.setNotional(10000000).SettlementDate(Date.valueOf("20191204")).setYield(0.0328);

    fixedbond.Price();

    FM.info(String.format("Duration[%.4f]ModifyDuration[%.4f]", fixedbond.Duration, fixedbond.ModifyDurition));
  }
}
