package dataStructure.tree.impl;

import java.util.Iterator;

public interface Tree<T> {
  // generic
  int size();
  boolean empty();
  Iterator<T> elements();
  Iterator<Position<T>> positions();
  
  // accessor
  Position<T> root();
  Position<T> parent(Position<T> p);
  Iterator<Position<T>> children(Position<T> p);

  // query
  boolean isExternal(Position<T> p);
  boolean isInternal(Position<T> p);
  boolean isRoot(Position<T> p);

  // update
  void swapElements(Position<T> p, Position<T> q);
  Position<T> replaceElement(Position<T> p, T e);
}