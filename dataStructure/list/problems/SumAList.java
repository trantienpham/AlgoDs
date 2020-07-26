package dataStructure.list.problems;

import dataStructure.list.impl.Position;
import dataStructure.list.impl.DoubleLinkedList;

public class SumAList {
  public int sum(DoubleLinkedList<Integer> L) {
    return sumHelper(L, L.first());
  }

  public int sumHelper(DoubleLinkedList<Integer> L, Position<Integer> p) {
    int sum = 0;
    while (!L.isLast(p)) {
      sum += p.element();
      p = L.after(p);
    }
    sum += p.element(); // last element
    return sum;
  }

  public static void main(String[] args) {
    DoubleLinkedList<Integer> L = new DoubleLinkedList<>();
    L.insertLast(1);
    L.insertLast(2);
    L.insertLast(3);
    int result = new SumAList().sum(L);
    System.out.println(result);
  }
}

/**
Algorithm sum(L)
  p <- L.first()
  return sumHelper(L, p)

Algorithm sumHelper(L, p)
  sum <- 0
  while !L.isLast(p) then
    sum <- sum + p.element()
    p <- L.after(p)
  sum <- sum + p.element()
  return sum
*/