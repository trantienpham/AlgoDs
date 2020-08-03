package algorithms.heapsort;

import java.util.Arrays;

public class HeapSort {
  public static void main(String[] args) {
    // int[] A = new int[] {73, 6, 57, 88, 60, 42, 83, 72, 48, 85};
    // asc(A);
    // System.out.println(Arrays.toString(A));

    int[] B = new int[] {73, 6, 57, 88, 60, 42, 83, 72, 48, 85};
    desc(B);
    System.out.println(Arrays.toString(B));
  }

  public static void asc(int[] A) {
    buildHeap(A);

    int last = A.length - 1;
    while (last > 0) {
      swap(A, 0, last);
      last--;
      maxHeapify(A, last, 0);
    }
  }
  public static void desc(int[] A) {
    buildHeap1(A);

    // int last = A.length - 1;
    // while (last > 0) {
    //   swap(A, 0, last);
    //   last--;
    //   minHeapify(A, last, 0);
    // }
  }

  static void buildHeap(int[] A) {
    int last = A.length - 1;
    int next = last;

    while (next > 0) {
      int parent = (int) Math.floor((next - 1) / 2);
      maxHeapify(A, last, parent);
      next = next - 2;
    }
  }
  static void maxHeapify(int[] A, int last, int r) {
    int largest = rankOfMax(A, r, last);
    if (largest > r) {
      swap(A, r, largest);
      maxHeapify(A, last, largest);
    }
  }
  static int rankOfMax(int[] A, int r, int last) {
    int left = 2 * r + 1;
    int right = left + 1;
    int largest = r;
    if (left <= last && A[left] > A[largest]) {
      largest = left;
    }
    if (right <= last && A[right] > A[largest]) {
      largest = right;
    }
    return largest;
  }

  static void buildHeap1(int[] A) {
    int last = A.length - 1;
    int next = last;

    while (next > 0) {
      int parent = (int) Math.floor((next - 1) / 2);
      minHeapify(A, last, parent);
      next = next - 2;
    }
  }
  static void minHeapify(int[] A, int last, int r) {
    int smallest = rankOfMin(A, r, last);
    if (smallest > r) {
      swap(A, r, smallest);
      minHeapify(A, last, smallest);
    }
  }
  static int rankOfMin(int[] A, int size, int i) {
    int smallest = i;
    int left = 2 * i + 1;
    int right = left + 1;
    if (left <= size && A[left] < A[smallest]) {
      smallest = left;
    }
    if (right <= size && A[right] < A[smallest]) {
      smallest = right;
    }
    return smallest;
  }

  static void swap(int[] A, int i, int j) {
    int temp = A[i];
    A[i] = A[j];
    A[j] = temp;
  }
  // static void upHeap(int[] A, int i) {
  //   int parent = i / 2;
  //   if (parent >= 0 && A[parent] > A[i]) {
  //     swap(A, parent, i);
  //     upHeap(A, parent);
  //   }
  // }
  // static void downHeap(int[] A, int size, int i) {
  //   int smallest = rankOfMin(A, size, i);
  //   if (i < smallest && smallest < size) {
  //     swap(A, i, smallest);
  //     downHeap(A, size, smallest);
  //   }
  // }
}
