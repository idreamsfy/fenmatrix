package org.quant.fenmatrix.time;

import org.quant.fenmatrix.tools.FMException;

public enum Freq {
  /**zero*/
  None(-1),
  /** zero */
  Zero(0),
  /** once a year */
  Annual(1),
  /** twice a year */
  Semiannual(2),
  /** every fourth month */
  EveryFourthMonth(3),
  /** every third month */
  Quarterly(4),
  /** every second month */
  Bimonthly(6),
  /** once a month */
  Monthly(12),
  /** every fourth week */
  EveryFourthWeek(13),
  /** every second week */
  Biweekly(26),
  /** once a week */
  Weekly(52),
  /** once a day */
  Daily(365),
  /** some other unknown frequency */
  OtherFrequency(999);

  private final int enumValue;

  private Freq(final int frequency) {
    this.enumValue = frequency;
  }

  static public Freq valueOf(final int value) {
    switch (value) {
      case -1:
        return Freq.None;
      case 0:
        return Freq.Zero;
      case 1:
        return Freq.Annual;
      case 2:
        return Freq.Semiannual;
      case 3:
        return Freq.EveryFourthMonth;
      case 4:
        return Freq.Quarterly;
      case 6:
        return Freq.Bimonthly;
      case 12:
        return Freq.Monthly;
      case 13:
        return Freq.EveryFourthWeek;
      case 26:
        return Freq.Biweekly;
      case 52:
        return Freq.Weekly;
      case 365:
        return Freq.Daily;
      case 999:
        return Freq.OtherFrequency;
      default:
        throw new FMException("value must be one of -1,0,1,2,3,4,6,12,13,26,52,365,999"); // TODO: message
    }
  }

  public int toInteger() {
    return this.enumValue;
  }

}
