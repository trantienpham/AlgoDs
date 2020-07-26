package dataStructure.queue.impl;

import dataStructure.queue.impl.exceptions.QueueEmptyException;
import dataStructure.queue.impl.exceptions.QueueFullException;

public class LinkedQueue<T> implements Queue<T> {
  LinkedElement<T> front;
  LinkedElement<T> rear;
  int size = 0;

  public LinkedQueue() {
    this.front = new LinkedElement<T>(null);
    this.rear = new LinkedElement<T>(null);
    this.front.setNext(rear);
    this.rear.setPrev(front);
    this.size = 0;
  }

  @Override
  public void enqueue(T e) throws QueueFullException {
    LinkedElement<T> newE = new LinkedElement<T>(e, this.rear.getPrev(), this.rear);
    
    LinkedElement<T> p = this.rear.getPrev();
    p.setNext(newE);
    this.rear.setPrev(newE);
    size++;
  }

  @Override
  public T dequeue() throws QueueEmptyException {
    if (empty()) throw new QueueEmptyException();
    
    LinkedElement<T> p = this.front.getNext();
    this.front.setNext(p.getNext());
    p.getNext().setPrev(p.getPrev());
    p.setNext(null);
    p.setPrev(null);
    size--;

    return p.element();
  }

  @Override
  public T front() throws QueueEmptyException {
    if (empty()) throw new QueueEmptyException();
    return this.front.getNext().element();
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean empty() {
    return this.front.getNext() == this.rear && this.rear.getPrev() == this.front;
  }

  @Override
  public String toString() {
    StringBuilder buidler = new StringBuilder();
    if (!this.empty()) {
      buidler.append("[");
      LinkedElement<T> temp = this.front.getNext();
      while (temp != this.rear) {
        buidler.append(temp.element() + ", ");
        temp = temp.getNext();
      }
      buidler.delete(buidler.length() - 2, buidler.length());
      buidler.append("]");
    }
    return buidler.toString();
  }

  public static void main(String[] args) {
    LinkedQueue<Integer> q = new LinkedQueue<>();
    try {
      q.enqueue(1);
      q.enqueue(2);
      System.out.println(q);
      int e = q.dequeue();
      System.out.println(e);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
/*
Algorithm enqueue(e)
  newE <- new LinkedElement(e, rear.prev, rear)

  p <- rear.prev
  p.next = newE
  rear.prev = newE
  size <- size + 1

Algorithm dequeue()
  if empty() then
    throw QueueEmptyException
  
  p <- front.prev
  front.next <- p.next
  p.next.prev <- p.prev
  p.next <- null
  p.prev <- null
  size <- size - 1
  return p.element()

Algorithm front()
  if empty() then
    throw QueueEmptyException
  
  return front.next

Algorithm isEmpty()
  return front.next = rear /\ rear.prev = front

Algorithm size()
  return size
*/