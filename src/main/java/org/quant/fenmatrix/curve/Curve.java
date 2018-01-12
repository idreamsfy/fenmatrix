package org.quant.fenmatrix.curve;

import java.util.ArrayList;

import org.quant.fenmatrix.asset.Rate;
import org.quant.fenmatrix.asset.Rate.QuoteType;
import org.quant.fenmatrix.convention.Currency;
import org.quant.fenmatrix.time.Date;

public class Curve {

  private String Index;

  private Date spotdate;

  private ArrayList<Rate> curve;

  private ArrayList<Rate> mktcurve;

  private QuoteType qtype = QuoteType.Mid;

  private Currency Ccy;

  public enum CurveMethod {
    Linear,
    Spline,
    CubicSpline,
    Newton;
  }

  private CurveMethod method;

  public static Curve valueOf(String idx, Date spotDate, ArrayList<Rate> curve) {
    Curve cv = new Curve();
    cv.Index = idx;
    cv.spotdate = spotDate;
    cv.mktcurve = curve;
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
    if (this.method == CurveMethod.Linear) {
      
    }
  }

  public ArrayList<Rate> getCurve() {
    return curve;
  }

  public void setCurve(ArrayList<Rate> curve) {
    this.curve = curve;
  }

  public static void main(String[] args) {
    
    ArrayList<Rate> mkts=new ArrayList<Rate>();
    Date spotdate=Date.valueOf("20190905");
    mkts.add(Rate.valueOf(spotdate, "O/N", 2.8300));
    mkts.add(Rate.valueOf(spotdate, "1W", 2.8300));
    mkts.add(Rate.valueOf(spotdate, "2W", 2.8300));
    mkts.add(Rate.valueOf(spotdate, "1M", 2.8300));
    mkts.add(Rate.valueOf(spotdate, "6M", 2.8300));
    mkts.add(Rate.valueOf(spotdate, "9M", 2.8300));
    mkts.add(Rate.valueOf(spotdate, "1Y", 2.8300));
    Curve curve=Curve.valueOf("SHIBOR", spotdate, mkts);
    
    
    
    
  }
  
  

}
