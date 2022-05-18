package com.test.adventOfCode.y2015;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class Puzzle3
{

  public static void main(String[] args) throws URISyntaxException
  {
    String file = "ac/2015/input3.txt";
    Path path = Paths.get(Puzzle3.class.getClassLoader().getResource(file).toURI());
    try (Stream<String> stream = Files.lines(path))
    {
      Long totalWrappingPaperArea = stream.map(l -> l.split("x")).map(a -> getWrappingPaperSquareFeet(Integer.parseInt(a[0]), Integer.parseInt(a[1]), Integer.parseInt(a[2]))).reduce(0L,(l,y)->l+y);
      System.out.println(totalWrappingPaperArea);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    
    try (Stream<String> stream = Files.lines(path))
    {
      Long totalRibbonLength = stream.map(l -> l.split("x")).map(a -> getRibbonLength(Integer.parseInt(a[0]), Integer.parseInt(a[1]), Integer.parseInt(a[2]))).reduce(0L,(l,y)->l+y);
      System.out.println(totalRibbonLength);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

  }

  private static long getWrappingPaperSquareFeet(int l, int w, int h)
  {
    int[] sides = getAsSortedArray(l, w, h);
    long smallestArea = (long) sides[0] * sides[1];
    return smallestArea + 2 * (smallestArea + sides[0] * sides[2] + sides[1] * sides[2]);
  }

  private static long getRibbonLength(int l, int w, int h)
  {
    int[] sides = getAsSortedArray(l, w, h);
    return 2*(sides[0] + sides[1]) +  (sides[0] * sides[1] * sides[2]);
  }
  private static int[] getAsSortedArray(int l, int w, int h)
  {
    int[] sides = { l, w, h };
    Arrays.sort(sides);
    return sides;
  }

}
