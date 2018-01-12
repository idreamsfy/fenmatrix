package org.quant.fenmatrix.asset;

import org.joda.time.Days;
import org.joda.time.Weeks;
import org.quant.fenmatrix.time.Date;
import org.quant.fenmatrix.time.TimeUtil;
import org.quant.fenmatrix.tools.FM;

public class Rate {

  public String term;

  public Date date;

  public double tenor;

  public double bid;

  public double ask;

  public double mid;

  public enum QuoteType {
    Bid,
    Ask,
    Mid;
  }

  public Rate() {
    tenor = .0;
    bid = .0;
    ask = .0;
    mid = .0;
  }

  public Rate(Date date, String t, double b, double a, double m) {

    this.term = t;

    if (t.compareTo("O/N") == 0) {
      this.date = date;
      this.tenor = 1;

    } else if (t.substring(t.length() - 1).compareTo("D") == 0) {
      this.date = TimeUtil.addDays(date, Integer.parseInt(t.substring(0, t.length() - 1)));
      this.tenor = Integer.parseInt(t.substring(0, t.length() - 1));
    } else if (t.substring(t.length() - 1).compareTo("W") == 0) {
      Weeks weeks = Weeks.weeks(Integer.parseInt(t.substring(0, t.length() - 1)));
      this.date = TimeUtil.addWeek(date, weeks.getWeeks());
      this.tenor = weeks.toStandardDays().getDays();
    } else if (t.substring(t.length() - 1).compareTo("M") == 0) {
      this.date = TimeUtil.addMonth(date, Integer.parseInt(t.substring(0, t.length() - 1)));
      this.tenor = Days.daysBetween(date.getDate(), this.date.getDate()).getDays();
    } else if (t.substring(t.length() - 1).compareTo("Y") == 0) {
      this.date = TimeUtil.addYear(date, Integer.parseInt(t.substring(0, t.length() - 1)));
      this.tenor = Days.daysBetween(date.getDate(), this.date.getDate()).getDays();
    } else
      FM.error("Unkown tenor type!");

    this.bid = b;
    this.ask = a;
    this.mid = m;
  }

  public static Rate valueOf(Date asofdate, String t, double b, double a, double m) {
    Rate rate = new Rate(asofdate, t, b, a, m);
    return rate;
  }

  public static Rate valueOf(Date asofdate, String t, double m) {
    Rate rate = new Rate(asofdate,t, 0.0, 0.0, m);
    return rate;
  }

  public static double Tenor(Date d, String t) {

    if (t.compareTo("O/N") == 0) {
      //Days days=Days.days(1);

      return 1;
    } else if (t.substring(t.length() - 1).compareTo("W") == 0) {
      Weeks weeks = Weeks.weeks(Integer.parseInt(t.substring(0, t.length() - 1)));
      return weeks.toStandardDays().getDays();
    } else if (t.substring(t.length() - 1).compareTo("M") == 0) {
      //Months months=Months.months(Integer.parseInt(t.substring(0, t.length()-1)));
      Date temp = TimeUtil.addMonth(d, Integer.parseInt(t.substring(0, t.length() - 1)));
      Days days = Days.daysBetween(d.getDate(), temp.getDate());
      return days.getDays();
    } else if (t.substring(t.length() - 1).compareTo("Y") == 0) {
      Date temp = TimeUtil.addYear(d, Integer.parseInt(t.substring(0, t.length() - 1)));
      Days days = Days.daysBetween(d.getDate(), temp.getDate());
      return days.getDays();
    } else
      FM.error("Unkown tenor type!");
    return 0;
  }
}
