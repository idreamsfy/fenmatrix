package org.quant.fenmatrix.instruments;

import org.quant.fenmatrix.time.Date;


public  class Forward implements Instrument {

	private Date ValDate;

  public double NPV() {
    return 0;
  }

  public boolean isExpired() {
    return false;
  }

  public void Valuation() {
  }


}