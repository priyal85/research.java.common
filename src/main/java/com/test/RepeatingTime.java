package com.test;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

public class RepeatingTime
{
  private LocalDate date;

  private LocalTime time;

  public RepeatingTime(LocalDate date, LocalTime time)
  {
    this.date = date;
    this.time = time;
  }

  public LocalDate getDate()
  {
    return date;
  }

  public void setDate(LocalDate date)
  {
    this.date = date;
  }

  public LocalTime getTime()
  {
    return time;
  }

  public void setTime(LocalTime time)
  {
    this.time = time;
  }
}