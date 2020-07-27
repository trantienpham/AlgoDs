package algorithms.quicksort;

import dataStructure.list.impl.DoubleLinkedList;
import dataStructure.list.impl.List;
import dataStructure.list.impl.Position;

/**
Let L be a List of objects colored either red, green, or blue.
Design an in-place algorithm sortRBG(L) that places all red objects in list L before the blue colored objects,
and all the blue objects before the green objects. Thus the resulting List will have all the red objects followed by the blue objects, followed by the green objects.
Hint: use the method swapElements to move the elements around in the List. To receive full credit,
you must use positions for traversal, e.g., first, last, after, before, swapElements, etc. which is necessary to make it in-place.
*/
public class SortRBG {
  public static void main(String[] args) {
    List<Character> L = new DoubleLinkedList<>();
    L.insertLast('G');
    L.insertLast('B');
    L.insertLast('G');
    L.insertLast('R');
    L.insertLast('B');
    L.insertLast('R');

    Comparator<Character> C = new RGBComparator();

    List<Character> result = sortRBG(L, C);
    System.out.println(result);
  }

  static List<Character> sortRBG(List<Character> L, Comparator<Character> C) {
    if (L.size() < 2) return L;

    inPlaceQuickSort(L, L.first(), L.last(), C);

    return L;
  }

  static void inPlaceQuickSort(List<Character> L, Position<Character> lo, Position<Character> hi, Comparator<Character> C) {
    if (lessThan(L, lo, hi)) {
      Position<Character>[] p = inPlacePartition(L, lo, hi, C);
      inPlaceQuickSort(L, lo, L.before(p[0]), C);
      inPlaceQuickSort(L, L.after(p[1]), hi, C);
    }
  }
  static Position<Character>[] inPlacePartition(List<Character> L, Position<Character> lo, Position<Character> hi, Comparator<Character> C) {
    Position<Character> l = lo;
    Position<Character> temp = lo;
    Position<Character> r = hi;
    Character pivot = lo.element();
    while (lessThanOrEqual(L, temp, r)) {
      if (C.isLess(temp.element(), pivot)) {
        L.swapElement(temp , l);
        l = L.after(l);
        temp = L.after(temp);
      } else if (C.isGreater(temp.element(), pivot)) {
        L.swapElement(temp , r);
        r = L.before(r);
      } else {
        temp = L.after(temp);
      }
    }
    return new Position[] { l, r };
  }

  static boolean lessThanOrEqual(List<Character> L, Position<Character> a, Position<Character> b) {
    Position<Character> p = L.first();
    int i1 = 0;
    int i2 = 0;
    int i = 0;
    while (p.element() != null) {
      if (p.element() == a.element()) {
        i1 = i;
      }
      if (p.element() == b.element()) {
        i2 = i;
      }
      p = L.after(p);
      i++;
    }
    return i1 <= i2;
  }
  static boolean lessThan(List<Character> L, Position<Character> a, Position<Character> b) {
    Position<Character> p = L.first();
    int i1 = 0;
    int i2 = 0;
    int i = 0;
    while (p.element() != null) {
      if (p.element() == a.element()) {
        i1 = i;
      }
      if (p.element() == b.element()) {
        i2 = i;
      }
      p = L.after(p);
      i++;
    }
    return i1 < i2;
  }
}

/**
 * Algorithm sortRBG(L, C)
 *  Input: List of objects (R,G,B) and a comparator C
 *  Ouput: List of sorted objects
 *  
 *  if L.size() < 2 then
 *    return L
 *  inPlaceQuickSort(L, L.first(), L.last(), C)
 *  return L
 * 
 * Algorithm inPlaceQuickSort(L, lo, hi, C)
 *  if lessThan(L, lo, hi) then
 *    (p1, p2) <- inPlacePartition(L, lo, hi, C)
 *    inPlaceQuickSort(L, lo, L.before(p1), C)
 *    inPlaceQuickSort(L, L.after(p2), hi, C)
 * 
 * Algorithm inPlacePartition(L, lo, hi, C)
 *  l <- lo
 *  temp <- lo
 *  r <- hi
 *  pivot <- lo.element()
 *  while lessThanOrEqual(L, temp, r) do
 *    if C.isLess(temp.element(), pivot) then
 *      L.swapElement(temp , l)
 *      l <- L.after(l)
 *      temp <- L.after(temp)
 *    else if C.isGreater(temp.element(), pivot) then
 *      L.swapElement(temp , r)
 *      r <- L.before(r)
 *    else
 *      temp <- L.after(temp)
 *  return new Position[] { l, r }
 * 
 * Algorithm lessThanOrEqual(L, a, b)
 *  p <- L.first()
 *  i1 <- 0
 *  i2 <- 0
 *  i <- 0
 *  while p.element() != null do
 *    if p.element() = a.element() then
 *      i1 <- i
 *    if p.element() = b.element() then
 *      i2 <- i
 *    p <- L.after(p)
 *    i <- i + 1
 *  return i1 <= i2
 * 
 * Algorithm lessThan(L, a, b)
 *  p <- L.first()
 *  i1 <- 0
 *  i2 <- 0
 *  i <- 0
 *  while p.element() != null do
 *    if p.element() = a.element() then
 *      i1 <- i
 *    if p.element() = b.element() then
 *      i2 <- i
 *    p <- L.after(p)
 *    i <- i + 1
 *  return i1 < i2
 */