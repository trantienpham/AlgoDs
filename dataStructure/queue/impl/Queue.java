package dataStructure.queue.impl;

import dataStructure.queue.impl.exceptions.QueueEmptyException;
import dataStructure.queue.impl.exceptions.QueueFullException;

public interface Queue<T> {
  void enqueue(T e) throws QueueFullException;
  T dequeue() throws QueueEmptyException;
  T front() throws QueueEmptyException;
  int size();
  boolean empty();
}