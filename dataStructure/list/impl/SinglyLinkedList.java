package dataStructure.list.impl;

public class SinglyLinkedList<T> implements List<T> {
  Element<T> front;
  Element<T> rear;

  public SinglyLinkedList() {
    this.front = new Element<T>(null);
    this.rear = new Element<T>(null);
  }

  public Position<T> first() {
    return null;
  }

  public Position<T> last() {
    return null;
  }

  public boolean isFirst(Position<T> p) {
    return false;
  }

  public boolean isLast(Position<T> p) {
    return false;
  }

  public Position<T> before(Position<T> p) {
    return null;
  }

  public Position<T> after(Position<T> p) {
    return null;
  }

  public Position<T> replaceElement(Position<T> p, T e) {
    return p;
  }

  public void swapElement(Position<T> p, Position<T> q) {

  }

  public Position<T> insertFirst(T e) {
    return null;
  }

  public Position<T> insertLast(T e) {
    return null;
  }

  public Position<T> insertBefore(Position<T> p, T e) {
    return null;
  }

  public Position<T> insertAfter(Position<T> p, T e) {
    return null;
  }

  public Position<T> remove(Position<T> p) {
    return null;
  }

  public int size() {
    return 0;
  }

  public boolean empty() {
    return false;
  }
}

/**
Algorithm first()

Algorithm last()

Algorithm isFirst(p)

Algorithm isLast(p)

Algorithm before(p)

Algorithm after(p)

Algorithm replaceElement(p, e)

Algorithm swapElement(p, q)

Algorithm insertBefore(p, e)

Algorithm insertAfter(p, e)

Algorithm insertFirst(e)

Algorithm insertLast(e)

Algorithm remove(p)

Algorithm size()
  return size

Algorithm empty()
  return front.next = rear
*/