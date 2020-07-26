package dataStructure.list.impl;

public interface List<T> {
  Position<T> first();
  Position<T> last();
  boolean isFirst(Position<T> p);
  boolean isLast(Position<T> p);
  Position<T> before(Position<T> p);
  Position<T> after(Position<T> p);

  Position<T> replaceElement(Position<T> p, T e);
  void swapElement(Position<T> p, Position<T> q);
  Position<T> insertFirst(T e);
  Position<T> insertLast(T e);
  Position<T> insertBefore(Position<T> p, T e);
  Position<T> insertAfter(Position<T> p, T e);
  Position<T> remove(Position<T> p);
  int size();
  boolean empty();
}