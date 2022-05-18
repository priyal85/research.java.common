package com.test;

import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

public class CommonTests
{
  static A theA;

  public static void main(String[] args) throws InterruptedException
  {
    Set<String > s= Sets.newHashSet(null,"T",null);
    
    System.out.println(s.stream().map(String::valueOf).collect(Collectors.joining(",")));
    
//    Map< String, A> hMap = new WeakHashMap<>();
//    A obj = new A("A ");
//    hMap.put( " Apple ",obj);
//    System.out.println(hMap);
//    obj = null;
//    System.gc();
//    Thread.sleep(5000);
//    System.out.println(hMap);
    
    doStuff();
    System.gc();
    Thread.sleep(15000);
    System.out.println(theA);
  }
  
  private static void doStuff() {
    B b = new B(new A("App"));
    theA=b.getA("A1");
  }

}

class A {
  private String a;
  
  public A(String a)
  {
    super();
    this.a = a;
  }
  public String toString() {
     return a;
  }
  public void finalize() {
     System.out.println("Finalize method of A");
  }
  
}

class B{
  private Map< String, A> hMap = new WeakHashMap<>();
  public B(A ...as)
  {
    for (A a : as)
    {
      hMap.put("A"+(hMap.size()+1), a);
    }
  }
  
  public A getA(String whichA)
  {
    return hMap.get(whichA);
  }
  public void finalize() {
    System.out.println("Finalize method of B");
  }
  
  @Override
  public String toString()
  {
    return hMap.toString();
  }
}
