package dataStructure.sequence.impl;

public class ArraySequence<T> implements Sequence<T> {
  int N = 100;
  Node<T>[] S;
  int front = 0, rear = 0;

  public ArraySequence(int capacity) {
    if (capacity > 0) {
      this.N = capacity;
    }
    this.S = new Node[this.N];
  }

  @Override
  public Position<T> elementAtRank(int r) {
    int index = rank2Index(r);
    Position<T> e = S[index];
    return e;
  }

  @Override
  public Position<T> replaceAtRank(T e, int r) {
    int index = rank2Index(r);
    Node<T> node = S[index];
    node.setElement(e);
    return node;
  }

  @Override
  public Position<T> insertAtRank(T e, int r) {
    if (!isValidRank(r)) throw new Error("Invalid rank");

    ensureNotOverflow();
    
    int size = size();
    if (r < size/2) {
      front = (N + front - 1) % N; // Note
      shiftLeft(0, r);
    } else {
      rear = (rear + 1) % N; // Note
      shiftRight(r, size);
    }
    int index = rank2Index(r);
    S[index] = new Node<>(e, index);

    return S[index];
  }

  @Override
  public Position<T> removeAtRank(int r) {
    if (empty()) throw new Error("Sequence is empty");
    if (!isValidRank(r)) throw new Error("Invalid rank");

    int size = size();
    int index = rank2Index(r);
    Node<T> e = (Node<T>) S[index];
    if (r < size/2) {
      shiftRight(0, r);
      front = (front + 1) % N;
    } else {
      shiftLeft(r, size - 1);
      rear = (N + rear - 1) % N;
    }
    return e;
  }

  @Override
  public Position<T> first() {
    return atRank(0);
  }

  @Override
  public Position<T> last() {
    return atRank(size() - 1);
  }

  @Override
  public boolean isFirst(Position<T> p) {
    int r = rankOf(p);
    return r == 0;
  }

  @Override
  public boolean isLast(Position<T> p) {
    int r = rankOf(p);
    return r == size() - 1;
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
    return insertAtRank(e, size());
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
  public int size() {
    return (N - front + rear) % N;
  }

  @Override
  public boolean empty() {
    return front == rear;
  }

  @Override
  public Position<T> atRank(int r) {
    int index = rank2Index(r);
    return S[index];
  }

  @Override
  public int rankOf(Position<T> p) {
    int index = ((Node<T>) p).getIndex();
    int r = index2Rank(index);
    return r;
  }
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    Position<T> p = this.first();
    while (p != null) {
      Object data = p.element();
      builder.append(data + ", ");
      p = this.after(p); 
    }
    return "[" + (!empty() ? builder.substring(0, builder.length() - 2).toString() : "") + "]";
  }

  private boolean isValidRank(int r) {
    return 0 <= r && r <= size();
  }
  private int rank2Index(int r) {
    if (!isValidRank(r)) {
      throw new Error("Invalid rank " + r);
    }
    return (front + r) % N; // Note
  }
  private int index2Rank(int i) {
    int r = (N - front + i) % N;
    return r;
  }
  private void shiftLeft(int r1, int r2) {
    int index = rank2Index(r1);
    int r;
    for (r = r1; r < r2; r++) {
      int next = rank2Index(r + 1);
      S[index] = S[next];
      ((Node<T>) S[index]).setIndex(index);
      index = next;
    }
    if (r == r2) {
      int i = rank2Index(r);
      S[i] = null;
    }
  }
  private void shiftRight(int r1, int r2) {
    int index = rank2Index(r2);
    int r;
    for (r = r2; r > r1; r--) {
      int prev = rank2Index(r - 1);
      S[index] = S[prev];
      ((Node<T>) S[index]).setIndex(index);
      index = prev;
    }
    if (r == r1) {
      int i = rank2Index(r);
      S[i] = null;
    }
  }
  private void ensureNotOverflow() {
    int size = size();
    if (size >= N -1) {
      N = N * 2;
      Node<T>[] newS = new Node[N * 2];
      for (int i = 0; i < size; i++) {
        int ix = rank2Index(i);
        newS[i] = this.S[ix];
        ((Node<T>) newS[i]).setIndex(i);
      }
      S = newS;
      front = 0;
      rear = size;
    }
  }
}

/*
Algorithm elemAtRank(r)
Algorithm replaceAtRank(e, r)
Algorithm insertAtRank(e, r)
Algorithm removeAtRank(r)

Algorithm first()
Algorithm last()
Algorithm isFirst(p)
Algorithm isLast(p)
Algorithm before(p)
Algorithm last(p)

Algorithm replaceElement(p, e)
Algorithm swapElement(p, q)
Algorithm insertFirst(e)
Algorithm insertLast(e)
Algorithm insertBefore(p, e)
Algorithm insertAfter(p, e)
Algorithm remove(p)

Algorithm size()
Algorithm empty()
*/