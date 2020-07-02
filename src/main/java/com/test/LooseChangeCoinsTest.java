package com.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class LooseChangeCoinsTest
{
  private static final int coinVals[] = { 25, 10, 5, 1 };

  private static final TreeMap<Integer, String> coinNameMap = new TreeMap<>();

  static
  {
    coinNameMap.put(25, "quater");
    coinNameMap.put(10, "dime");
    coinNameMap.put(5, "nickle");
    coinNameMap.put(1, "pennie");
  }

  public static void main(String[] args)
  {
    for (Entry<String, Integer> entry : looseChange(50).entrySet())
    {
      System.out.println(entry.getKey() + " " + entry.getValue());
    }
  }

  private static Map<String, Integer> looseChange(int amount)
  {
    Map<String, Integer> looseChangeMap = new TreeMap<>();
    for (Integer coin : coinNameMap.descendingMap().keySet())
    {
      int factor = amount / coin;
      if (factor > 0)
      {
        looseChangeMap.put(coinNameMap.get(coin), factor);

      }
      int remainder = amount % coin;
      if (remainder > 0)
      {
        amount = remainder;
      }
      else
      {
        break;
      }
    }
    // int coinIndex = 0;
    // populateLooseChange(amount, looseChange, coinIndex);
    return looseChangeMap;
  }

  private static void populateLooseChange(int amount, Map<String, Integer> looseChangeMap, int coinIndex)
  {
    int coin = coinVals[coinIndex];
    int factor = amount / coin;
    if (factor > 0)
    {
      looseChangeMap.put(coinNameMap.get(coin), factor);

    }
    int remainder = amount % coin;
    if (remainder > 0)
    {
      populateLooseChange(remainder, looseChangeMap, ++coinIndex);
    }
  }

}
