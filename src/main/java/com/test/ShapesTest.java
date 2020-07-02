package com.test;

import java.util.ArrayList;
import java.util.Collection;

public class ShapesTest
{

  public static void main(String[] args)
  {

   Collection<Shape> shapes = new ArrayList<>();
   
   printAreas(shapes);

  }
  
 static void printAreas(Collection<Shape> shapes) {
     for (Shape shape : shapes)
    {
      System.out.println("Area :"+ shape.area());
    }
  }

}

interface Shape{
  
  double area();
}