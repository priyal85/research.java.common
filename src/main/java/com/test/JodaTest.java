package com.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.joda.time.Period;
import org.joda.time.format.ISODateTimeFormat;
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

    List<LocalTime> times = new ArrayList<>();
    List<RepeatingTime> dateTimeList = new ArrayList<>();
    int repetitionPerDay = 3;
    DateTimeZone defaultZone = DateTimeZone.getDefault();
    DateTime startDateTime = new DateTime(2019, 10, 20, 0, 0, 0, defaultZone);
    DateTime endDateTime = new DateTime(2019, 10, 21, 23, 59, 0, defaultZone);
    System.out.println(startDateTime.toLocalDate().equals(endDateTime.toLocalDate()));
    System.out.println(Days.daysBetween(startDateTime.toLocalDate(), endDateTime.toLocalDate()).getDays());
    LocalTime parsedTime = LocalTime.parse("T14:45:15.000Z", ISODateTimeFormat.tTime());
    System.out.println(parsedTime.getMillisOfDay());
    
    times.add(LocalTime.parse("T14:45:15.000Z", ISODateTimeFormat.tTime()));
    times.add(LocalTime.parse("T03:45:15.000Z", ISODateTimeFormat.tTime()));

    LocalDate startDate = startDateTime.toLocalDate();
    // Adding 1 since  we should consider the occurrences within same date as well
    int daysBetween = Days.daysBetween(startDateTime.toLocalDate(), endDateTime.toLocalDate()).getDays() + 1; 
    LocalDate targetDate = startDate;

    Collections.sort(times);
    for (int i = 0; i < daysBetween; i++)
    {
      for (int j = 0; j < repetitionPerDay; j++)
      {
        Optional<LocalTime> targetTime = Optional.ofNullable(times.size() > j ? times.get(j) : null);
        long targetDateMilisAsStratOfDay = targetDate.toDateTimeAtStartOfDay().getMillis();
        long milisOfTargetDateTime = targetTime.map(m -> m.getMillisOfDay() + targetDateMilisAsStratOfDay)
            .orElse(targetDateMilisAsStratOfDay);
        LocalDateTime targetDateTime = new LocalDateTime(milisOfTargetDateTime, startDateTime.getZone());
        boolean isTargetDateTimeGreatThanOrEqualsStartDateTime = targetDateTime.isAfter(startDateTime.toLocalDateTime())
                    || targetDateTime.equals(startDateTime.toLocalDateTime());
        boolean isTargetDateTimeLessThanOrEqualsEndTime = targetDateTime.isBefore(endDateTime.toLocalDateTime())
            || targetDateTime.equals(endDateTime.toLocalDateTime());
        if ((!targetTime.isPresent() || isTargetDateTimeGreatThanOrEqualsStartDateTime) && isTargetDateTimeLessThanOrEqualsEndTime)
        {
          dateTimeList.add(new RepeatingTime(targetDate, targetTime.orElse(null)));
        }
      }
      targetDate = targetDate.plusDays(1);
    }
    System.out.println(dateTimeList.size());
    for (RepeatingTime repeatingTime : dateTimeList)
    {
      System.out.println("Target date :" +repeatingTime.getDate()+" time :" +repeatingTime.getTime());
    }
  }

}
