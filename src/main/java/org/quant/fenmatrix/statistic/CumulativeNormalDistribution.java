package org.quant.fenmatrix.statistic;

import org.apache.commons.math3.distribution.NormalDistribution;

@SuppressWarnings("serial")
public class CumulativeNormalDistribution extends NormalDistribution {

  public double Calc(double z) {

    NormalDistribution standardNormal = new NormalDistribution();

    return standardNormal.inverseCumulativeProbability(z);
  }

  public static void main(String[] agrs) {
   // System.out.println(String.format("Distribution[%.6f]", CumulativeNormalDistribution.Calc(0.95)));
   // System.out.println(String.format("Distribution[%.6f]", CumulativeNormalDistribution.Calc(0.95)));
    
  }
}
