package dataStructure.stack.problems;

import java.util.Stack;

/**
Given a string which contains only lowercase letters, remove duplicate letters so that every letter appears once and only once.
You must make sure your result is the smallest in lexicographical order among all possible results. 

Example 1:
Input: "bcabc"
Output: "abc"

Example 2:
Input: "cbacdcbc"
Output: "acdb"

Note: This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
*/
public class RemoveDuplicateLetters {
  public String removeDuplicateLetters(String s) {
    int[] noVisited = new int[26], lastIndexes = new int[26];
    for (int i = 0; i < s.length(); i++) {
      int c = s.charAt(i) - 'a';
      lastIndexes[c] = i;
    }

    Stack<Integer> stack = new Stack<>();

    for (int i = 0; i < s.length(); i++) {
      int c = s.charAt(i) - 'a';
      if (noVisited[c]++ > 0) {
        continue;
      }

      while (!stack.isEmpty() && stack.peek() > c && i < lastIndexes[stack.peek()]) {
        noVisited[stack.pop()] = 0;
      }

      stack.push(c);
    }

    StringBuilder builder = new StringBuilder();
    for (Integer c : stack) {
      builder.append((char) ('a' + c));
    }
    return builder.toString();
  }

  public static void main(String[] args) {
    String input = "cbacdcbc";
    String output = new RemoveDuplicateLetters().removeDuplicateLetters(input);
    System.out.println(output);
  }
}