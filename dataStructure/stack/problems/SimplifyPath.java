package dataStructure.stack.problems;

import java.util.Stack;

/**
Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the canonical path.

In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double period .. moves the directory up a level.

Note that the returned canonical path must always begin with a slash /, and there must be only a single slash / between two directory names. The last directory name (if it exists) must not end with a trailing /. Also, the canonical path must be the shortest string representing the absolute path.

Example 1:
Input: "/home/"
Output: "/home"
Explanation: Note that there is no trailing slash after the last directory name.

Example 2:
Input: "/../"
Output: "/"
Explanation: Going one level up from the root directory is a no-op, as the root level is the highest level you can go.

Example 3:
Input: "/home//foo/"
Output: "/home/foo"
Explanation: In the canonical path, multiple consecutive slashes are replaced by a single one.

Example 4:
Input: "/a/./b/../../c/"
Output: "/c"

Example 5:
Input: "/a/../../b/../c//.//"
Output: "/c"

Example 6:
Input: "/a//b////c/d//././/.."
Output: "/a/b/c"

*/
public class SimplifyPath {
  static final char SLASH = '/';
  static final char DOT = '.';
  static StringBuilder DOTDOT = new StringBuilder("..");

  public String simplifyPath(String path) {
    Stack<String> stack = new Stack<>();
    StringBuilder buider = new StringBuilder();
    for (int i = 0; i < path.length(); i++) {
      char c = path.charAt(i);

      if (SLASH == c) {
        if (buider.compareTo(DOTDOT) == 0) {
          if (!stack.isEmpty()) {
            stack.pop();
          }
        } else if ((buider.length() > 0 && buider.charAt(0) != DOT) || (buider.length() > 1 && buider.charAt(0) == DOT)) {
          stack.add(buider.toString());
        }
        buider = buider.delete(0, buider.length());
      } else {
        buider.append(c);
      }
    }

    if (buider.compareTo(DOTDOT) == 0) {
      if (!stack.isEmpty()) {
        stack.pop();
      }
    } else if ((buider.length() > 0 && buider.charAt(0) != DOT) || (buider.length() > 1 && buider.charAt(0) == DOT)) {
      stack.add(buider.toString());
    }
    buider = buider.delete(0, buider.length());

    while (!stack.isEmpty()) {
      buider.insert(0, '/');
      buider.insert(1, stack.pop());
    }

    return buider.length() == 0 ? "/" : buider.toString();
  }

  public static void main(String[] args) {
    String input = "/a//b////c/d//./...//..";
    String output = new SimplifyPath().simplifyPath(input);
    System.out.println(output);
  }
}