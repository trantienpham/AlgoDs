package dataStructure.priorityqueue.impl;

public interface Position<K, E> {
  E element();
  K key();
}