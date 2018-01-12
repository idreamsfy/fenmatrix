package org.quant.fenmatrix.asset;

import org.quant.fenmatrix.time.Date;

public class YieldRate {

  public Rate quotes;

  public double rate = 0.0;

  public enum Type {
    Cash,
    Forward,
    Swap;
  }

  public Date MatDate;

  public IntBasis Basis;

}
