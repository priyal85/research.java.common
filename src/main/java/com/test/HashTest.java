package com.test;

import java.util.HashMap;

public class HashTest
{
  public static void main(String[] args)
  {
    Father father1 = new Father("John", 40);
    Father father2 = new Father("Martin", 45);

    Child child1 = new Child("Alice", 10, father1);
    Child child2 = new Child("Jane", 12, father2);

    HashMap<Father,Child> map = new HashMap<>();

    map.put(father1, child1);
    map.put(father2, child2);

    System.out.println("Before: " + map.get(father1));

    father1.setAge(50); // Change the age of father1
    System.out.println("After: " + map.get(father1));


//    System.out.println("Father1 equals Father2: " + father1.equals(father2)); // true
//    System.out.println("Child1 equals Child2: " + child1.equals(child2)); // true
//
//    System.out.println("Father1 hashCode: " + father1.hashCode());
//    System.out.println("Father2 hashCode: " + father2.hashCode());
//
//    System.out.println("Child1 hashCode: " + child1.hashCode());
//    System.out.println("Child2 hashCode: " + child2.hashCode());
  }
}

class Father
{
  private  String name;
  private int age;

  public Father(String name, int age)
  {
    this.name = name;
    this.age = age;
  }

  public void setAge(int age)
  {
    this.age = age;
  }

  public void setName(String name)
  {
    this.name = name;
  }

public String getName()
  {
    return name;
  }

  public int getAge()
  {
    return age;
  }

  // override equals and hashCode methods
  @Override
  public boolean equals(Object obj)
  {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Father father = (Father) obj;
    return age == father.age && name.equals(father.name);
  }

  @Override
  public int hashCode()
  {
    int result = name.hashCode();
    result = 31 * result + age;
    return result;
  }

  @Override
  public String toString()
  {
    return "Father{" +
           "name='" + name + '\'' +
           ", age=" + age +
           '}';
  }
}

class Child {
  private String name;
  private int age;
  private Father father;

  public Child(String name, int age, Father father) {
    this.name = name;
    this.age = age;
    this.father = father;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public void setFather(Father father) {
    this.father = father;
  }

  public String getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public Father getFather() {
    return father;
  }

  // override equals and hashCode methods
  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Child child = (Child) obj;
    return age == child.age && name.equals(child.name) && father.equals(child.father);
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + age;
    result = 31 * result + father.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Child{" +
           "name='" + name + '\'' +
           ", age=" + age +
           ", father=" + father +
           '}';
  }
}
