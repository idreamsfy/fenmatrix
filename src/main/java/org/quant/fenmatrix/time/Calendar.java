package org.quant.fenmatrix.time;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Calendar {

  private String Name;

  private Map<Integer, ArrayList<Date>> Holidays = new HashMap<Integer, ArrayList<Date>>();

  public Calendar() {
    this.Name = "NONE";
  }

  public Calendar(String name) {
    this.setName(name);

  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }

  public void InitHolidays() {

  }

  public Map<Integer, ArrayList<Date>> getHolidays() {
    return Holidays;
  }

  public void setHolidays(Map<Integer, ArrayList<Date>> holidays) {
    Holidays = holidays;
  }

  public boolean isHoliDay(Date d) {
    ArrayList<Date> holidays = Holidays.get(d.getDate().getYear());
    if (holidays != null) {
      for (Date t : holidays) {
        if (t.compareTo(d) == 0)
          return true;
      }
    }

    return false;
  }

  public static Calendar getCalendar() {
    Calendar c = new Calendar();
    //TODO get from config or env
    return c;

  }
}
