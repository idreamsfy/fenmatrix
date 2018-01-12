package org.quant.fenmatrix.interpolation;

import cern.colt.list.DoubleArrayList;

public abstract class Interpolation {

	protected DoubleArrayList _x;

	protected DoubleArrayList _y;


	public void setCurve(double[] x, double[] y) {
		_x = new DoubleArrayList(x);
		_y = new DoubleArrayList(y);
	}

	public abstract double getValue(double x);

}
