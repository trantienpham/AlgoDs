package dataStructure.tree.impl.binary;

import dataStructure.tree.impl.Position;

public class LinkedNode<T> implements Position<T>{
  T element;
  LinkedNode<T> left;
  LinkedNode<T> right;
  LinkedNode<T> parent;
  public LinkedNode() {
    this(null);
  }
  public LinkedNode(T element) {
    this.element = element;
  }

  @Override
  public T element() {
    return element;
  }

  public LinkedNode<T> parent() {
    return parent;
  }

  public LinkedNode<T> left() {
    return left;
  }

  public LinkedNode<T> right() {
    return right;
  }


  public void setElement(T element) {
    this.element = element;
  }

  public void setParent(LinkedNode<T> parent) {
    this.parent = parent;
  }

  public void setLeft(LinkedNode<T> left) {
    this.left = left;
  }

  public void setRight(LinkedNode<T> right) {
    this.right = right;
  }
}