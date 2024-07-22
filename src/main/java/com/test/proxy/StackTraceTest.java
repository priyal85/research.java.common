package com.test.proxy;

public class StackTraceTest
{
  public static void main(String[] args)
  {
  methodA();
  }

  static void methodA(){
    methodB();
  }

  static void methodB(){

    StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

    // Print the stack trace elements (optional)
    for (StackTraceElement element : stackTraceElements) {
      System.out.println("\tat "+element.toString());
    }
  }
}
