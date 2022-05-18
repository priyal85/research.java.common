package se.cambio.experiment.TEST;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class StreamsTest
{

  public static void main(String[] args)
  {
    Map<Long, Map<Long,String>> complexMap = new HashMap<>();
    Map<Long,String> map1 = new HashMap<>();
    map1.put(100L, "A100");
    map1.put(101L, "A101");
    map1.put(102L, "A102");
    map1.put(103L, "A103");
    complexMap.put(1L, map1);
    
    Map<Long,String> map2 = new HashMap<>();
    map2.put(200L, "B100");
    map2.put(201L, "B101");
    map2.put(202L, "B102");
    map2.put(203L, "B103");
    complexMap.put(2L, map2);
    
    
    Map<Long, String> collect = complexMap.entrySet().stream().flatMap(ent -> ent.getValue().entrySet().stream()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    
    for (Entry<Long, String> entry : collect.entrySet())
    {
      System.out.println(entry.getKey()+" : " +entry.getValue());
    }
    
    String[] s= {"xYz","123","Abc"};
   // Arrays.stream(identifiers).filter(identifier -> identifier.type == type).sorted(comparator).map(identifier -> identifier.identifier).collect(Collectors.toList());
    
  }

}
