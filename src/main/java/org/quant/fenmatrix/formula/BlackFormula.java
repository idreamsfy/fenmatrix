package org.quant.fenmatrix.formula;

import org.quant.fenmatrix.instruments.Option;
import org.quant.fenmatrix.statistic.CumulativeNormalDistribution;
import org.quant.fenmatrix.tools.FM;

public class BlackFormula {

  public static double blackFormula(Option.CorP CorP,
      double strike,
      double forward,
      double stddev,
      double discount,
      double displacement) {

    FM.require(strike >= 0.0, "strike must be non-negative"); // TODO: message
    FM.require(forward > 0.0, "forward must be positive"); // TODO: message
    FM.require(stddev >= 0.0, "stddev must be non-negative"); // TODO: message
    FM.require(discount > 0.0, "discount must be positive"); // TODO: message
    FM.require(displacement >= 0.0, "displacement must be non-negative"); // TODO: message

    forward += displacement;
    strike += displacement;
    if (stddev == 0.0)
      return Math.max((forward - strike) * CorP.toInteger(), (0.0d)) * discount;

    if (strike == 0.0) // strike=0 iff displacement=0
      return (CorP == Option.CorP.Call ? forward * discount : 0.0);

    final double d1 = Math.log(forward / strike) / stddev + 0.5 * stddev;
    final double d2 = d1 - stddev;

    // TODO: code review
    final CumulativeNormalDistribution phi = new CumulativeNormalDistribution();
    final double result =
        discount * CorP.toInteger() * (forward * phi.Calc(CorP.toInteger() * d1) - strike * phi.Calc(CorP.toInteger() * d2));

    if (result >= 0.0)
      return result;
    throw new ArithmeticException("a negative value was calculated"); // TODO: message
  }

}
