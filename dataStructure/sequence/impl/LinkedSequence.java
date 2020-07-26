package dataStructure.sequence.impl;

public class LinkedSequence<T> implements Sequence<T> {
  private int size;
  private LinkedNode<T> front;
  private LinkedNode<T> rear;

  public LinkedSequence() {
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

  @Override
  public Position<T> first() {
    if (empty()) throw new Error("Sequence is empty");
    return this.front.getNext();
  }

  @Override
  public Position<T> last() {
    if (empty()) throw new Error("Sequence is empty");
    return this.rear.getPrev();
  }

  @Override
  public boolean isFirst(Position<T> p) {
    return rankOf(p) == 0;
  }

  @Override
  public boolean isLast(Position<T> p) {
    return rankOf(p) == size() - 1;
  }

  @Override
  public Position<T> before(Position<T> p) {
    int r = rankOf(p);
    return atRank(r - 1);
  }

  @Override
  public Position<T> after(Position<T> p) {
    int r = rankOf(p);
    return atRank(r + 1);
  }

  @Override
  public Position<T> replaceElement(Position<T> p, T e) {
    int r = rankOf(p);
    return replaceAtRank(e, r);
  }

  @Override
  public void swapElement(Position<T> p, Position<T> q) {
    T temp = q.element();
    replaceElement(q, p.element());
    replaceElement(p, temp);
  }

  @Override
  public Position<T> insertFirst(T e) {
    return insertAtRank(e, 0);
  }

  @Override
  public Position<T> insertLast(T e) {
    return insertAtRank(e, size() - 1);
  }

  @Override
  public Position<T> insertBefore(Position<T> p, T e) {
    int r = rankOf(p);
    return insertAtRank(e, r - 1);
  }

  @Override
  public Position<T> insertAfter(Position<T> p, T e) {
    int r = rankOf(p);
    return insertAtRank(e, r + 1);
  }

  @Override
  public Position<T> remove(Position<T> p) {
    int r = rankOf(p);
    return removeAtRank(r);
  }

  @Override
  public Position<T> atRank(int r) {
    return rank2Position(r);
  }

  @Override
  public int rankOf(Position<T> p) {
    return position2Rank(p);
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
  private int position2Rank(Position<T> p) {
    LinkedNode<T> temp = this.front.getNext();
    int i = -1;
    while (temp.element() != null && p.element() != null) {
      i++;
      if (p.element().equals(temp.element())) {
        break;
      }
      temp = temp.getNext();
    }
    return i;
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
    Sequence<Integer> S = new LinkedSequence<>();
    S.insertAtRank(0, S.size());
    System.out.println(S);
    System.out.println(S.atRank(0).element());
  }
}

/*
Algorithm elemAtRank(r)
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

Algorithm first()
  if empty() then throw new Error("Sequence is empty")
  return this.front.getNext()

Algorithm last()
  if empty() then throw new Error("Sequence is empty")
  return this.rear.getPrev()

Algorithm isFirst(p)
  return rankOf(p) = 0

Algorithm isLast(p)
  return rankOf(p) = (size() - 1)

Algorithm before(p)
  r <- rankOf(p)
  return atRank(r - 1)

Algorithm after(p)
  r <- rankOf(p)
  return atRank(r + 1);

Algorithm replaceElement(p, e)
  int r = rankOf(p)
  return replaceAtRank(e, r)

Algorithm swapElement(p, q)
  temp <- q.element()
  replaceElement(q, p.element())
  replaceElement(p, temp)

Algorithm insertFirst(e)
  return insertAtRank(e, 0)

Algorithm insertLast(e)
  return insertAtRank(e, size() - 1)

Algorithm insertBefore(p, e)
  r <- rankOf(p);
  return insertAtRank(e, r - 1)

Algorithm insertAfter(p, e)
  r <- rankOf(p);
  return insertAtRank(e, r + 1)

Algorithm remove(p)
  r <- rankOf(p)
  return removeAtRank(r)

Algorithm atRank(r)
  return rank2Position(r)

Algorithm (p)
  return position2Rank(p)

Algorithm size()
  return size

Algorithm empty()
  front.getNext() = rear /\ rear.getPrev() = front
*/