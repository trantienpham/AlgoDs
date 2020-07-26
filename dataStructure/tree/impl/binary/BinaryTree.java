package dataStructure.tree.impl.binary;

import dataStructure.tree.impl.*;

public interface BinaryTree<T> extends Tree<T> {
  Position<T> leftChild(Position<T> p);
  Position<T> rightChild(Position<T> p);
  Position<T> sibling(Position<T> p);

  void add(T e);
  default void addRoot(T e) {}
  default void addLeft(T e) {}
  default void addRight(T e) {}
}