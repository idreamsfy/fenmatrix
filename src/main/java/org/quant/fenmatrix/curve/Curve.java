package org.quant.fenmatrix.curve;

import java.util.ArrayList;

import org.quant.fenmatrix.asset.Rate;
import org.quant.fenmatrix.asset.Rate.QuoteType;
import org.quant.fenmatrix.convention.Currency;
import org.quant.fenmatrix.interpolation.Interpolation;
import org.quant.fenmatrix.interpolation.LinearInterpolation;
import org.quant.fenmatrix.time.Date;
import org.quant.fenmatrix.time.TimeUtil;
import org.quant.fenmatrix.tools.FM;

public class Curve {

  private String Index;

  private Date spotdate;

  private ArrayList<Rate> curve;

  private ArrayList<Rate> mktcurve;

  private QuoteType qtype = QuoteType.Mid;

  private Currency Ccy;

  private Interpolation Interpolator;

  /**/
  public enum CurveMethod {
    Linear,
    Spline,
    CubicSpline,
    SABR,
    Newton;
  }

  private CurveMethod method;

  public static Curve valueOf(String idx, Date spotDate, ArrayList<Rate> curve) {
    Curve cv = new Curve();
    cv.Index = idx;
    cv.spotdate = spotDate;
    cv.mktcurve = curve;
    cv.method = CurveMethod.Linear;
    return cv;
  }

  public String getIndex() {
    return Index;
  }

  public Curve setIndex(String index) {
    Index = index;
    return this;
  }

  public ArrayList<Rate> getMktCurve() {
    return mktcurve;
  }

  public void setMktCurve(ArrayList<Rate> curve) {
    mktcurve = curve;
  }

  public void shift(double bps) {
    // TODO shift curve
  }

  public Date getAsofdate() {
    return spotdate;
  }

  public void setAsofdate(Date asofdate) {
    this.spotdate = asofdate;
  }

  public Currency getCcy() {
    return Ccy;
  }

  public void setCcy(Currency ccy) {
    Ccy = ccy;
  }

  public double getRate(Date mat) {

    return .0;
  }

  public QuoteType getQtype() {
    return qtype;
  }

  public void setQtype(QuoteType qtype) {
    this.qtype = qtype;
  }

  public CurveMethod getMethod() {
    return method;
  }

  public Curve setMethod(CurveMethod method) {
    this.method = method;
    return this;
  }

  public void GenerateCurve() {

    this.curve = new ArrayList<Rate>();
    if (this.method == CurveMethod.Linear) {
      Interpolator = new LinearInterpolation();
      Interpolator.setCurve(this.mktcurve);
      LinearInterpolation linear = (LinearInterpolation) Interpolator;
      linear.interpolate();

      this.curve.add(Rate.valueOf(spotdate, 10, linear.getValue(10)));
      this.curve.add(Rate.valueOf(spotdate, 34, linear.getValue(34)));
      this.curve.add(Rate.valueOf(spotdate, 94, linear.getValue(94)));
      this.curve.add(Rate.valueOf(spotdate, 185, linear.getValue(185)));
      this.curve.add(Rate.valueOf(spotdate, 277, linear.getValue(277)));
      this.curve.add(Rate.valueOf(spotdate, 369, linear.getValue(369)));
      this.curve.add(Rate.valueOf(spotdate, 459, linear.getValue(459)));
      this.curve.add(Rate.valueOf(spotdate, 550, linear.getValue(550)));
      this.curve.add(Rate.valueOf(spotdate, 642, linear.getValue(642)));
      this.curve.add(Rate.valueOf(spotdate, 734, linear.getValue(734)));
      this.curve.add(Rate.valueOf(spotdate, 824, linear.getValue(824)));
      this.curve.add(Rate.valueOf(spotdate, 915, linear.getValue(915)));
      this.curve.add(Rate.valueOf(spotdate, 1007, linear.getValue(1007)));
      this.curve.add(Rate.valueOf(spotdate, 1000, linear.getValue(1000)));
      this.curve.add(Rate.valueOf(spotdate, 1189, linear.getValue(1189)));
      this.curve.add(Rate.valueOf(spotdate, 1280, linear.getValue(1280)));
      this.curve.add(Rate.valueOf(spotdate, 1372, linear.getValue(1372)));
      this.curve.add(Rate.valueOf(spotdate, 1466, linear.getValue(369)));
      this.curve.add(Rate.valueOf(spotdate, 1557, linear.getValue(459)));
      this.curve.add(Rate.valueOf(spotdate, 1648, linear.getValue(550)));
      this.curve.add(Rate.valueOf(spotdate, 1739, linear.getValue(642)));
      this.curve.add(Rate.valueOf(spotdate, 1830, linear.getValue(734)));
      this.curve.add(Rate.valueOf(spotdate, 1921, linear.getValue(824)));
      this.curve.add(Rate.valueOf(spotdate, 2012, linear.getValue(915)));
      this.curve.add(Rate.valueOf(spotdate, 2103, linear.getValue(1007)));
      this.curve.add(Rate.valueOf(spotdate, 2195, linear.getValue(1000)));
      this.curve.add(Rate.valueOf(spotdate, 2285, linear.getValue(1189)));
      this.curve.add(Rate.valueOf(spotdate, 2376, linear.getValue(1280)));
      this.curve.add(Rate.valueOf(spotdate, 2468, linear.getValue(1372)));
      this.curve.add(Rate.valueOf(spotdate, 2560, linear.getValue(1372)));
    }

  }

  public ArrayList<Rate> getCurve() {
    return curve;
  }

  public void setCurve(ArrayList<Rate> curve) {
    this.curve = curve;
  }

  public Rate getValuedRate(Date date) {

    Rate rate = Rate.valueOf(this.spotdate, 0, 0);

    if (Interpolator == null) {
      FM.error("Can't get the curve value, please set a interpolator first!");
    }

    if (this.method == CurveMethod.Linear) {
      LinearInterpolation linear = (LinearInterpolation) Interpolator;
      rate.tenor = TimeUtil.DaysBetween(this.spotdate, date);
      rate.mid = linear.getValue(rate.tenor);

    }

    return rate;

  }

  public static void main(String[] args) {

    ArrayList<Rate> mkts = new ArrayList<Rate>();
    Date spotdate = Date.valueOf("20160115");

    mkts.add(Rate.valueOf(spotdate, "1W", 2.36000));
    mkts.add(Rate.valueOf(spotdate, "1M", 2.321111));
    mkts.add(Rate.valueOf(spotdate, "3M", 2.310167));
    mkts.add(Rate.valueOf(spotdate, "6M", 2.271833));
    mkts.add(Rate.valueOf(spotdate, "1Y", 2.232611));
    mkts.add(Rate.valueOf(spotdate, "2Y", 2.22600));
    mkts.add(Rate.valueOf(spotdate, "3Y", 2.28850));
    mkts.add(Rate.valueOf(spotdate, "4Y", 2.389833));
    mkts.add(Rate.valueOf(spotdate, "5Y", 2.485389));
    mkts.add(Rate.valueOf(spotdate, "7Y", 2.63000));

    Curve curve = Curve.valueOf("SHIBOR", spotdate, mkts);

    curve.GenerateCurve();

    for (Rate r : curve.getCurve()) {
      FM.info(String.format("Date[%s]Rate[%f]", r.date.toString(), r.mid));
    }

    FM.info(String.format("Date[%s]Rate[%f]", "20180304", curve.getValuedRate(Date.valueOf("20180304")).mid));

  }

}
