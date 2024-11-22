package com.test.proxy;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// Define a method-level annotation
@Retention(RetentionPolicy.RUNTIME)
@interface MethodAnnotation {}

// Interface that the original class implements, with an annotation on the method
interface MyInterface {
  @MethodAnnotation
  void doSomething();
}

// The original class implementing the interface
class OriginalClass implements MyInterface {
  @Override
  public void doSomething() {
    System.out.println("Doing something in the original class.");
  }
}

public class ProxyExample {
  public static void main(String[] args) throws NoSuchMethodException {
    MyInterface original = new OriginalClass();

    // Creating a proxy for the original object
    MyInterface proxyInstance = (MyInterface) Proxy.newProxyInstance(
      original.getClass().getClassLoader(),
      original.getClass().getInterfaces(),
      new InvocationHandler() {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
          System.out.println("Proxy invocation");
          return method.invoke(original, args);
        }
      }
    );

    // Checking for method-level annotation on the original method
    Method originalMethod = original.getClass().getMethod("doSomething");
    boolean originalMethodHasAnnotation = originalMethod.isAnnotationPresent(MethodAnnotation.class);
    System.out.println("Original method has MethodAnnotation: " + originalMethodHasAnnotation); // false

    // Checking for method-level annotation on the interface method (from the proxy)
    Method interfaceMethod = MyInterface.class.getMethod("doSomething");
    boolean interfaceMethodHasAnnotation = interfaceMethod.isAnnotationPresent(MethodAnnotation.class);
    System.out.println("Interface method has MethodAnnotation: " + interfaceMethodHasAnnotation); // true

    // Checking for method-level annotation on the proxy method
    Method proxyMethod = proxyInstance.getClass().getMethod("doSomething");
    boolean proxyMethodHasAnnotation = proxyMethod.isAnnotationPresent(MethodAnnotation.class);
    System.out.println("Proxy method has MethodAnnotation: " + proxyMethodHasAnnotation); // true

    proxyInstance.doSomething();
  }
}
