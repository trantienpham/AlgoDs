package dataStructure.stack.impl;

import dataStructure.stack.impl.exceptions.StackOverflowException;
import dataStructure.stack.impl.exceptions.StackUnderflowException;

public interface Stack<T> {
  void push(T e) throws StackOverflowException;
  T pop() throws StackUnderflowException;
  T top() throws StackUnderflowException;
  int size();
  boolean empty();
}