package org.quant.fenmatrix.interpolation;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.quant.fenmatrix.tools.FMException;

public class LinearInterpolation extends Interpolation {

	public PolynomialSplineFunction _impl = null;

	public LinearInterpolation() {
		
	};
	

	public void interpolate()throws FMException {
		LinearInterpolator linear = new LinearInterpolator();
		try {
			_impl = linear.interpolate(super._x.elements(), super._y.elements());
		} catch (DimensionMismatchException e1) {
			throw new FMException("Dimension exception:" + e1.getMessage());
		}
	}
	
	@Override
	public double getValue(double x) {
		if (_impl == null)
			return .0;
		return _impl.value(x);

	}

	public static void main(String[] args) {

		/*
		Point p1 = new Point(new Date(440232), .0, .0, 0.02);
		Point p2 = new Point(new Date(440240), .0, .0, 0.03);
		Point p3 = new Point(new Date(440290), .0, .0, 0.032);
		Point p4 = new Point(new Date(440320), .0, .0, 0.041);
		Point p5 = new Point(new Date(440530), .0, .0, 0.055);

		ArrayList<Point> line = new ArrayList<Point>();

		line.add(p1);
		line.add(p2);
		line.add(p3);
		line.add(p4);
		line.add(p5);
*/
	

		LinearInterpolation linear = new LinearInterpolation();
		linear.setCurve(new double[] { 0.0, 1.0, 2.0, 3.0, 4.0 }, new double[] { 5.0, 4.0, 3.0, 2.0, 1.0 });
		
		linear.interpolate();
		
		double test = linear._impl.value(1.1);
		
		System.out.print(test);

	}
}
