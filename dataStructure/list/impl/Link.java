package dataStructure.list.impl;

public class Link<T> implements Position<T> {
  private T data;
  private Link<T> next;
  private Link<T> prev;

  public Link(T data) {
    this.data = data;
  }

  public Link(T data, Link<T> next, Link<T> prev) {
    this.data = data;
    this.next = next;
    this.setPrev(prev);
  }

  public Link<T> getPrev() {
    return prev;
  }

  public void setPrev(Link<T> prev) {
    this.prev = prev;
  }

  public Link<T> getNext() {
    return next;
  }

  public void setNext(Link<T> next) {
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
    return data;
  }
}