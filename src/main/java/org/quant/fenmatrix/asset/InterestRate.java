package org.quant.fenmatrix.asset;

import org.quant.fenmatrix.convention.Compounding;
import org.quant.fenmatrix.convention.Index;
import org.quant.fenmatrix.time.Calendar;
import org.quant.fenmatrix.time.Freq;

public class InterestRate implements Cloneable {

  public Rate quotes;

  private double Rate;

  private Compounding compound;

  private boolean freqMakesSense;

  private Freq freq;

  private Calendar calendar = null;

  private IntBasis Basis;

  private Index idx = null;

  public enum FixOrFloat {

    NOTYPE,

    Fixed,

    FLOATING;

  }

  private FixOrFloat IntType;

  public enum DisType {

    NO_DISCTYPE, /*  */
    FIXED_DISC, /*  */
    DUAL_DISC, /*  */
    FORWARD_DISC, /*  */
    STRIKE_DISC, /*  */
    RATIO_DISC, /*  */
    NO_DISC, /* Rate is already discount rate, no discount */
    COLL_FRA_DISC /*  */

  }

  private InterestRate() {

  }

  public static InterestRate valueOf(double rate,
      FixOrFloat inttype,
      Compounding compound,
      Freq freq,
      Calendar c,
      Index idx) {

    InterestRate interest = new InterestRate();
    interest.setAssettype(inttype).setCalender(c).setIdx(idx).setFreq(freq).setCompound(compound).setIntRate(rate);
    return interest;

  }

  public Compounding getCompound() {
    return compound;
  }

  public InterestRate setCompound(Compounding compound) {
    this.compound = compound;
    return this;
  }

  public boolean isFreqMakesSense() {
    return freqMakesSense;
  }

  public InterestRate setFreqMakesSense(boolean freqMakesSense) {
    this.freqMakesSense = freqMakesSense;
    return this;
  }

  public Freq getFreq() {
    return freq;
  }

  public InterestRate setFreq(Freq freq) {
    this.freq = freq;
    return this;
  }

  public double getIntRate() {
    return Rate;
  }

  public InterestRate setIntRate(double intRate) {
    this.Rate = intRate;
    return this;
  }

  public FixOrFloat getAssettype() {
    return IntType;
  }

  public InterestRate setAssettype(FixOrFloat assettype) {
    this.IntType = assettype;
    return this;
  }

  public Calendar getCalendar() {
    return calendar;
  }

  public InterestRate setCalender(Calendar calender) {
    this.calendar = calender;
    return this;
  }

  public Index getIdx() {
    return idx;
  }

  public InterestRate setIdx(Index idx) {
    this.idx = idx;
    return this;
  }

  public IntBasis getBasis() {
    return Basis;
  }

  public void setBasis(IntBasis basis) {
    Basis = basis;
  }
}
