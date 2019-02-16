package se.cambio.experiment.classloader;

import java.util.HashSet;

public class ClassLoaderExample
{

  public static void main(String[] args)
  {
   System.out.println(HashSet.class.getClassLoader()); // Bootstrap class loader. Returned null here.
   System.out.println(JustAnotherClass.class.getClassLoader());
   System.out.println(sun.net.spi.nameservice.dns.DNSNameService.class.getClassLoader()); // Extension class loader
   System.out.println(sun.net.spi.nameservice.dns.DNSNameServiceDescriptor.class.getClassLoader());
   System.out.println();

  }

}
