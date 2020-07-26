package dataStructure.queue.impl;

import java.util.Stack;

import dataStructure.queue.impl.exceptions.QueueEmptyException;
import dataStructure.queue.impl.exceptions.QueueFullException;

public class StackQueue<T> implements Queue<T> {
  Stack<T> s1 = new Stack<>();
  Stack<T> s2 = new Stack<>();

  public StackQueue() {}

  public void enqueue(T o) throws QueueFullException {
    while(!s1.empty()) {
      s2.add(s1.pop());
    }
    s1.add(o);
    while (!s2.empty()) {
      s1.add(s2.pop());
    }
  }

  public T dequeue() throws QueueEmptyException {
    if (empty()) {
      throw new QueueEmptyException();
    }

    return s1.pop();
  }

  public T front() throws QueueEmptyException {
    if (empty()) {
      throw new QueueEmptyException();
    }

    return s1.peek();
  }

  public int size() {
    return s1.size();
  }

  public boolean empty() {
    return s1.empty();
  }

  public static void main(String[] args) {
    StackQueue<Integer> q = new StackQueue<Integer>();
    try {
      q.enqueue(1);
      q.dequeue();
      boolean f = q.empty();
  
      System.out.println(f);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

/**
// Stack implementation
// Use 2 stacks to implement

Algorithm enqueue(o)
  while !s1.empty() do
    s2.push(s1.pop())
  s1.push(o)
  while !s2.empty() do
    s1.push(s2.pop())

Algorithm dequeue()
  return s1.pop()

Algorithm front()
  return s1.top()

Algorithm empty()
  return s1.empty()

Algorithm size()
  return s1.size()
*/