package dataStructure.queue.impl;

public class Element<T> implements Position<T> {
  private T data;

  public Element(T data) {
    this.setData(data);
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