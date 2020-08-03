package algorithms.mergesort;

import java.util.Arrays;

public class SortAnArray {
  public static void main(String[] args) {
    int[] A = new int[] { 1, 4, 7, 2, 8, 5, 6, 9, 3 };
    int[] result = mergeSort(A);
    System.out.println(Arrays.toString(result));
  }

  static int[] mergeSort(int[] A) {
    int[] S = new int[A.length]; 
    mergeSort(A, 0, A.length - 1, S);
    return A;
  }
  static void mergeSort(int[] A, int lo, int hi, int[] S) {
    if ((hi - lo + 1) > 1) {
      int mid = (int) Math.floor((hi + lo)/2);
      mergeSort(A, lo, mid, S);
      mergeSort(A, mid + 1, hi, S);
      merge(A, lo, mid, hi, S);
    }
  }
  static void merge(int[] A, int lo, int mid, int hi, int[] S) {
    int size = hi - lo + 1;
    int t = 0;
    int j = lo;
    int k = mid + 1;

    while (j <= mid && k <= hi) {
      if (A[j] > A[k]) {
        S[t] = A[k];
        k++;
      } else {
        S[t] = A[j];
        j++;
      }
      t++;
    }

    while (j <= mid) {
      S[t] = A[j];
      j++;
      t++;
    }

    while (k <= hi) {
      S[t] = A[k];
      t++;
      k++;
    }

    for (int i = 0; i <= size - 1; i++) {
      A[lo + i] = S[i];
    }
  }
}

/**
Algorithm mergeSort(A)
  S <- new Sequence
  mergeSort(A, 0, A.size() - 1, S)
  return A

Algorithm mergeSort(A, lo, hi, S)
  if (hi - lo + 1) > 1 then
    mid <- floor((hi + lo) / 2)
    mergeSort(A, lo, mid, S)
    mergeSort(A, mid + 1, hi, S)
    merge(A, lo, mid, hi, S)

Algorithm merge(A, lo, mid, hi, S)
  size <- hi - lo + 1
  t <- 0;
  j <- lo;
  k <- mid + 1

  while j <= mid /\ k <= hi do
    if A[j] > A[k] then
      S[t] <- A[k]
      k <- k + 1
    else
      S[t] <- A[j]
      j <- j + 1
    t <- t + 1

  while j <= mid do
    S[t] <- A[j];
    j <- j + 1
    t <- t + 1

  while k <= hi do
    S[t] <- A[k]
    k <- k + 1
    t <- t + 1

  for i <- 0 to size do
    A[lo + i] <- S[i]

Algorithm mergeSort(S)
  if S.size() > 1 then
    (S1, S2) <- partition(S, n/2)
    mergeSort(S1)
    mergeSort(S2)
    merge(S1, S2, S)

Algorithm merge(S1, S2, S)
  while !S1.empty() /\ S2.empty() do
    if B.first().element() < A.first().element() then
      S.insertLast(B.remove(B.first()))
    else
      S.insertLast(A.remove(A.first()))
  
  while !A.empty() do
    S.insertLast(A.remove(A.first()))

  while !B.empty() do
    S.insertLast(B.remove(B.first()))
*/