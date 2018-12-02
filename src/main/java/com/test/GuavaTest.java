package com.test;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

public class GuavaTest
{
public static void main(String[] args)
{
  Multimap< Integer, String> multiMap = HashMultimap.create();
  String[] array = new String[0];
  Arrays.stream(array ).collect(Collectors.toSet());
}
}
