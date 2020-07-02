package com.test;

public class ThreadTest2
{

  public static void main(String[] args)
  {
    ValReader2 reader = new ValReader2();

    Thread t1 = new Thread(reader::printVals, "T1");
    Thread t2 = new Thread(reader::printVals, "T2");
    Thread t3 = new Thread(reader::printVals, "T3");

    t2.start();
    t3.start();
    t1.start();
  }

}
