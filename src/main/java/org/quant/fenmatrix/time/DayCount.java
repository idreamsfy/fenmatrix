package org.quant.fenmatrix.time;

import org.joda.time.Days;
import org.joda.time.Period;
import org.quant.fenmatrix.asset.IntBasis;
import org.quant.fenmatrix.tools.FM;

public class DayCount {

  private IntBasis basis;

  public IntBasis getBasis() {
    return basis;
  }

  public DayCount setBasis(IntBasis basis) {
    this.basis = basis;
    return this;
  }

  public static DayCount valueOf(IntBasis basis) {
    DayCount daycount = new DayCount();

    daycount.basis = basis;

    return daycount;

  }

  public double C30_360(Date d1, Date d2) {
    Period period = new Period(d1.getDate(), d2.getDate());

    return 0.0;
  }

  public double Act_Act() {

    return 0.0;
  }

  public double DaysBetween(Date d1, Date d2) {

    //check if d1<d2
    int flag = 1;
    if (d1.compareTo(d2) > 0)
      flag = -1;
    /*
    FM.debug(String.format("Date1[%s]Date2[%s]", d1.toString(), d2.toString()));
    Period period = new Period(d1.getDate(), d2.getDate());

    //for simple test use month 30 year 365 here 

    FM.debug(String.format("Period[%d]", period.getDays()));

    FM.debug(String.format("Period[%d]", period.getMonths()));
    FM.debug(String.format("Period[%d]", period.getYears()));
    double counts = flag * (period.getDays() + (period.getMonths() * 30) + (period.getYears() * 365));
    FM.debug(String.format("days count[%f]", counts));
    */
    Days days=Days.daysBetween(d1.getDate(), d2.getDate());
    FM.debug(String.format("days count [%d]", days.getDays()));
    return flag*days.getDays();
  }

  public double getFactor(Date d1, Date d2) {

    switch (this.basis) {
      case A360:
        break;

      case ACTUAL:
        break;

      default:
        FM.warn("NO BASIS defined!");
        break;

    }

    return 0.0;
  }

  public double getCouponFactor(Date d1, Date d2, Freq freq) {

    return 0.0;
  }

  public double YearFactor(double tenor) {

    return tenor / 360;
  }
}
