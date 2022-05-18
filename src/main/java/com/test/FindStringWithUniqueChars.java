package com.test;

import java.util.HashSet;
import java.util.Set;

public class FindStringWithUniqueChars
{
  public static void main(String[] args)
  {
    //findUniqe([ 'Aa', 'aaa', 'aaaaa', 'BbBb', 'Aaaa', 'AaAaAa', 'a' ]) === 'BbBb'
     //   findUniqe([ 'abc', 'acb', 'bac', 'foo', 'bca', 'cab', 'cba' ]) === 'foo'
  }
  
  private static String findUnique(String[] strings) {
     String unique ="";
     Set<Character> foundChars = new HashSet<>();
    for (String string : strings)
    {
      char[] charArray=string.toCharArray();
      for (char c : charArray)
      {
        
      }
     
    }
     return unique;
  }
}
