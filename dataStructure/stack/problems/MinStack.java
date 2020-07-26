package dataStructure.stack.problems;

import java.util.Stack;

/**

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.

Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2
 

Constraints:
  Methods pop, top and getMin operations will always be called on non-empty stacks.

*/

public class MinStack {
  
  Stack<Node> stack = new Stack<>();
  class Node {
    int data;
    int min;
    Node(int data, int min) {
      this.data = data;
      this.min = min;
    }
  }
  
  public MinStack() { }

  public void push(int x) {
    Node node = new Node(x, stack.isEmpty() ? x : (x < stack.peek().min ? x : stack.peek().min));
    stack.push(node);
  }

  public void pop() {
    stack.pop();
  }

  public int top() {
    Node node = stack.peek();
    return node.data;
  }

  public int getMin() {
    Node node = stack.peek();
    return node.min;
  }
}