package com.test;

public class SingletonCompare
{
  public static void main(String[] args)
  {

  }

  private static  class Singleton1
  {
    private static Singleton1 instance;

    private Singleton1() {}

    public static Singleton1 getInstance()
    {
      if (instance == null)
      {
        synchronized (Singleton1.class){
          if (instance == null) // Double-checked locking
          {
            instance = new Singleton1();
          }
        }

      }
      return instance;
    }
  }

  private static class Singleton2
  {
    private static  Singleton2 instance;

    private Singleton2() {}

    public static Singleton2 getInstance()
    {
      Singleton2 localInstance = instance;
      if (localInstance == null){
        synchronized (Singleton2.class) {
          localInstance = instance; // Re-check after acquiring lock
          if (localInstance == null) {

            localInstance = instance = new Singleton2();
          }
        }
      }
      return localInstance;
    }
  }
}
