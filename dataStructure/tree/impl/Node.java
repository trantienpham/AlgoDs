package dataStructure.tree.impl;

public class Node<T> implements Position<T> {
  T element;
  int key;
  Node<T> parent;
  Node<T>[] children;

  public Node(T element, Node<T> parent, Node<T>[] children) {
    this.element = element;
    this.parent = parent;
    this.children = children;
  }

  @Override
  public T element() {
    return element;
  }

  public void setElement(T element) {
    this.element = element;
  }
}