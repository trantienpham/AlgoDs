package dataStructure.stack.impl;

import dataStructure.stack.impl.exceptions.StackOverflowException;
import dataStructure.stack.impl.exceptions.StackUnderflowException;

public class ArrayStack<T> implements Stack<T> {
  final static int MAX = 100;
  int top;
  Position<T> a[] = new Element[MAX]; // Maximum size of Stack

  public ArrayStack() {
    top = -1;
  }

  public void push(T x) throws StackOverflowException {
    if (top >= (MAX - 1)) {
      throw new StackOverflowException();
    }
    a[++top] = new Element<T>(x);
  }

  public T pop() throws StackUnderflowException {
    if (top < 0) {
      throw new StackUnderflowException();
    }

    T x = a[top].element();
    a[top] = null;
    top--;
    return x;
  }

  public T top() throws StackUnderflowException {
    if (top < 0) {
      throw new StackUnderflowException();
    }

    T x = a[top].element();
    return x;
  }

  public boolean empty() {
    return (top < 0);
  }

  public int size() {
    if (empty()) return 0;
    return this.top;
  }

  public static void main(String[] args) {
    ArrayStack<Integer> s = new ArrayStack<Integer>();
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
  t <- t + 1
  S[t] = o

Algorithm pop()
  if empty() then
    throw StackUnderflowException
  e <- S[t]
  S[t] = null
  t <- t - 1
  return e

Algorithm top()
  if empty() then
    throw StackUnderflowException
  e <- S[t]
  return e

Algorithm size()
  return t

Algorithm empty()
  return t = 0
*/