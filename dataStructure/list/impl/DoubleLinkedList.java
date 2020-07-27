package dataStructure.list.impl;

public class DoubleLinkedList<T> implements List<T> {
  Link<T> header;
  Link<T> tailer;
  int size;

  public DoubleLinkedList() {
    this.header = new Link<T>(null);
    this.tailer = new Link<T>(null);
    this.header.setNext(this.tailer);
    this.tailer.setPrev(this.header);
    this.size = 0;
  }

  public Position<T> first() {
    if (empty()) throw new Error("List is empty");

    return header.getNext();
  }

  public Position<T> last() {
    if (empty()) throw new Error("List is empty");

    return tailer.getPrev();
  }

  public boolean isFirst(Position<T> p) {
    return header.getNext() == p;
  }

  public boolean isLast(Position<T> p) {
    return tailer.getPrev() == p;
  }

  public Position<T> before(Position<T> p) {
    return ((Link<T>) p).getPrev();
  }

  public Position<T> after(Position<T> p) {
    return ((Link<T>) p).getNext();
  }

  public Position<T> replaceElement(Position<T> p, T e) {
    ((Link<T>) p).setData(e);
    return p;
  }

  public void swapElement(Position<T> p, Position<T> q) {
    T t = p.element();
    ((Link<T>) p).setData(q.element());
    ((Link<T>) q).setData(t);
  }

  public Position<T> insertFirst(T e) {
    return insertAfter(header, e);
  }

  public Position<T> insertLast(T e) {
    return insertBefore(tailer, e);
  }

  public Position<T> insertBefore(Position<T> p, T e) {
    Link<T> link = new Link<T>(e);

    link.setPrev(((Link<T>) p).getPrev());
    link.setNext((Link<T>) p);

    ((Link<T>) p).getPrev().setNext(link);
    ((Link<T>) p).setPrev(link);

    size++;

    return link;
  }

  public Position<T> insertAfter(Position<T> p, T e) {
    Link<T> link = new Link<T>(e);

    link.setPrev((Link<T>) p);
    link.setNext(((Link<T>) p).getNext());
    ((Link<T>) p).getNext().setPrev(link);
    ((Link<T>) p).setNext(link);

    size++;

    return link;
  }

  public Position<T> remove(Position<T> p) {
    Link<T> link = (Link<T>) p;

    link.getPrev().setNext(link.getNext());
    link.getNext().setPrev(link.getPrev());

    link.setNext(null);
    link.setPrev(null);

    size--;

    return p;
  }

  public int size() {
    return size;
  }

  public boolean empty() {
    return header.getNext() == null && tailer.getPrev() == null;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (!empty()) {
      Position<T> p = first();
      builder.append("[");
      while (p != tailer) {
        builder.append(p.element() + ", ");
        p = after(p);
      }
      builder.delete(builder.length() - 2, builder.length());
      builder.append("]");
    }
    return builder.toString();
  }
}

/**
Algorithm first()
  return header.getNext()

Algorithm last()
  return tailer.getPrev()

Algorithm isFirst(p)
  return header.getNext() = p

Algorithm isLast(p)
  return tailer.getPrev() = p

Algorithm before(p)
  if isFirst(p) then
    throw Error("There is no Position before p")
  return p.prev

Algorithm after(p)
  if (isLast(p)) then
    throw Error("There is no Position after p")
  return p.next

Algorithm replaceElement(p, e)
  o <- p.element
  p.element <- e
  return o

Algorithm swapElement(p, q)
  t <- p.element
  p.element <- q.element
  q.element <- t

Algorithm insertBefore(p, e)
  create a new Position v
  v.element <- e
  v.prev <- p.prev
  v.next <- p
  (p.prev).next <- v
  p.prev <- v
  size <- size + 1
  return v

Algorithm insertAfter(p, e)
  create a new Position v
  v.element <- e
  v.prev <- p
  v.next <- p.next
  (p.next).prev = v
  p.next <- v
  size <- size + 1
  return v

Algorithm insertFirst(e)
  return insertAfter(header, e)

Algorithm insertLast(e)
  return insertBefore(tailer, e)

Algorithm remove(p)
  t <- p.element
  (p.next).prev <- p.prev
  (p.prev).next <- p.next
  p.prev <- null
  p.next <- null
  size <- size - 1
  return t

Algorithm size()
  return size

Algorithm empty()
  return header.next = tailer /\ tailer.prev = header
*/