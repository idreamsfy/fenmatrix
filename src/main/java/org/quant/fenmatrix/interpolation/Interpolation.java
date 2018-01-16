package org.quant.fenmatrix.interpolation;

import java.util.ArrayList;

import org.quant.fenmatrix.asset.Rate;

import cern.colt.list.DoubleArrayList;

public abstract class Interpolation {

  protected DoubleArrayList _x;

  protected DoubleArrayList _y;

  public void setCurve(double[] x, double[] y) {
    _x = new DoubleArrayList(x);
    _y = new DoubleArrayList(y);
  }

  public void setCurve(ArrayList<Rate> curve) {
    for (Rate r : curve) {

      _x.add(r.tenor);

      _y.add(r.mid);

    }

  }

  public abstract double getValue(double x);

}
