package dataStructure.vector.impl;

public class VPosition<T> implements Position<T> {
  private T element;

  public VPosition(T element) {
    this.element = element;
  }

  @Override
  public T element() {
    return element;
  }
  public void setElement(T element) {
    this.element = element;
  }
}