package org.quant.fenmatrix.instruments;

public  class Option implements Instrument {

  public enum CorP {
    Call,
    Put;
    public int toInteger() {
      if (this == Call)
        return 1;
      else
        return -1;

    }
  }

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
