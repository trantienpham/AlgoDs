package dataStructure.vector.impl;

public class ArrayVector<T> implements Vector<T> {
  static final int N = 100;
  VPosition<T>[] V = new VPosition[N];
  int size = 0;

  public Position<T> elementAtRank(int r) {
    if (r < 0 || r > size) throw new Error("r must be in range of [0, "+size+"]");

    return V[r];
  }

  public Position<T> replaceAtRank(T e, int r) {
    if (r < 0 || r > size) throw new Error("r must be in range of [0, "+size+"]");
    V[r] = new VPosition<T>(e);

    return V[r];
  }

  public Position<T> insertAtRank(T e, int r) {
    if (r < 0 || r > size) throw new Error("r must be in range of [0, "+size+"]");
    if (isFull()) throw new Error("Vector is full");
    
    for (int i = size - 1; i >= r; i--) {
      V[i + 1] = V[i];
    }
    V[r] = new VPosition<T>(e);
    size++;
    return V[r];
  }

  public Position<T> removeAtRank(int r) {
    if (r < 0 || r > size) throw new Error("r must be in range of [0, "+size+"]");
    if (empty()) throw new Error("Vector is empty");

    Position<T> e = V[r];
    for (int i = r; i <= size; i++) {
      V[i] = V[i+1];
    }
    size--;
    return e;
  }

  public int size() {
    return size;
  }

  public boolean empty() {
    return size == 0;
  }

  public boolean isFull() {
    return size == N;
  }
}
/**

Algorithm elemAtRank(r)
  return V[r]

Algorithm replaceAtRank(e, r)
  V[r] <- e
  return e

Algorithm insertAtRank(e, r)
  for i <- n - 1, n - 2, .... r do
    V[i+1] <- V[i]
  V[r] <- e
  n <- n + 1

Algorithm removeAtRank(r)
  e <- V[r]
  for i <- r, r + 1, r + 2, ... n do
    V[i] = V[i + 1]
  n <- n - 1
  return e

Algorithm size()
  return n

Algorithm empty()
  return n = 0

*/