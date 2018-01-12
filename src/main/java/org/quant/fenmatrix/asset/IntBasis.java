package org.quant.fenmatrix.asset;

public enum IntBasis {

  NO_INTBASIS, /*  */
  A360, /*  */
  A365F, /*  */
  A30, /*  */
  A30E, /*  */
  ACTUAL, /*  */
  ACT29, /*  */
  D360, /*  */
  D365, /*  */
  COUPON, /*  */
  PRICE, /*  */
  PERF, /*  */
  D360PR, /*  */
  D365PR, /*  */
  EXC29, /* number of days between start and end excluding February 29 ( and always using a basis of 365.) */
  END29, /* Calc End 366 */
  DAILY_PRICE, /* Used for commodity's - NTL is daily not per reset */
  BEG29, /* use 366 if the period begins within a leap year, use 365 otherwise */
  B252, /*  */
  JGB, /* Japanese Government Bond */
  DRV, /* DRV 360/360 */
  EURBD, /* ISDA 30E/360 */
  A30_365, /*  */
  A30ACT, /*  */
  ACTVAR, /*  */
  A364, /* A364 standard */
  ACTR, /* ACT/ACT by Russian standard */
  A365RR;/* Russian cash flow rounding and accrual calculation */
}
