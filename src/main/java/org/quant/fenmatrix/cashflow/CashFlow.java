package org.quant.fenmatrix.cashflow;

import org.quant.fenmatrix.time.Date;

public class CashFlow extends Event {

  private Date StartDate;

  private Date EndDate;

  public double AllInRate;

  public double discRate;

  public double NPV;

  public double Amount;

  public double Notional;

  public static CashFlow valueOf(Date start, Date end) {
    CashFlow cashflow = new CashFlow();
    cashflow.StartDate(start).EndDate(end);
    return cashflow;
  }

  @Override
  public String toString() {

    return String.format("startdate[%s]enddate[%s]Amount[%.4f]NPV[%.4f]", StartDate.toString(), EndDate.toString(),
        Amount, NPV);

  }

  public Date getStartDate() {
    return StartDate;
  }

  public CashFlow StartDate(Date startDate) {
    this.StartDate = startDate;
    return this;
  }

  public Date getEndDate() {
    return EndDate;
  }

  public CashFlow EndDate(Date endDate) {
    this.EndDate = endDate;
    return this;
  }

}
