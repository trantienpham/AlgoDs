package dataStructure.queue.impl;

public class LinkedElement<T> implements Position<T> {
  private T data;
  private LinkedElement<T> next;
  private LinkedElement<T> prev;

  public LinkedElement(T data) {
  }

  public LinkedElement(T data, LinkedElement<T> prev, LinkedElement<T> next) {
    this.data = data;
    this.setPrev(prev);
    this.next = next;
  }

  public LinkedElement<T> getPrev() {
    return prev;
  }

  public void setPrev(LinkedElement<T> prev) {
    this.prev = prev;
  }

  public LinkedElement<T> getNext() {
    return next;
  }

  public void setNext(LinkedElement<T> next) {
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