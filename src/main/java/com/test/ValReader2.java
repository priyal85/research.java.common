package com.test;

import java.util.ArrayList;
import java.util.List;

public class ValReader2
{
  private List<String> vals;

  private volatile boolean initialized = false;

  public void printVals()
  {
    System.out.println(Thread.currentThread() + " entered printVals()");
    List<String> values = getValues();
    System.out.println(Thread.currentThread() + " started printing");
    for (String val : values)
    {
      System.out.println(Thread.currentThread().getName() + " " + val);
    }
  }

  private List<String> getValues()
  {
    System.out.println(Thread.currentThread() + " entered getValues()");

    if (!initialized)
    {

      System.out.println(Thread.currentThread() + " entered if");
      synchronized (this)
      {

        System.out.println(Thread.currentThread() + " entered sync block");
        if (vals == null)
        {
          System.out.println(Thread.currentThread() + " entered inner if");

          vals = new ArrayList<>();
          for (int i = 1; i <= 15000; i++)
          {
            vals.add(Integer.toString(i));
          }
          initialized = true;
        }
      }
    }

    System.out.println(Thread.currentThread() + " exist getValues()");
    return vals;
  }
}
