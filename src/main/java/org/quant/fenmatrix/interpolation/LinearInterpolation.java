package org.quant.fenmatrix.interpolation;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.quant.fenmatrix.tools.FMException;

public class LinearInterpolation extends Interpolation {

  public PolynomialSplineFunction _impl = null;

  public void interpolate() throws FMException {
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

    LinearInterpolation linear = new LinearInterpolation();
    linear.setCurve(new double[] {0.0, 1.0, 2.0, 3.0, 4.0}, new double[] {5.0, 4.0, 3.0, 2.0, 1.0});

    linear.interpolate();

    double test = linear._impl.value(1.1);

    System.out.print(test);

  }
}
