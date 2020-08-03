package algorithms.insertionsort;

import java.util.Arrays;

public class InsertionSort {
  public static void main(String[] args) {
    int[] A = new int[] {5,4,3,2,1};
    asc(A);
    System.out.println(Arrays.toString(A));

    A = new int[] {1,2,3,4,5};
    desc(A);
    System.out.println(Arrays.toString(A));
  }

  public static void asc(int[] A) {
    for (int i = 1; i < A.length; i++) {
      int temp = A[i];
      int j = i;
      for (j = i; j > 0; j--) {
        if (A[j - 1] > temp) {
          A[j] = A[j - 1];
        }
      }
      A[j] = temp;
    }
  }
  public static void desc(int[] A) {
    for (int i = 1; i < A.length; i++) {
      int temp = A[i];
      int j = i;
      for (j = i; j > 0; j--) {
        if (A[j - 1] < temp) {
          A[j] = A[j - 1];
        }
      }
      A[j] = temp;
    }
  }
}