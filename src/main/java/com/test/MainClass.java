package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainClass
{

  public static void main(String[] args)
  {

    int size = 5;
    int from = 0;
    int to = 0;
    List<Long> list = new ArrayList<Long>(Arrays.asList(1L, 2l, 3l, 4l, 5l, 6l, 7l, 8l, 9l, 10l, 11l));
    while (to < list.size())
    {
      from = to;
      to = to + size;
      System.out.println(list.subList(from, to > list.size() ? list.size() : to));
    }
    // System.out.println(list.subList(from, to));
    // from = to;
    // to = to + size;
    // System.out.println( list.subList(from, to));
    Map<String, Long> activityTypeCacheMap = new HashMap<>();
    ActivityPluginTypeManager activityPluginTypeManager = new ActivityPluginTypeManager();
    activityTypeCacheMap.computeIfAbsent(ActivityTypeConstants.CATEGORY, k -> activityPluginTypeManager
        .getActivityCategoryType(k));
    Map<String, Long> activityTypeCacheMap2 = new HashMap<>(activityTypeCacheMap);
    testFinally();
    varArgsMethod("212", new HashSet<>());
    System.out.println(Arrays.asList(4582,3548,7894));
  }
  
  static void varArgsMethod(String patientId, Set<String> handledActivityTypeIds, String ...units) {}
  
  static class ActivityPluginTypeManager {
    
    Long getActivityCategoryType(String s) {
      return 4L;
    }
  }
  
  static class ActivityTypeConstants{
    static final String CATEGORY ="";
  }

  static void testFinally()
  {
    try
    {
      return;
    }
    finally
    {
      System.out.println("Finaly reached");
    }
  }

}
