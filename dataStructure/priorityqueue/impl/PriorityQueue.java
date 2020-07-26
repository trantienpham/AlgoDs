package dataStructure.priorityqueue.impl;

public interface PriorityQueue<K, E> {
  void insertItem(K key, E e);
  E removeMin();
  boolean empty();
  int size();
}