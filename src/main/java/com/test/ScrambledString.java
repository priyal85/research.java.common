package com.test;

import java.util.Arrays;

public class ScrambledString
{
  public static void main(String[] args)
  {
    String str1 = "eat";
    String dic[] = { "tea", "ate", "bat", "eat", "tab", "at" };
    for (String s : dic)
    {
      if (str1.length() == s.length() && Arrays.equals(s.chars().sorted().toArray(), str1.chars().sorted().toArray()))
      {
        System.out.println(s);
      }

    }
  }
}
