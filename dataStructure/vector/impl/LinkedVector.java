package dataStructure.vector.impl;

public class LinkedVector<T> implements Vector<T> {
  private int size;
  private LinkedNode<T> front;
  private LinkedNode<T> rear;

  public LinkedVector() {
    this.front = new LinkedNode<T>(null);
    this.rear = new LinkedNode<T>(null);
    this.front.setNext(this.rear);
    this.rear.setPrev(this.front);
    this.size = 0;
  }

  @Override
  public Position<T> elementAtRank(int r) {
    if (isInValidRank(r)) throw new Error("Invalid rank");
    return rank2Position(r);
  }

  @Override
  public Position<T> replaceAtRank(T e, int r) {
    if (isInValidRank(r)) throw new Error("Invalid rank");
    LinkedNode<T> p = rank2Position(r);
    p.setData(e);
    return p;
  }

  @Override
  public Position<T> insertAtRank(T e, int r) {
    if (isInValidRank(r)) throw new Error("Invalid rank");
    LinkedNode<T> newE = new LinkedNode<T>(e);
    LinkedNode<T> p = rank2Position(r);
    
    newE.setPrev(p.getPrev());
    newE.setNext(p);
    p.getPrev().setNext(newE);
    p.setPrev(newE);
    size++;
    return newE;
  }

  @Override
  public Position<T> removeAtRank(int r) {
    if (empty()) throw new Error("Vector is empty");
    LinkedNode<T> p = rank2Position(r);
    p.getPrev().setNext(p.getNext());
    p.getNext().setPrev(p.getPrev());
    p.setNext(null);
    p.setPrev(null);
    size--;
    return p;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean empty() {
    return front.getNext() == rear && rear.getPrev() == front;
  }
  
  private boolean isInValidRank(int r) {
    return 0 > r || r > size();
  }
  private LinkedNode<T> rank2Position(int r) {
    int i = 0;
    LinkedNode<T> p = this.front;
    while (i <= r) {
      p = p.getNext();
      i++;
    }

    return p;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (!empty()) {
      builder.append("[");
      LinkedNode<T> p = this.front.getNext();
      while (p != this.rear) {
        builder.append(p.element() + ", ");
        p = p.getNext();
      }
      builder.delete(builder.length() - 2, builder.length());
      builder.append("]");
    }
    return builder.toString();
  }

  public static void main(String[] args) {
    Vector<Integer> V = new LinkedVector<>();
    V.insertAtRank(0, 0);
    V.insertAtRank(2, V.size());
    System.out.println(V);
    V.insertAtRank(1, 1);
    System.out.println(V);
    Position<Integer> p = V.removeAtRank(1);
    System.out.println(p.element());
    System.out.println(V);
    V.replaceAtRank(1, 1);
    System.out.println(V);
  }
}

/*
Algorithm elementAtRank(r)
  if isInValidRank(r) throw Error("Invalid rank")
  return rank2Position(r)

Algorithm replaceAtRank(e, r)
  if isInValidRank(r) throw new Error("Invalid rank")
  p <- rank2Position(r)
  p.setData(e)
  return p;

Algorithm insertAtRank(e, r)
  if isInValidRank(r) throw new Error("Invalid rank")
  newE = new LinkedNode<T>(e)
  p = rank2Position(r)
  
  newE.setPrev(p.getPrev())
  newE.setNext(p)
  p.getPrev().setNext(newE)
  p.setPrev(newE)
  size <- size + 1
  return newE

Algorithm removeAtRank(r)
  if empty() throw new Error("Vector is empty")
  p = rank2Position(r)
  p.getPrev().setNext(p.getNext())
  p.getNext().setPrev(p.getPrev())
  p.setNext(null)
  p.setPrev(null)
  size <- size - 1
  return p

Algorithm size()
  return size

Algorithm empty()
  front.getNext() = rear /\ rear.getPrev() = front
*/