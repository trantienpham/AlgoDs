package dataStructure.sequence.problems;

import dataStructure.sequence.impl.Position;
import dataStructure.sequence.impl.Sequence;
import dataStructure.sequence.impl.ArraySequence;

public class RemoveDuplicate {
  public static void main(String[] args) {
    Sequence<Integer> S = new ArraySequence<Integer>(5);
    S.insertAtRank(0, 0);
    S.insertAtRank(1, 1);
    S.insertAtRank(2, 2);
    S.insertAtRank(1, 3);
    S.insertAtRank(4, 4);
    System.out.println(S);

    Sequence<Integer> newS = removeAllDuplicate(S);
    System.out.println(newS);
  }

  public static Sequence<Integer> removeAllDuplicate(Sequence<Integer> S) {
    if (S.empty()) {
      return S;
    }
    Position<Integer> p = S.first();
    int i = S.size() / 2;
    while(i <= (S.size() / 2) && !S.isLast(p)) {
      Position<Integer> q = S.last();
      while (p != q) {
        if (p.element().equals(q.element())) {
          S.remove(q);
          i = S.size() / 2;
        }
        q = S.before(q);
      }
      p = S.after(p);
    }
    return S;
  }
}

/**
Algorithm removeAllDuplicate(S)
  if S.isEmpty() then
    return S
  p <- S.first()
  i <- S.size() / 2
  while i <= S.size() / 2 \/ !S.isLast(p) do
    q <- S.last()
    while (q != p) do
      if p.element() = q.element() then
        S.remove(q)
        i <- S.size() / 2
      q = S.before(q)
    p <- S.after(p)
  return S
*/