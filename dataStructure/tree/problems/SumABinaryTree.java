package dataStructure.tree.problems;

import dataStructure.tree.impl.binary.*;
import dataStructure.tree.impl.*;

public class SumABinaryTree {
  public int sum(BinaryTree<Integer> T) {
    Position<Integer> r = T.root();

    return sumHelper(T, r);
  }

  public int sumHelper(BinaryTree<Integer> T, Position<Integer> p) {
    if (T.isExternal(p)) {
      return (int) p.element();
    }

    int left = sumHelper(T, T.leftChild(p));
    int right = sumHelper(T, T.rightChild(p));

    return left + right + ((int) p.element());
  }

  public static void main(String[] args) {
    BinaryTree<Integer> T = new LinkedBinaryTree<>();
    T.add(2);
    T.add(5);
    T.add(6);
    T.add(9);
    T.add(7);

    int sum = new SumABinaryTree().sum(T);
    System.out.println(sum);
  }
}

/**
Algorithm sum(T)
  r <- T.root()
  return sumHelper(T, r)

Algorithm sumHelper(T, p)
  if T.isExternal(p) then
    return p.element()
  
  left <- sumHelper(T, T.leftChild(p))
  right <- sumHelper(T, T.rightChild(p))

  return left + right + p.element()
*/