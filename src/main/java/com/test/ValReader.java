package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ValReader
{
  private List<String> vals;

  private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

  public void printVals()
  {
    System.out.println(Thread.currentThread() + " entered printVals()");
    lock.readLock().lock();
    List<String> values;
    try
    {
      System.out.println(Thread.currentThread() + " entered try of printVals()");
      values = getValues();
    }
    finally
    {
      System.out.println(Thread.currentThread() + " entered finally of printVals()");
      lock.readLock().unlock();
    }
    System.out.println(Thread.currentThread() + " started printing");
    for (String val : values)
    {
      System.out.println(Thread.currentThread().getName() + " " + val);
    }
  }

  private List<String> getValues()
  {
    System.out.println(Thread.currentThread() + " entered getValues()");
    lock.readLock().unlock();
    lock.writeLock().lock();
    try
    {
      System.out.println(Thread.currentThread() + " entered try");
      if (vals == null)
      {
        System.out.println(Thread.currentThread() + " entered if");

        vals = new ArrayList<>();
        for (int i = 1; i <= 15000; i++)
        {
          vals.add(Integer.toString(i));
        }
      }
    }
    finally
    {
      System.out.println(Thread.currentThread() + " entered finally");
      lock.readLock().lock();
      lock.writeLock().unlock();
    }
    System.out.println(Thread.currentThread() + " exist getValues()");
    return vals;
  }
}
