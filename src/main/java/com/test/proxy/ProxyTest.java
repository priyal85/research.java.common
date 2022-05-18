package com.test.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ProxyTest
{
  public static void main(String[] args) throws InstantiationException, IllegalAccessException
  {
    A a = new A();
    I proxy1 = getJDKProxy(a, I.class);

    proxy1.print();
    I proxy12 = getJDKProxy(proxy1, I.class);
    proxy12.print();


//    A proxy2 = getJavaAssistProxy(a);
//    proxy2.print();
//    proxy2.print2();
//    
    A proxy3 = getCGLibProxy(a);
    proxy3.print();
    proxy3.print2();
    
    A b = new B();
    A proxyB2 = getJavaAssistProxy(b);
    proxyB2.print();
    proxyB2.print2();
    
    A proxyB3 = getCGLibProxy(b);
    proxyB3.print();
    proxyB3.print2();
  }

  private static <T>  I getJDKProxy(Object instance, Class<T> interfaceType)
  {
    I proxy1 = (I) Proxy.newProxyInstance(A.class.getClassLoader(), new Class[] { interfaceType }, new InvocationHandler()
    {

      @Override
      public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
      {
        try
        {
          System.out.println("This is from proxy just before the method");
          return method.invoke(instance, args);
        }
        finally
        {
          System.out.println("This is from proxy just after the method");
        }
      }
    });
    return proxy1;
  }

  private static <T> T getJavaAssistProxy(T instance) throws InstantiationException, IllegalAccessException
  {
    ProxyFactory factory = new ProxyFactory();
    factory.setSuperclass(instance.getClass());
    Class aClass = factory.createClass();
    final T newInstance = (T) aClass.newInstance();
    MethodHandler methodHandler = new MethodHandler()
    {
      @Override
      public Object invoke(Object self, Method overridden, Method proceed, Object[] args) throws Throwable
      {
        try
        {
          System.out.println("This is from proxy2 just before the method");
          return overridden.invoke(instance, args);
        }
        finally
        {
          System.out.println("This is from proxy2 just after the method");
        }
      }
    };
    ((ProxyObject) newInstance).setHandler(methodHandler);
    return newInstance;
  }
  
  @SuppressWarnings("unchecked")
  private static <T> T getCGLibProxy(T instance)
  {
    T exampleProxy = (T) Enhancer.create(instance.getClass(), new MethodInterceptor() {
        @Override
        public Object intercept(Object object, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            try
            {
              System.out.println("This is from proxy3 just before the method");
              return method.invoke(instance, args);
            }
            finally
            {
              System.out.println("This is from proxy3 just after the method");
            }
        }
    });
    return exampleProxy;
  }
}

class A implements I
{
  public void print()
  {
    System.out.println("This is A");
  }

  public void print2()
  {
    System.out.println("This is A 2");
  }

}

final class B extends A{
  @Override
  public void print()
  {
    System.out.println("This is B");
  }
}
@FunctionalInterface
interface I
{
  void print();
  
  default void set(String s) {
    
  }
}