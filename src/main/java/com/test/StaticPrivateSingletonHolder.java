package com.test;

public class StaticPrivateSingletonHolder
{
  private static final class SecretServiceInitializer
  {
    private static final Object INSTANCE = initialize();

    private static Object initialize()
    {
      throw new IllegalStateException("test");
    }

    private SecretServiceInitializer()
    {
    }
  }

  public static Object getObject()
  {
    return SecretServiceInitializer.INSTANCE;
  }

  public static void main(String[] args)
  {
    System.out.println("Main 1.0");
    try
    {
      getObject();
    }
    catch (Throwable e)
    {
      e.printStackTrace(); // Shows the cause
    }

    System.out.println("Main 1.1");

    try
    {
      getObject();
    }
    catch (Throwable e)
    {
      e.printStackTrace(); // java.lang.NoClassDefFoundError: Could not initialize class Main$SecretServiceInitializer only
    }
    System.out.println("Main 1.2");
  }
}
