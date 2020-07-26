package dataStructure.tree.impl.binary;

import java.util.ArrayList;
import java.util.Iterator;

import dataStructure.tree.impl.Position;

public class LinkedBinaryTree<T> implements BinaryTree<T> {
  protected LinkedNode<T> root = null;
  protected LinkedNode<T> last = null;
  private int size = 0;

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean empty() {
    return size == 0;
  }

  @Override
  public Iterator<T> elements() {
    return null;
  }

  @Override
  public Iterator<Position<T>> positions() {
    return null;
  }

  @Override
  public Position<T> root() {
    return root;
  }

  @Override
  public Position<T> parent(Position<T> p) {
    LinkedNode<T> vv = checkPosition(p);
    Position<T> parentPos = vv.parent();
    if (parentPos == null)
      throw new Error("No parent");
    return parentPos;
  }

  @Override
  public Iterator<Position<T>> children(Position<T> p) {
    ArrayList<Position<T>> list = new ArrayList<>();
    if (hasLeft(p))
      list.add(leftChild(p));
    if (hasRight(p))
      list.add(rightChild(p));
    return list.iterator();
  }

  @Override
  public boolean isExternal(Position<T> p) {
    return !isInternal(p);
  }

  @Override
  public boolean isInternal(Position<T> p) {
    checkPosition(p);
    return (hasLeft(p) || hasRight(p));
  }

  @Override
  public boolean isRoot(Position<T> p) {
    return false;
  }

  @Override
  public void swapElements(Position<T> p, Position<T> q) {
    T temp = p.element();
    replaceElement(p, q.element());
    replaceElement(q, temp);
  }

  @Override
  public Position<T> replaceElement(Position<T> p, T e) {
    LinkedNode<T> vv = checkPosition(p);
    vv.setElement(e);
    return vv;
  }

  @Override
  public Position<T> leftChild(Position<T> p) {
    LinkedNode<T> vv = checkPosition(p);
    Position<T> leftPos = vv.left();
    if (leftPos == null)
      throw new Error("No left child");
    return leftPos;
  }

  @Override
  public Position<T> rightChild(Position<T> p) {
    LinkedNode<T> vv = checkPosition(p);
    Position<T> rightPos = vv.right();
    if (rightPos == null)
      throw new Error("No right child");
    return rightPos;
  }

  @Override
  public Position<T> sibling(Position<T> p) {
    LinkedNode<T> vv = checkPosition(p);
    LinkedNode<T> parentPos = vv.parent();
    if (parentPos != null) {
	    LinkedNode<T> sibPos;
	    LinkedNode<T> leftPos = parentPos.left();
	    if (leftPos == vv)
	      sibPos = parentPos.right();
	    else
	      sibPos = parentPos.left();
	    if (sibPos != null)
	      return sibPos;
    }
    throw new Error("No sibling");
  }

  @Override
  public void add(T e) {
  }

  @Override
  public void addRoot(T e) {
    root = new LinkedNode<T>(e);
  }

  @Override
  public void addLeft(T e) {
    
  }

  protected LinkedNode<T> checkPosition(Position<T> v) {
    if (v == null || !(v instanceof LinkedNode))
      throw new Error("The position is invalid");
    return (LinkedNode<T>) v;
  }

  protected boolean hasLeft(Position<T> p) { 
    LinkedNode<T> vv = checkPosition(p);
    return (vv.left() != null);
  }
  protected boolean hasRight(Position<T> p) { 
    LinkedNode<T> vv = checkPosition(p);
    return (vv.right() != null);
  }
}