package com.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Joiner;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class CacheTest
{
 static int counter;
 static String [] items = {"item1","item2","item3","item4","item5","item6","item7","item8","item9","item10"};
  static LoadingCache<String, List<String>> toDosCache = CacheBuilder.newBuilder()
      .maximumSize(1000)
      .expireAfterWrite(10, TimeUnit.SECONDS)
      .build(
              new CacheLoader<String, List<String>>() {
                  public List<String> load(String id) {
                    System.out.println("Loading Cache");
                         return getNames();
                  }
              }
      );
  public static void main(String[] args) throws InterruptedException
  {
   while (true)
  {
   System.out.println(Joiner.on(',').join(toDosCache.getUnchecked("Items")));
    Thread.sleep(4000);
  }

  }
 static List<String> getNames(){
   List<String> selectedItems = new ArrayList<>();
   for (int i = 0; i <counter  &&i < items.length ; i++)
  {
     selectedItems.add(items[i]);
  }
   counter++;
   return selectedItems;
 }
}
