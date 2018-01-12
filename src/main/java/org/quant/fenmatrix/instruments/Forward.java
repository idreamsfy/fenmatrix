package org.quant.fenmatrix.instruments;

import org.quant.fenmatrix.time.Date;


public  class Forward implements Instrument {

	private Date ValDate;

  @Override
  public double NPV() {
    return 0;
  }

  @Override
  public boolean isExpired() {
    return false;
  }

  @Override
  public void Valuation() {
  }


}