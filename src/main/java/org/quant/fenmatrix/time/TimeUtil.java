package org.quant.fenmatrix.time;

import org.joda.time.Period;
import org.quant.fenmatrix.asset.IntBasis;
import org.quant.fenmatrix.tools.FM;

public class TimeUtil {

  public static boolean isBusDay(Date d) {

    Calendar c = Calendar.getCalendar();

    return isBusDay(d, c);
  }

  public static boolean isBusDay(Date d, Calendar c) {

    FM.debug("before check business date:" + d.toString());
    if (d.getDate().getDayOfWeek() > 5)
      return false;

    return true;
  }

  public static Date NextBusDay(Date d, Calendar c, BDRule rule) {

    Date tempdate = d.DateGap(1);

    FM.debug("before adjust business date:" + d.toString());

    if (c == null)
      c = Calendar.getCalendar();

    if (rule == null) {
      FM.warn("No Day rule defined! Using default: following!");
      rule = BDRule.Following;
    }

    while (!isBusDay(tempdate, c)) {

      switch (rule) {
        case Following:
          tempdate = tempdate.DateGap(1);
          break;
        case Preceding:
          tempdate = tempdate.DateGap(-1);
          break;
        default:
          FM.warn("Unsupport rule, will not adjusted date!");
          return tempdate;
      }
    }
    FM.debug("after adjust business date:" + d.toString());
    return tempdate;
  }

  public static Date addDays(Date d, int days, Calendar c, BDRule rule) {

    Date d1 = new Date(d);

    d1.setLocalDate(d1.getDate().plusDays(days));
    if (rule == null || rule == BDRule.Unadjusted)
      return d1;
    if (!isBusDay(d1))
      d1 = NextBusDay(d1, c, rule);

    return d1;
  }

  public static Date addDays(Date d, int days) {

    return addDays(d, days, null, null);
  }

  public static Date addMonth(Date d, int mons, Calendar c, BDRule rule) {
    Date d1 = new Date(d);

    d1.setLocalDate(d1.getDate().plusMonths(mons));

    if (rule == null || rule == BDRule.Unadjusted)
      return d1;

    if (!isBusDay(d1))
      d1 = NextBusDay(d1, c, rule);

    return d1;
  }

  public static Date addMonth(Date d, int mons) {
    return addMonth(d, mons, null, null);
  }

  public static Date addWeek(Date d, int weeks, Calendar c, BDRule rule) {
    Date d1 = new Date(d);

    d1.setLocalDate(d1.getDate().plusWeeks(weeks));

    if (rule == null || rule == BDRule.Unadjusted)
      return d1;

    if (!isBusDay(d1))
      d1 = NextBusDay(d1, c, rule);

    return d1;
  }

  public static Date addWeek(Date d, int weeks) {
    return addWeek(d, weeks, null, null);
  }

  public static Date addYear(Date d, int years, Calendar c, BDRule rule) {
    Date d1 = new Date(d);

    d1.setLocalDate(d1.getDate().plusYears(years));

    if (rule == null || rule == BDRule.Unadjusted)
      return d1;

    if (!isBusDay(d1))
      d1 = NextBusDay(d1, c, rule);

    return d1;
  }

  public static Date addYear(Date d, int years) {

    return addYear(d, years, null, null);
  }

  public static void main(String[] agrs) {

    Date test = new Date("20171231");

    test = NextBusDay(test, null, BDRule.Following);

    System.out.println(test.toString());

  }
}
