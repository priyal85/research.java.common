package com.test;

public class ThreadTest
{
  public static void main(String[] args)
  {

    Singleton instance = Singleton.getInstance();

    Thread t1 = new Thread(() -> {
      instance.addValue("Hello 1");
      sleepForMilis(200);
      instance.addValue("Hello 2");
      sleepForMilis(500);
      instance.addValue("Hello 3");
      sleepForMilis(1000);
      instance.addValue("Hello 4");
      sleepForMilis(1500);
      instance.printValues();
    }, "T1");

    Thread t2 = new Thread(() -> {
      instance.addValue("Val 1");
      sleepForMilis(100);
      instance.addValue("Val 2");
      sleepForMilis(100);
      instance.addValue("val 3");
      sleepForMilis(100);
      instance.addValue("Val 4");
      sleepForMilis(100);
      instance.printValues();
    }, "T2");
    t1.start();
    t2.start();
    instance.printValues();

  }

  private static void sleepForMilis(long milis)
  {
    try
    {
      Thread.sleep(milis);
    }
    catch (InterruptedException e)
    {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
