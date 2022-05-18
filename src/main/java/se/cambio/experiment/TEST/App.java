package se.cambio.experiment.TEST;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * Hello world!
 *
 */
public class App 
{
  public static void main(String[] args)
  {
    System.out.println("Hello World!");
    List<A> as = new ArrayList<A>();
    as.add(new A());
    List<Object> objs = (List) as;
    for (Object object : objs)
    {
      System.out.println(object);
    }
    Function mapper;
    objs.stream().map(o -> o.toString());

    EnumSet<Status> statuses = EnumSet.noneOf(Status.class);
    statuses.add(Status.ACTIVE);
    statuses.add(Status.DONE);

    System.out.println(getFirstNoneRepeatingChar("eleven"));
    System.out.println(getFirstNoneRepeatingChar("Anna"));
    System.out.println(getFirstNoneRepeatingChar("level"));
  }

  static Character getFirstNoneRepeatingChar(String input)
  {
    Set<Character> noneRepeats = new LinkedHashSet<Character>();
    char[] charArray = input.toLowerCase().toCharArray();
    for (char c : charArray)
    {
      if (noneRepeats.contains(c))
      {
        noneRepeats.remove(c);
      }
      else
      {
        noneRepeats.add(c);
      }
    }
    Iterator<Character> iterator = noneRepeats.iterator();
    return iterator.hasNext() ? iterator.next() : null;
  }
}


class A{
  
}

enum Status{
  ACTIVE,DONE
}
