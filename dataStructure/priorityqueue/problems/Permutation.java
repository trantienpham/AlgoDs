package dataStructure.priorityqueue.problems;

import dataStructure.priorityqueue.impl.Comparator;
import dataStructure.priorityqueue.impl.HeapPriorityQueue;
import dataStructure.priorityqueue.impl.PriorityQueue;
import dataStructure.sequence.impl.Position;
import dataStructure.sequence.impl.Sequence;

public class Permutation {
  Comparator<Integer> C;
  public boolean isPermutation(Sequence<Integer> S1, Sequence<Integer> S2) {
    if (S1.size() == S2.size() && !S1.empty()) {
      PriorityQueue<Integer, Integer> PQ1 = new HeapPriorityQueue<>();
      PriorityQueue<Integer, Integer> PQ2 = new HeapPriorityQueue<>();

      Position<Integer> p1 = S1.last();
      while (!S1.empty()) {
        PQ1.insertItem(p1.element(), p1.element());
        S1.remove(p1);
        p1 = S1.last();
      }

      Position<Integer> p2 = S2.last();
      while (!S2.empty()) {
        PQ2.insertItem(p2.element(), p2.element());
        S2.remove(p2);
        p2 = S2.last();
      }

      Integer pq1 = PQ1.removeMin();
      Integer pq2 = PQ2.removeMin();

      while (!PQ1.empty()) {
        if (!C.isEqualTo(pq1, pq2)) {
          return false;
        }
        pq1 = PQ1.removeMin();
        pq2 = PQ2.removeMin();
      }

      return true;
    }
    return false;
  }
}

/**
Algorithm isPermutation(S1, S2)
  if S1.size() = S2.size() /\ !S1.empty() then
    PQ1 <- new PriorityQueue
    PQ2 <- new PriorityQueue

    p1 <- S1.last()
    while !S1.empty() do
      PQ1.insertItem(p1, p1)
      S1.remove(p1)
      p1 <- S1.last()

    p2 <- S2.last()
    while !S2.empty() do
      PQ2.insertItem(p2, p2)
      S2.remove(p2)
      p2 <- S2.last()

    pq1 <- PQ1.removeMin()
    pq2 <- PQ2.removeMin()

    while !PQ1.empty() do
      if pq1.element() not equal pq2.element() then
        return false
      pq1 <- PQ1.removeMin()
      pq2 <- PQ2.removeMin()
      
    return true
  return false
*/