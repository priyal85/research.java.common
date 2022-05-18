package com.test;

public class Ambiguous {

  public static void main(String... args){
  then(bar());
    //  then(bar2());
  }

  public static <E extends Float> E bar() {
      return null;
  }
  
  public static <E extends Exception> E bar2() {
    return null;
}

  public static void then(Throwable actual) {
    System.out.println("Throwable was called");
  }

  public static void then(CharSequence actual) { 
    System.out.println("CharSequence was called");
  }

}