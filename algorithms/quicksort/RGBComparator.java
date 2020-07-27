package algorithms.quicksort;

import java.util.HashMap;
import java.util.Map;

public class RGBComparator implements Comparator<Character> {
  static Map<Character, Integer> map = new HashMap<>();
  static {
    map.put('R', 0);
    map.put('G', 1);
    map.put('B', 2);
  }
  @Override
  public boolean isLess(Character a, Character b) {
    return map.get(a) < map.get(b);
  }

  @Override
  public boolean isLessOrEqualTo(Character a, Character b) {
    return map.get(a) <= map.get(b);
  }

  @Override
  public boolean isEqualTo(Character a, Character b) {
    return map.get(a) == map.get(b);
  }

  @Override
  public boolean isGreater(Character a, Character b) {
    return map.get(a) > map.get(b);
  }

  @Override
  public boolean isGreaterOrEqualTo(Character a, Character b) {
    return map.get(a) >= map.get(b);
  }

  @Override
  public boolean isComparable(Character a) {
    return map.containsKey(a);
  }
  
}