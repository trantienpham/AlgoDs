package dataStructure.vector.impl;

public class LinkedNode<T> implements Position<T> {
  private T data;
  private LinkedNode<T> next;
  private LinkedNode<T> prev;

  public LinkedNode(T data) {
    this(data, null, null);
  }

  public LinkedNode(T data, LinkedNode<T> prev, LinkedNode<T> next) {
    this.setData(data);
    this.setPrev(prev);
    this.setNext(next);
  }

  public LinkedNode<T> getPrev() {
    return prev;
  }

  public void setPrev(LinkedNode<T> prev) {
    this.prev = prev;
  }

  public LinkedNode<T> getNext() {
    return next;
  }

  public void setNext(LinkedNode<T> next) {
    this.next = next;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

  @Override
  public T element() {
    return getData();
  }
}