package algorithms.selectionsort;

public class SelectionSort {
  public static void asc(int[] A) {
    for (int i = 0; i < A.length; i++) {
      int minIndex = i;
      for (int j = i + 1; j < A.length; j++) {
        if (A[j] < A[minIndex]) {
          minIndex = j;
        }
      }
      swap(A, i, minIndex);
    }
  }
  public static void desc(int[] A) {
    for (int i = 0; i < A.length; i++) {
      int bigIndex = i;
      for (int j = i + 1; j < A.length; j++) {
        if (A[j] > A[bigIndex]) {
          bigIndex = j;
        }
      }
      swap(A, i, bigIndex);
    }
  }

  static void swap(int[] A, int i, int j) {
    int temp = A[i];
    A[i] = A[j];
    A[j] = temp;
  }
}