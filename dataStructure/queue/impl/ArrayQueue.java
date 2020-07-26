package dataStructure.queue.impl;

import java.util.Arrays;

import dataStructure.queue.impl.exceptions.QueueEmptyException;
import dataStructure.queue.impl.exceptions.QueueFullException;

public class ArrayQueue<T> implements Queue<T> {
  int N = 100;
  Position<T>[] array;
  int f = 0, size;

  public ArrayQueue(int k) {
    this.N = k;
    this.array = new Element[N];
  }

  public void enqueue(T o) throws QueueFullException {
    if (size() == N) {
      throw new QueueFullException();
    }

    array[(f + size) % N] = new Element<T>(o);
    size++;
  }

  public T dequeue() throws QueueEmptyException {
    if (empty()) {
      throw new QueueEmptyException();
    }

    T e = array[f].element();
    array[f] = null;
    f = (f + 1) % N;
    size--;

    return e;
  }

  public T front() throws QueueEmptyException {
    if (empty()) {
      throw new QueueEmptyException();
    }

    return array[f].element();
  }

  public int size() {
    return size;
  }

  public boolean empty() {
    return size() == 0;
  }

  @Override
  public String toString() {
    return Arrays.toString(array);
  }

  public static void main(String[] args) {
    Queue<Integer> q = new ArrayQueue<>(4);

    try {
      q.enqueue(1);
      q.enqueue(2);
      q.enqueue(3);
      q.enqueue(4);
      System.out.println(q);

      q.dequeue();
      System.out.println(q);

      q.enqueue(5);
      System.out.println(q);

      q.dequeue();
      System.out.println(q);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

/**
// Array implementation with circular fashion

Algorithm enqueue(o)
  if size() = N then
    throw QueueFullException
  Q[(f + size) mod N] <- o
  size <- size + 1

Algorithm dequeue()
  if empty() then
    throw QueueEmptyException
  e <- Q[f]
  Q[f] <- null
  f <- (f + 1) mod N
  size <- size - 1
  return e

Algorithm front()
  return Q[f]

Algorithm empty()
  return size() = 0

Algorithm size()
  return size
*/

