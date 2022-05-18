package com.test.interviews;


public class Test3
{
 public static void main(String[] args)
{
  for (int i = 1; i < 5 ; i++)
  {
    for (int j = 0; j < ((i*2)-1); j++)
    {
      System.out.print("*");
    }
    System.out.println();
  }
  
  for (int i = 0; i < 5 ; i++)
  {
    for (int j = 0; j < 2*i+ 1; j++)
    {
      System.out.print("*");
    }
    System.out.println();
  }
}
 
 public void printArea(Shape[] shapes)
{
  for (Shape shape : shapes)
  {
    System.out.println(shape.area());
  }

}
 
 private static void m() {}
 
}
