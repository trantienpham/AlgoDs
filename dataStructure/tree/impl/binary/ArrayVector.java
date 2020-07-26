package dataStructure.tree.impl.binary;

import dataStructure.tree.impl.Position;

public class ArrayVector<T> implements Vector<T> {
  int N = 100;
  ArrayNode<T>[] V = new ArrayNode[N];
  int front = 0, rear = 0;

  public ArrayVector() {
    V = new ArrayNode[N];
  }

  public Position<T> elementAtRank(int r) {
    int index = rank2Index(r);
    return V[index];
  }

  public Position<T> replaceAtRank(T e, int r) {
    int index = rank2Index(r);
    V[index].setData(e);
    V[index].setIndex(index);
    return V[index];
  }

  public Position<T> insertAtRank(T e, int r) {
    if (isInValidRank(r)) throw new Error("Invalid rank");

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
    ArrayNode<T> node = new ArrayNode<>(e, index);
    V[index] = node;
    return V[index];
  }

  public Position<T> removeAtRank(int r) {
    if (empty()) throw new Error("Vector is empty");
    if (isInValidRank(r)) throw new Error("Invalid rank");

    int size = size();
    int index = rank2Index(r);
    Position<T> e = V[index];
    if (r < size/2) {
      shiftRight(0, r);
      front = (front + 1) % N;
    } else {
      shiftLeft(r, size-1);
      rear = (N + rear - 1) % N;
    }
    return e;
  }

  public int size() {
    return (N - front + rear) % N;
  }

  public boolean empty() {
    return front == rear;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (!empty()) {
      builder.append("[");
      for (int i = 0; i < size(); i++) {
        Position<T> p = elementAtRank(i);
        builder.append(p.element() + ", ");
      }
      builder.delete(builder.length() - 2, builder.length());
      builder.append("]");
    }
    return builder.toString();
  }

  private boolean isInValidRank(int r) {
    return r < 0 || r > size();
  }
  private int rank2Index(int r) {
    if (isInValidRank(r)) {
      throw new Error("Invalid rank " + r);
    }
    return (front + r) % N;
  }
  private void shiftLeft(int r1, int r2) {
    int index = rank2Index(r1);
    for (int i = r1; i < r2; i++) {
      int next = rank2Index(i + 1);
      V[index] = V[next];
      index = next;
    }
  }
  private void shiftRight(int r1, int r2) {
    int index = rank2Index(r2);
    for (int i = r2; i > r1; i--) {
      int prev = rank2Index(i - 1);
      V[index] = V[prev];
      index = prev;
    }
  }
  private void ensureNotOverflow() {
    int size = size();
    if (size >= N -1) {
      N = N * 2;
      ArrayNode<T>[] newV = new ArrayNode[N*2];
      for (int i = 0; i < size; i++) {
        int ix = rank2Index(i);
        newV[i] = V[ix];
        newV[i].setIndex(i);
      }
      V = newV;
      front = 0;
      rear = size;
    }
  }
}