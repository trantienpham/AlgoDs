package dataStructure.stack.impl;

import java.util.LinkedList;
import java.util.Queue;

import dataStructure.stack.impl.exceptions.StackUnderflowException;

public class QueueStack<T> implements Stack<T>{
  private Queue<T> queue1 = new LinkedList<>();
  private Queue<T> queue2 = new LinkedList<>();
  T top = null;
  
  public QueueStack() { }

  public void push(T data) throws StackOverflowError {
    queue1.add(data);
    top = data;
  }

  public T pop() throws StackUnderflowException {
    if (empty())
      throw new StackUnderflowException();

    while(queue1.size() > 1) {
      top = queue1.poll();
      queue2.add(top);
    }
    
    T data = queue1.poll();
    
    swap(queue1, queue2);

    return data;
  }

  public T top() throws StackUnderflowException {
    return top;
  }

  public boolean empty() {
    return queue1.isEmpty();
  }

  public int size() {
    return queue1.size();
  }

  private void swap(Queue<T> q1, Queue<T> q2) {
    Queue<T> temp = q1;
    q1 = q2;
    q2 = temp;
  }
}
/*
Algorithm push(o)
  t <- o
  q1.enqueue(t)

Algorithm pop()
  if empty() then
    throw StackUnderflowException
  
  while q1.size() > 1 do
    t <- q1.dequeue()
    q2.enqueue(t)
  
  e <- q1.dequeue()

  swap(q1, q2) // swap q2 to q1 and vise versa

  return e

Algorithm top()
  if empty() then
    throw StackUnderflowException
  return t

Algorithm size()
  return q1.size()

Algorithm empty()
  return q1.empty()
*/