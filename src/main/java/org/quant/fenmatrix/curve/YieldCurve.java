package org.quant.fenmatrix.curve;

import java.util.ArrayList;
import org.quant.fenmatrix.asset.IntRate;
import org.quant.fenmatrix.asset.YieldRate;
import org.quant.fenmatrix.time.Date;
import org.quant.fenmatrix.time.Freq;
import org.quant.fenmatrix.tools.FM;

public class YieldCurve extends Curve {

  private String name;

  private ArrayList<YieldRate> YC = null;

  private Freq freq;

  public YieldCurve(Curve spot) {

  }

  public YieldCurve(YieldRate FlatRate) {

    YC = new ArrayList<YieldRate>();
    FM.require(FlatRate.MatDate.isValid(), "please set maturity for this rate");
    YC.add(FlatRate);

  }

  public void BootstrapZCRate() {

  }

  public IntRate spotRate() {

    IntRate intrate = null;

    return intrate;

  }

  public IntRate discountFactor(Date maturity) {

    IntRate intrate = IntRate.valueOf(0, null, null, freq, null, null);

    intrate.setFreq(this.freq);

    int f = this.freq.toInteger();

    FM.require(this.freq != Freq.OtherFrequency, "the counpon frequency must be defined!");

    if (this.freq == Freq.Zero) {

      //TODO bootstrap method to calculate zero coupon yield curve
      
    } else {
      YieldRate yr = getYield(maturity);

      intrate.setIntRate(1 / Math.pow(1 + yr.rate, f));
    }

    return intrate;

  }

  public YieldRate getYield(Date mat) {

    FM.require(this.YC == null, "please build the yield curve first!");

    YieldRate yr = null;

    for (YieldRate y : this.YC) {

      if (y.MatDate.compareTo(mat) == 0) {
        yr = y;

        break;
      }
    }

    return yr;

  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ArrayList<YieldRate> getMktRates() {
    return YC;
  }

  public void setMktRates(ArrayList<YieldRate> mktRates) {
    this.YC = mktRates;
  }

  public static void main(String[] args) {

  }

  public Freq getFreq() {
    return freq;
  }

  public void setFreq(Freq freq) {
    this.freq = freq;
  }

}
