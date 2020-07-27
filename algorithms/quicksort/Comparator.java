package algorithms.quicksort;

public interface Comparator<T> {
  boolean isLess(T a, T b);
  boolean isLessOrEqualTo(T a, T b);
  boolean isEqualTo(T a, T b);
  boolean isGreater(T a, T b);
  boolean isGreaterOrEqualTo(T a, T b);
  boolean isComparable(T a);
}