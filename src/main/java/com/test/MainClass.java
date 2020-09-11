package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainClass
{

  public static void main(String[] args)
  {
    int size = 5;
    int from = 0;
    int to = 0;
    List<Long> list = new ArrayList<Long>(Arrays.asList(1L,2l,3l,4l,5l,6l,7l,8l,9l,10l,11l));
    while (to < list.size())
    {
      from = to;
      to = to + size;
      System.out.println( list.subList(from, to>list.size()?list.size():to));
    }
//    System.out.println(list.subList(from, to));
//    from = to;
//    to = to + size;
//    System.out.println( list.subList(from, to));
   

  }

}
