package org.quant.fenmatrix.time;

import java.io.Serializable;
import org.quant.fenmatrix.tools.FMException;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@SuppressWarnings("serial")
public class Date implements Comparable<Date>, Serializable, Cloneable {

  private static DateTimeFormatter format = DateTimeFormat.forPattern("yyyyMMdd");

  private LocalDate date = LocalDate.parse("99991231", format);

  public Date() {
  }

  public Date(String str) {
    this.date = LocalDate.parse(str, format);
  }

  public Date(java.util.Date d) {
    date = new LocalDate(d);
  }

  public Date(LocalDate d) {
    this.date = d;
  }

  public Date(Date d) {
    this.date = d.getDate();
  }

  public boolean isValid() {
    if (this.date.toString("yyyyMMdd").compareTo("99991231") == 0)
      return false;
    else
      return true;
  }

  public LocalDate getDate() {
    return this.date;
  }

  public void setLocalDate(LocalDate d) {
    this.date = d;
  }

  public Date DateGap(int days) {

    if (days == 0)
      return this;

    LocalDate newdate;
    if (days <= 0)
      newdate = this.date.minusDays(Math.abs(days));
    else
      newdate = this.date.plusDays(days);
    return new Date(newdate);
  }

  public void parseofYMD(String d) throws FMException {
    try {
      this.date = LocalDate.parse(d, format);
    } catch (Exception e) {
      throw new FMException("Error in parse the date:" + e.getMessage());
    }
  }

  public static Date valueOf(String d, String format) throws FMException {
    try {
      DateTimeFormatter f = DateTimeFormat.forPattern(format);
      Date newdate = new Date();
      newdate.date = LocalDate.parse(d, f);

      return newdate;

    } catch (Exception e) {
      throw new FMException("Error in parse the date:" + e.getMessage());
    }
  }

  public static Date valueOf(String d) throws FMException {
    try {
      DateTimeFormatter f = DateTimeFormat.forPattern("yyyyMMdd");
      Date newdate = new Date();
      newdate.date = LocalDate.parse(d, f);

      return newdate;

    } catch (Exception e) {
      throw new FMException("Error in parse the date:" + e.getMessage());
    }
  }

  public int compareTo(Date d) {
    return this.date.compareTo(d.getDate());
  }

  @Override
  public String toString() {
    return this.date.toString("yyyyMMdd");
  }

  public static void main(String[] agrs) {

    Date newdate = new Date();
    newdate.parseofYMD("20171212");
    System.out.println(newdate.toString());
    System.lineSeparator();
    System.out.println("year:" + newdate.getDate().getYear());
    System.out.println("year:" + newdate.getDate().getYear());

  }
}
