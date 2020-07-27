package algorithms.quicksort;

import dataStructure.sequence.impl.Position;

import java.util.Arrays;

import dataStructure.sequence.impl.ArraySequence;
import dataStructure.sequence.impl.Sequence;

/**
 * Suppose we are given an n-element sequence S such that each element in S
 * represents a different vote in an election, where each vote is given as an
 * integer representing the ID of the chosen candidate. Without making any
 * assumptions about who is running or even how many candidates there are,
 * design an O(n log n)-time algorithm to see who wins the election S
 * represents, assuming the candidate with the most votes wins.
 */
public class WinElection {
  public static void main(String[] args) {
    Sequence<Integer> S = new ArraySequence<>(100);
    S.insertLast(2);
    S.insertLast(2);
    S.insertLast(5);
    S.insertLast(1);
    S.insertLast(1);
    S.insertLast(1);
    S.insertLast(7);
    S.insertLast(9);

    int winnerID = findWinnerElection(S);
    System.out.println(winnerID);
  }

  static Integer findWinnerElection(Sequence<Integer> S) {
    if (S.empty())
      return null;
    if (S.size() < 2)
      return S.last().element();

    inPlacePartition(S, 0, S.size() - 1);

    Sequence<int[]> D = new ArraySequence<>(100);

    while (!S.empty()) {
      int e = S.remove(S.first()).element();
      if (D.empty()) {
        D.insertLast(new int[] { e, 1 });
      } else {
        if (e == D.last().element()[0]) {
          D.last().element()[1]++;
        } else {
          D.insertLast(new int[] { e, 1 });
        }
      }
    }

    Integer winnerID = null;
    int max = 0;

    while (!D.empty()) {
      Position<int[]> p = D.remove(D.first());
      if (p.element()[1] > max) {
        winnerID = p.element()[0];
        max = p.element()[1];
      }
    }

    return winnerID;
  }

  // quick sort for duplicate key
  static void inPlaceQuickSort(Sequence<Integer> S, int lo, int hi) {
    if (lo >= hi)
      return;

    int[] p = inPlacePartition(S, lo, hi);
    inPlaceQuickSort(S, lo, p[0] - 1);
    inPlaceQuickSort(S, p[1] + 1, hi);
  }

  static int[] inPlacePartition(Sequence<Integer> S, int lo, int hi) {
    int l = lo, temp = lo, r = hi;
    int pivot = S.elementAtRank(lo).element();

    while (temp <= r) {
      if (S.elementAtRank(temp).element() < pivot) {
        S.swapElement(S.elementAtRank(temp), S.elementAtRank(l));
        temp++;
        l++;
      } else if (S.elementAtRank(temp).element() > pivot) {
        S.swapElement(S.elementAtRank(temp), S.elementAtRank(r));
        r--;
      } else {
        temp++;
      }
    }
    return new int[] { l, r };
  }
}

/**
 * Algorithm findWinnerElection(S)
 *  if S.empty() then return null if S.size() < 2
 *    then return S.last().element()
 * 
 *  inPlacePartition(S, 0, S.size() - 1)
 * 
 *  D <- new Sequence;
 * 
 *  while !S.empty() do e <- S.remove(S.first()).element()
 *    if D.empty() then
 *      D.insertLast({ e, 1 })
 *    else if e == D.last().element()[0] then
 *      D.last().element()[1] <- D.last().element()[1] + 1 else D.insertLast({e, 1})
 * 
 *  winnerID <- null
 *  max <- 0
 * 
 *  while !D.empty() do
 *    p <- D.remove(D.first())
 *    if p.element()[1] > max then
 *      winnerID <- p.element()[0]
 *      max <- p.element()[1]
 * 
 *  return winnerID
 * 
 * Algorithm inPlaceQuickSort(S, lo, hi)
 *  if lo >= hi then
 *    return
 *  (p1, p2) <- inPlacePartition(S, lo, hi)
 *  inPlaceQuickSort(S, lo, p1 - 1)
 *  inPlaceQuickSort(S, p2 + 1, hi)
 * 
 * // For duplicates
 * Algorithm inPlacePartition(S, lo, hi)
 *  l <- lo
 *  r <- hi
 *  temp <- lo
 *  pivot <- S.elementAtRank(lo).element()
 *  while temp <= r do
 *    if S.elementAtRank(temp).element() < pivot then
 *      S.swapElement(S.elementAtRank(temp), S.elementAtRank(l));
 *      l <- l + 1;
 *      temp <- temp + 1
 *    else if S.elementAtRank(temp).element() > pivot then
 *      S.swapElement(S.elementAtRank(temp), S.elementAtRank(r));
 *      r <- r - 1;
 *    else
 *      temp <- temp + 1
 *      
 *  return (l, r)
 * 
 * // For non-duplicates
 * Algorithm inPlacePartition(S, lo, hi)
 *  l <- lo
 *  r <- hi - 1
 *  pivot <- S.elementAtRank(hi).element()
 *  while l <= r then
 *    while l <= r /\ S.elementAtRank(l).element() <= pivot then
 *      l <- l + 1
 *    while l <= r /\ S.elementAtRank(r).element() >= pivot then
 *      r <- r -1
 *    if l < r then
 *      S.swapElement(S.elementAtRank(l), S.elementAtRank(r));
 *  S.swapElement(S.elementAtRank(l), S.elementAtRank(hi));
 *  return l
 */