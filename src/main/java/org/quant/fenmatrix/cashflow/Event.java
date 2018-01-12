package org.quant.fenmatrix.cashflow;

import org.quant.fenmatrix.time.Date;

public class Event implements Cloneable {

  private Date _eventdate;

  public enum EventType{
    Cash,
    Nontion,
    Fixing;
  }
  
  public EventType eventtype;

  protected double amount = 0.0;

  public Date get_eventdate() {
    return _eventdate;
  }

  public void set_eventdate(Date _eventdate) {
    this._eventdate = _eventdate;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

}
