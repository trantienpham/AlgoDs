package algorithms.mergesort;
/**
R-4.5 Suppose we are given two n-element sorted sequences A and B that should not be viewed as sets (that is, A and B may contain duplicate entries).
Give an O(n)-time pseudo-code algorithm for computing a sequence representing the set A \/ B (with no duplicates).
*/

import dataStructure.sequence.impl.ArraySequence;
import dataStructure.sequence.impl.Sequence;

public class Union2SortedSequence {
  public Sequence<Integer> union(Sequence<Integer> A, Sequence<Integer> B) {
    Sequence<Integer> S = new ArraySequence<>(100);

    while (!A.empty() && !B.empty()) {
      if (B.first().element() < A.first().element()) {
        insertTo(S, B.remove(B.first()).element());
      } else if (B.first().element() == A.first().element()) {
        insertTo(S, B.remove(B.first()).element());
        A.remove(A.first());
      } else {
        insertTo(S, A.remove(A.first()).element());
      }
    }

    while (!A.empty()) {
      S.insertLast(A.remove(A.first()).element());
    }

    while (!B.empty()) {
      S.insertLast(B.remove(B.first()).element());
    }

    return S;
  }

  public void insertTo(Sequence<Integer> S, Integer e) {
    if (!S.empty() && S.last().element() != e) {
      S.insertLast(e);
    } else if (S.empty()) {
      S.insertLast(e);
    }
  }

  public static void main(String[] args) {
    Sequence<Integer> A = new ArraySequence<>(100);
    A.insertLast(1);
    A.insertLast(3);
    A.insertLast(3);
    A.insertLast(5);
    A.insertLast(7);
    A.insertLast(9);
    Sequence<Integer> B = new ArraySequence<>(100);
    B.insertLast(2);
    B.insertLast(4);
    B.insertLast(6);
    B.insertLast(6);
    B.insertLast(7);
    B.insertLast(8);
    B.insertLast(9);

    Sequence<Integer> S = new Union2SortedSequence().union(A, B);
    System.out.println(S);
  }
}

/**
Algorithm union(A, B)
  S <- new Sequence
  while !A.empty() /\ !B.empty() do
    if B.first().element() < A.first().element() then
      insertTo(S, B.remove(B.first()).element())
    else if A.first().element() = B.first().element() then
      insertTo(S, B.remove(A.first()).element())
      B.remove(B.first())
    else
      insertTo(S, A.remove(A.first()).element())
  while !A.empty() do
    S.insertLast(A.remove(A.first()))
  while !b.empty() do
    S.insertLast(B.remove(b.first()))

Algorithm insertTo(S, e)
  if !S.empty() /\ S.last().element() != e then
    S.insertLast(e)
  else if S.empty() then
    S.insertLast(e)

*/