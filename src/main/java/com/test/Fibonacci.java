package com.test;

import com.google.common.base.Joiner;
import com.google.common.primitives.Ints;

public class Fibonacci
{

  public static void main(String[] args)
  {
   fibonacci(10);
   fibonacci2(10);

  }
  
  public static void fibonacci(int n) {
    
    int array[] = new int[n];
    
    for (int i = 0; i < n; i++)
    {
      if (i<2) {
        array[i]=i;
      }else {
        array[i]=array[i-1]+array[i-2];
      }
      System.out.println(array[i]);
    }
    System.out.println(Joiner.on(",").join(Ints.asList(array)));
  }
  
 public static void fibonacci2(int n) {
    
    int previous=0;
    int nthFibonacci=0;
    
    for (int i = 0; i < n; i++)
    {
      if (i<2) {
        nthFibonacci=i;
      }else {
        int temp =nthFibonacci+previous;
        previous=nthFibonacci;
        nthFibonacci = temp;
      }
      System.out.println(nthFibonacci);
    }
  }

}
