package dataStructure.stack.problems;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class ValidParentheses {
  private static final Map<Character, Character> matchingParenMap = new HashMap<>();
  private static final Set<Character> openningParenSet = new HashSet<>();

  static {
    matchingParenMap.put(')', '(');
    matchingParenMap.put(']', '[');
    matchingParenMap.put('}', '{');
    openningParenSet.addAll(matchingParenMap.values());
  }

  public boolean isValid(String s) {
    try {
      Stack<Character> parenStack = new Stack<>();
      for (int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);

        if (openningParenSet.contains(c)) {
          parenStack.push(c);
        }

        if (matchingParenMap.containsKey(c)) {
          Character lastParen = parenStack.pop();
          if (lastParen != matchingParenMap.get(c)) {
            return false;
          }
        }
      }
      return parenStack.isEmpty();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  public static void main(String[] args) {
    String input = "()";
    boolean output = new ValidParentheses().isValid(input);
    System.out.println(output);
  }
}