package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMain
{
  

  public static void main(String[] args)
  {
    Logger logger = LoggerFactory.getLogger(TestMain.class);
    logger.info("Hello {}", "Nimal");
    logger.info("Hello {} %s {}", "Nimal");
   Locale lc = Locale.getDefault();
   System.out.println(lc.getLanguage());
   TEst.A.valP.getDeclaringClass();
   System.out.println(!(true ^ true));
   System.out.println(!(true ^ false));
   System.out.println(!(false ^ true));
   System.out.println(!(false ^ false));
   System.out.println(new String[] {"Hello"});
   int x =1;
   //x++;
  // x=x+1;
 //  x+=1;
 //  x=+1;
   double d =-1.999d;
   System.out.println("Moduler d :" +d%1);
   Collections.singleton(1);
   System.out.println(x=+1);
   System.out.println(x);
   System.out.println(x+=1);
   System.out.println(x);
   List<A> listOfAs = new ArrayList<>();
   int max = listOfAs.stream().map(A::getSortingOrder).mapToInt(Integer::intValue).max().orElse(0);
   listOfAs.stream().map(A::getSortingOrder).max(Comparator.naturalOrder()).orElse(0);
   
   List<A> asList = new ArrayList<>(Arrays.asList(new A(0),new A(1)));
   for (A a : asList)
  {
    System.out.println(a.sortingOrder);
    if (a.sortingOrder==1)
    {
      asList.add(new A(2));
    }
    
    while (true)
    {
     
      
    }
  }
   
  }
  
  enum TEst{
    A(Test2.P),B(Test2.T);
    private String val;
    private Enum valP;
    private <E extends Enum<E>> TEst(final Enum<E>  valP)
    {
      this.valP = valP;
    }
    
    private enum Test2{
      P,T
    }
    
   
    
  }
  
 static class A{
   
   public A(int sortingOrder)
  {
    super();
    this.sortingOrder = sortingOrder;
  }

  int sortingOrder;

  public int getSortingOrder()
  {
    return sortingOrder;
  }

  public void setSortingOrder(int sortingOrder)
  {
    this.sortingOrder = sortingOrder;
  }
   
   
 }
 

}

