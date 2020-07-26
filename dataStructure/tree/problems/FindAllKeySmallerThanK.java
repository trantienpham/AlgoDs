package dataStructure.tree.problems;

import dataStructure.tree.impl.Position;
import dataStructure.tree.impl.binary.Vector;
import dataStructure.tree.impl.binary.ArrayVector;
import dataStructure.tree.impl.binary.ArrayVectorBinaryTree;

public class FindAllKeySmallerThanK {
  public Vector<Integer> findKeySmallerThan(ArrayVectorBinaryTree<Integer> T, int x) {
    Vector<Integer> V = new ArrayVector<>();
    Position<Integer> root = T.root();
    return findKeySmallerThanHelper(T, root, x, V);
  }
  public Vector<Integer> findKeySmallerThanHelper(ArrayVectorBinaryTree<Integer> T, Position<Integer> p, int x, Vector<Integer> V) {
    if (p.element() <= x) {
      V.insertAtRank(p.element(), V.size());
      if (T.isInternal(p)) {
        findKeySmallerThanHelper(T, T.leftChild(p), x, V);
        findKeySmallerThanHelper(T, T.rightChild(p), x, V);
      }
    }
    return V;
  }

  public static void main(String[] args) {
    ArrayVectorBinaryTree<Integer> T = new ArrayVectorBinaryTree<>();
    T.add(2);
    T.add(5);
    T.add(6);
    T.add(9);
    T.add(7);
    System.out.println(T);

    Vector<Integer> result = new FindAllKeySmallerThanK().findKeySmallerThan(T, 7);

    System.out.println(result);
  }
}

/**
Algorithm findKeySmallerThan(T, k)
  V <- new Vector
  root <- T.root()
  return findKeySmallerThanHelper(T, root, k, V)

Algorithm findKeySmallerThanHelper(T, p, k, V)
  if p.key <= k then
    V.insertAtRank(p.key, V.size - 1)

    if T.isInternal(p) then
      findKeySmallerThanHelper(T, T.leftChild(p), k, V);
      findKeySmallerThanHelper(T, T.rightChild(p), k, V);

  return V
*/