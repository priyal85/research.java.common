package com.test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.base.Strings;

public class DnaStrand
{
  public static String makeComplement(String dna)
  {
    if (Strings.isNullOrEmpty(dna))
    {
      return dna;
    }
    Map<Character, Character> charMap = new HashMap<Character, Character>();
    charMap.put('A', 'T');
    charMap.put('T', 'A');
    charMap.put('C', 'G');
    charMap.put('G', 'C');

    return dna.chars().mapToObj(c -> (char) c)
        .map((c) -> charMap.containsKey(c) ? charMap.get(c).toString() : c.toString()).collect(Collectors.joining());

  }

  public static void main(String[] args)
  {
    System.out.println(DnaStrand.makeComplement("ATTGA"));
    System.out.println(DnaStrand.makeComplement("ATBTGA"));
  }
}