package org.quant.fenmatrix.instruments;

public interface Instrument {

  public double NPV();

  public boolean isExpired();
  
  public void Valuation();

}
