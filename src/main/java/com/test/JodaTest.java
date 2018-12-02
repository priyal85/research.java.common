package com.test;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormatterBuilder;

public class JodaTest
{

  public static void main(String[] args)
  {
    Period period = new Period("P1Y2W5DT3H23M");
    System.out.println(period.toString());
    System.out.println(period.toString(new PeriodFormatterBuilder().toFormatter()));
    System.out.println(period.toString(new PeriodFormatterBuilder().toFormatter()));
    Period emptyPeriod = new Period();
    System.out.println(emptyPeriod.getMillis());

  }

}
