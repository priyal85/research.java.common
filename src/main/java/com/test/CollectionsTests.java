package com.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionsTests
{
  public static void main(String[] args)
  {
    List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12);
     
    for (int i = 0;i < list.size(); i+=5)
    {
      System.out.println(list.subList(i, Math.min(list.size() , i+5)));
    }
    
    Map <String, List<String>> map = new HashMap<>();
    map.entrySet().stream().filter(e -> e.getValue().stream().noneMatch(CollectionsTests.class::isInstance));
  }
}
