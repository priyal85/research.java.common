package com.test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class RomanNumber extends Number
{
  /**
   * 
   */
  private static final long serialVersionUID = -1679736423897404224L;

  private Integer value;

  private static Map<Integer, String> numeralMap = new LinkedHashMap<>();

  private static List<Integer> bases;

  static
  {
    numeralMap.put(1000, "M");
    numeralMap.put(500, "D");
    numeralMap.put(100, "C");
    numeralMap.put(50, "L");
    numeralMap.put(10, "X");
    numeralMap.put(5, "V");
    numeralMap.put(1, "I");
    bases = new ArrayList<Integer>(numeralMap.keySet());
  }

  public RomanNumber(Integer value)
  {
    super();
    this.value = value;
  }

  @Override
  public double doubleValue()
  {
    return value.doubleValue();
  }

  @Override
  public float floatValue()
  {
    return value.floatValue();
  }

  @Override
  public int intValue()
  {
    return value.intValue();
  }

  @Override
  public long longValue()
  {
    return value.longValue();
  }

  @Override
  public String toString()
  {
    return toRoman(value);
  }

  private String toRoman(Integer value)
  {
    StringBuilder romanVal = new StringBuilder("");
    int base = bases.get(0);
    buildStringReccursively(value, romanVal, base);
    System.out.println(romanVal);
    return romanVal.toString();
  }

  private void buildStringReccursively(Integer value, StringBuilder romanVal, Integer base)
  {
    int mod = value % base;
    int div = value / base;
    int baseIndex = bases.indexOf(base);
    Integer nextBase = (baseIndex + 1 < bases.size() ? bases.get(baseIndex + 1) : base);
    if (div > 0)
    {
      int nextMode = mod / nextBase;
      if (base == 1000 || (div < 4 && nextMode != 4))
      {

        for (int i = 0; i < div; i++)
        {
          romanVal.append(numeralMap.get(base));
        }
      }
      else if (div == 4 || nextMode == 4)
      {
        
        Integer pre_base =  base;
        if (base.toString().startsWith("5"))
        {
          pre_base = nextBase;
          mod = mod % nextBase;
        }
        Integer previousBase = bases.get(baseIndex - 1);
        romanVal.append(numeralMap.get(pre_base));
        romanVal.append(numeralMap.get(previousBase));
      }
    }
    if (mod > 0)
    {
      value = mod;
      base = nextBase;
      buildStringReccursively(value, romanVal, base);
    }
  }

}
