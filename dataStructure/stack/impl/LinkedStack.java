package dataStructure.stack.impl;

import dataStructure.stack.impl.exceptions.StackOverflowException;
import dataStructure.stack.impl.exceptions.StackUnderflowException;

public class LinkedStack<T> implements Stack<T> {
  final static int MAX = 100;
  private Element<T> top;
  private int size = 0;

  public void push(T x) throws StackOverflowException {
    if (size >= (MAX - 1)) {
      throw new StackOverflowException();
    }
    
    top = new Element<T>(x, top);
    size++;
  }

  public T pop() throws StackUnderflowException {
    if (size == 0) {
      throw new StackUnderflowException();
    }

    T data = top.getData();
    top = top.getNext();

    size--;

    return data;
  }

  public T top() throws StackUnderflowException {
    if (size == 0) {
      throw new StackUnderflowException();
    }
    
    T data = top.getData();

    return data;
  }

  public boolean empty() {
    return size == 0;
  }

  public boolean isFull() {
    return size >= MAX;
  }

  public int size() {
    return size;
  }

  public static void main(String[] args) {
    LinkedStack<Integer> s = new LinkedStack<Integer>();
    try {
      s.push(10); 
      s.push(20); 
      s.push(30); 
      System.out.println(s.pop() + " Popped from stack"); 
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

/*
Algorithm push(o)
  if size() = N then
    throw StackOverflowException
  t <- new Element(o, t)
  size <- size + 1

Algorithm pop()
  if empty() then
    throw StackUnderflowException
  e <- t.data
  t = t.next
  size <- size - 1
  return e

Algorithm top()
  if empty() then
    throw StackUnderflowException
  return t.data

Algorithm size()
  return size

Algorithm empty()
  return size = 0
*/