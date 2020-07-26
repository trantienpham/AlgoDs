package dataStructure.tree.impl.binary;

import java.util.ArrayList;
import java.util.Iterator;

import dataStructure.tree.impl.Position;

public class ArrayVectorBinaryTree<T> implements BinaryTree<T> {
  Vector<T> V = new ArrayVector<T>();
  int size = 0;
  ArrayNode<T> last;

  public ArrayVectorBinaryTree() {
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean empty() {
    return V.empty();
  }

  @Override
  public Iterator<T> elements() {
    ArrayList<T> eles = new ArrayList<>();
    for (int r = 0; r < size; r++) {
      eles.add(V.elementAtRank(r).element());
    }
    return eles.iterator();
  }

  @Override
  public Iterator<Position<T>> positions() {
    ArrayList<Position<T>> pos = new ArrayList<>();
    for (int r = 1; r <= V.size(); r++) {
      pos.add(V.elementAtRank(r));
    }
    return pos.iterator();
  }

  @Override
  public Position<T> root() {
    return V.elementAtRank(0);
  }

  @Override
  public Position<T> parent(Position<T> p) {
    int r = rankOf(p);
    return V.elementAtRank((r - 1)/2);
  }

  @Override
  public Iterator<Position<T>> children(Position<T> p) {
    ArrayList<Position<T>> children = new ArrayList<>();
    if (isInternal(p)) {
      children.add(leftChild(p));
      children.add(rightChild(p));
    }
    return children.iterator();
  }

  @Override
  public boolean isExternal(Position<T> p) {
    return !isInternal(p);
  }

  @Override
  public boolean isInternal(Position<T> p) {
    return leftChild(p) != null && rightChild(p) != null;
  }

  @Override
  public boolean isRoot(Position<T> p) {
    return rankOf(p) == 0;
  }

  @Override
  public void swapElements(Position<T> p, Position<T> q) {
    T temp = p.element();
    replaceElement(p, q.element());
    replaceElement(q, temp);
  }

  @Override
  public Position<T> replaceElement(Position<T> p, T e) {
    int r = rankOf(p);
    return V.replaceAtRank(e, r);
  }

  @Override
  public Position<T> leftChild(Position<T> p) {
    int r = rankOf(p);
    if ((r * 2 + 1) >= size()) return null;
    return V.elementAtRank(r * 2 + 1);
  }

  @Override
  public Position<T> rightChild(Position<T> p) {
    int r = rankOf(p);
    if ((r * 2 + 2) >= size()) return null;
    return V.elementAtRank(r * 2 + 2);
  }

  @Override
  public Position<T> sibling(Position<T> p) {
    int r = rankOf(p);
    if (r < 0 || r > size()) throw new Error("Invalid position");

    int parentR = (r - 1)/2;
    if ((parentR * 2 + 1) == r) {
      return V.elementAtRank(parentR * 2 + 2);
    }
    return V.elementAtRank(parentR * 2 + 1);
  }
  
  public void add(T e) {
    last = new ArrayNode<>(e, size);
    V.insertAtRank(e, size);
    size++;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    if (!empty()) {
      builder.append("[");
      for (int i = 0; i < size(); i++) {
        Position<T> p = V.elementAtRank(i);
        builder.append(p.element() + ", ");
      }
      builder.delete(builder.length() - 2, builder.length());
      builder.append("]");
    }
    return builder.toString();
  }

  private int rankOf(Position<T> p) {
    ArrayNode<T> node = (ArrayNode<T>) p;
    int r = node.index();
    if (r < 0 || r > size()) throw new Error("Invalid position");
    return node.index();
  }
}

/**
Algorithm root()
  return V.elemAtRank(0)

Algorithm parent(v)
  return V.elemAtRank((p(v) - 1)/2)

Algorithm leftchild(v)
  return V.elemAtRank(2p(v) + 1)

Algorithm rightchild(v)
  return V.elemAtRank(2p(v) + 2)

Algorithm isInternal(v):
  return leftchild(v) != null /\ rightchild(v) != null

Algorithm isExternal(v):
  return !T.isInternal(v)

Algorithm isRoot(v):
  return v = root()
*/