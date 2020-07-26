package dataStructure.sequence.impl;

public interface Sequence<T> extends Vector<T>, List<T> {
  Position<T> atRank(int r);
  int rankOf(Position<T> p);
}