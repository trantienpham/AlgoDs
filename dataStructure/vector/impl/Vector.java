package dataStructure.vector.impl;

public interface Vector<T> {
  Position<T> elementAtRank(int r);
  Position<T> replaceAtRank(T e, int r);
  Position<T> insertAtRank(T e, int r);
  Position<T> removeAtRank(int r);
  int size();
  boolean empty();
}