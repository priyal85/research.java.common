package com.test;

import java.util.regex.Pattern;

public class PatternTest
{

  public static void main(String[] args)
  {
    System.out.println(Pattern.matches("PT[\\d]+M", "PTS23M"));
    System.out.println(Pattern.matches("PT[\\d]+H", "PT23H"));
    System.out.println(Pattern.matches("P[\\d]+D", "P03D"));
    System.out.println(Pattern.matches("P[\\d]+W", "P43W"));
    System.out.println(Pattern.matches("P[\\d]+Y", "P3Y"));
  }

}
