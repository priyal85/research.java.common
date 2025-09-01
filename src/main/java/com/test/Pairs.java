package com.test;

public class Pairs
{

  public static void main(String[] args)
  {
    // TODO Auto-generated method stub
    int[] list= {1, 2, 3, 4, 5};
    int sum = 5;
    findPairs(list, sum);
  }
  public static void findPairs(int[] list, int sum)
  {
    int count = 0;
    for (int i = 0; i < list.length; i++)
    {
      for (int j = i + 1; j < list.length; j++)
      {
        if (list[i] + list[j] == sum)
        {
          count++;
          System.out.println("Pair found: (" + list[i] + ", " + list[j] + ")");
        }
      }
    }
    System.out.println("Total pairs found: " + count);
  }

  public static void findPairsImproved(int[] list, int sum) {
    java.util.HashSet<Integer> seen = new java.util.HashSet<>();
    int count = 0;
    for (int num : list) {
      int complement = sum - num;
      if (seen.contains(complement)) {
        count++;
        System.out.println("Pair found: (" + complement + ", " + num + ")");
      }
      seen.add(num);
    }
    System.out.println("Total pairs found: " + count);
  }
}
