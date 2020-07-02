package se.cambio.experiment.design;

import java.util.Date;

public class Person implements Comparable
{
  public String name;

  public Date dateOfBirth;

  public Person()
  {
  }

  /**
   * returns the age of the person
   */
  public int calculate()
  {
    int i = new Date().getYear() - dateOfBirth.getYear();
    log(Integer.toString(i));
    return i;
  }
  
  public boolean equals(Person obj)
  {
    return name.equals(obj.name) && dateOfBirth.equals(obj.dateOfBirth);
  }

  @Override
  public int compareTo(Object o)
  {
    return dateOfBirth.compareTo(((Person)o).dateOfBirth);
  }

  @Override
  public String toString()
  {
    return super.toString();
  }

  private void log(String msg)
  {
    System.out.println(msg);
  }
}
