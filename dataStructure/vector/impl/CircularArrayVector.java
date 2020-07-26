package dataStructure.vector.impl;

public class CircularArrayVector<T> implements Vector<T> {
  int N = 100;
  VPosition<T>[] V = new VPosition[N];
  int front = 0, rear = 0;

  public CircularArrayVector(int size) {
    if (size > 0) {
      N = size;
    }
    V = new VPosition[N];
  }

  public Position<T> elementAtRank(int r) {
    int index = rank2Index(r);
    return V[index];
  }

  public Position<T> replaceAtRank(T e, int r) {
    int index = rank2Index(r);
    V[index].setElement(e);
    return V[index];
  }

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
    V[index] = new VPosition<T>(e);
    return V[index];
  }

  public Position<T> removeAtRank(int r) {
    if (empty()) throw new Error("Vector is empty");
    if (!isValidRank(r)) throw new Error("Invalid rank");

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

  private boolean isValidRank(int r) {
    return 0 <= r && r < size();
  }
  private int rank2Index(int r) {
    if (!isValidRank(r)) {
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
      VPosition<T>[] newV = new VPosition[N*2];
      for (int i = 0; i < size; i++) {
        int ix = rank2Index(i);
        newV[i] = V[ix];
      }
      V = newV;
      front = 0;
      rear = size;
    }
  }
}
/*
Algorithm elemAtRank(r)
  index <- rank2Index(r)
  return V[index]

Algorithm replaceAtRank(e, r)
  index <- rank2Index(r)
  V[index] <- e
  return e

Algorithm insertAtRank(e, r)
  if !isValidRank(r) then
    throw Error("Invalid rank")

  ensureNotOverflow()

  size <- size()

  if (r < size/2) then
    front <- (N + front - 1) % N
    shiftLeft(0, r)
  else
    rear <- (rear + 1) % N
    shiftRight(r, size);

  index <- rank2Index(r)
  V[index] <- e

Algorithm removeAtRank(r)
  if !isValidRank(r) then
    throw Error("Invalid rank")

  size <- size()
  index <- rank2Index(r)
  e <- V[index]

  if r < size/2 then
    shiftRight(0, r);
    front = (front + 1) % N;
  else
    shiftLeft(r, size-1);
    rear = (N + rear - 1) % N;

  return e


Algorithm size()
  return (N - front + rear) % N

Algorithm empty()
  return front == rear

Algorithm isValidRank(r)
  return 0 <= r && r < size()

Algorithm rank2Index(r)
  if !isValidRank(r) then
    throw Error("Invalid rank ")
  return (front + r) % N

Algorithm shiftLeft(r1, r2)
  index <- rank2Index(r1)
  for i <- r1, r1 + 1, ... r2 do
    next <- rank2Index(i + 1)
    V[index] <- V[next]
    index <- next

Algorithm shiftRight(r1, r2)
  index <- rank2Index(r2)
  for i <- r2, r2 - 1, ... r1 do
    prev <- rank2Index(i - 1)
    V[index] <- V[prev]
    index <- prev


Algorithm ensureNotOverflow()
  size <- size()
  if size >= N - 1 then
    N <- N * 2
    newV <- new array of object with length * 2
    for i <- 0, 1, ... size do
      ix <- rank2Index(i)
      newV[i] = V[ix]
    V <- newV
    front <- 0
    rear <- size
*/