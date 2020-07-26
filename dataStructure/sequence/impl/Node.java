package dataStructure.sequence.impl;

public class Node<T> implements Position<T> {
  private T data;
  private int index;

  public Node(T data, int index) {
    this.data = data;
    this.index = index;
  }

  @Override
  public T element() {
    return data;
  }

  public void setElement(T data) {
    this.data = data;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public int getIndex() {
    return index;
  }

  @Override
  public String toString() {
    return String.valueOf(data);
  }
}