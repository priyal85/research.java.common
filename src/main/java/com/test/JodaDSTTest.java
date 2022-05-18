package com.test;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.joda.time.format.ISODateTimeFormat;

public class JodaDSTTest
{

  public static void main(String[] args)
  {
    String zone = "Europe/Stockholm";
   //  zone = "Atlantic/Bermuda";
    DateTime dt = DateTime.parse("2022-03-27T02:30:00.000Z").withZone(DateTimeZone.forID(zone));
    DateTime dtUtc = dt.withZone(DateTimeZone.UTC);
    System.out.println("Date Time Swe: " + dt);
    System.out.println("Date Time UTC: " + dtUtc);
    System.out.println("Zone: " + dt.getZone());
    System.out.println("DST offset Joda: " + dt.getZone().toTimeZone().getDSTSavings());

    int offSetMillies = dt.getZone().getOffset(dt.getMillis());
    String offSetOperator = "+";
    if (offSetMillies<0)
    {
      offSetOperator = "-";
      offSetMillies = Math.abs(offSetMillies);
    }
        
    System.out.println("Time offset Joda: " + offSetMillies);

    Duration duration = Duration.millis(offSetMillies);
    Period period = duration.toPeriod();
    System.out.println("Duration: " + period.getHours() + " " + period.getMinutes());
    long HH = period.getHours();
    long MM = period.getMinutes();
  //  long SS = period.getSeconds();
    String offSetInHHMM = String.format("%02d:%02d", HH, MM);
    System.out.println("Duration formatted: " + offSetInHHMM);

    LocalDate startDate = dt.toLocalDate();
    LocalDate startDateUtc = dtUtc.toLocalDate();
    System.out.println("Date Time : " + startDate);
    System.out.println("Date UTC: " + startDateUtc);

    String userEnteredTime = "01:30:00.000";
    String dateTimeString = startDate.toString() + "T"+userEnteredTime+offSetOperator+offSetInHHMM;
    System.out.println("Date Time String : " + dateTimeString);
    System.out.println(new LocalTime(
        ISODateTimeFormat.dateTime().withZone(DateTimeZone.forID(zone)).parseDateTime(dateTimeString)));
  }

}
