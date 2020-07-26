package dataStructure.list.problems;

import dataStructure.list.impl.Position;
import dataStructure.list.impl.DoubleLinkedList;

public class FindMiddle<T> {
  public Position<T> findMiddleLink(DoubleLinkedList<T> L) {
    if (L.empty()) return null;

    Position<T> h = L.first();
    Position<T> t = L.last();

    while (h != t) {
      h = L.after(h);
      t = L.before(t);
    }
    return h;
  }
}

/**
Algorithm findMiddle(L)
  Input : List L has odd number of nodes
  Output : middle position of L

  if(L.isEmpty())
    return null
 	h <- L.head()
 	t <- L.tail()
 	while h != t do
    h <- L.after(h)
    t <- L.before(t)
 	return h
*/