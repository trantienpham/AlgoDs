package dataStructure.sequence.problems;

import dataStructure.sequence.impl.Position;
import dataStructure.sequence.impl.Sequence;
import dataStructure.sequence.impl.ArraySequence;

public class EnumeratingAllSubsetsOfTheNumbers {
  public static void main(String[] args) {
    Sequence<Integer> S = new ArraySequence<Integer>(3);
    S.insertLast(1);
    S.insertLast(2);
    S.insertLast(3);

    Sequence<Sequence<Integer>> result = powerSet(S);

    System.out.println(result);
  }

  public static Sequence<Sequence<Integer>> powerSet(Sequence<Integer> S) {
    if (S.empty()) {
      Sequence<Sequence<Integer>> newS = new ArraySequence<Sequence<Integer>>(1);

      newS.insertLast(S);

      return newS;
    }

    Position<Integer> p = S.first();
    S.remove(p);

    Sequence<Sequence<Integer>> newS = powerSet(S);

    return powerSetHelper(p, newS);
  }

  public static Sequence<Sequence<Integer>> powerSetHelper(Position<Integer> p, Sequence<Sequence<Integer>> S) {
    if (S.empty()) {
      Sequence<Sequence<Integer>> newS = new ArraySequence<Sequence<Integer>>(2);

      newS.insertLast(new ArraySequence<Integer>(0));

      Sequence<Integer> newS1 = new ArraySequence<Integer>(1);
      newS1.insertLast(p.element());

      newS.insertLast(newS1);

      return newS;
    }

    Position<Sequence<Integer>> subS = S.last();
    S.remove(subS);
    Sequence<Sequence<Integer>> newS = powerSetHelper(p, S);
    if (!subS.element().empty()) {
      newS.insertLast(subS.element());
      
      Sequence<Integer> clonedSubS = cloneSequence(subS.element());
      clonedSubS.insertLast(p.element());
      
      newS.insertLast(clonedSubS);
    }

    return newS;
  }

  public static Sequence<Integer> cloneSequence(Sequence<Integer> S) {
    Sequence<Integer> newS = new ArraySequence<Integer>(S.size());
    Position<Integer> p = S.first();
    while (p != null) {
      newS.insertLast(p.element());
      p = S.after(p);
    }
    return newS;
  }
}

/**
Algorithm powerSet(S)
  if S.empty() then
    newS <- new Sequence
    newS.insertLast(S)
    return newS

  p <- S.last()
  S.remove(p)
  newS <- powerSet(S)
  
  return powerSetHelper(p, newS)


Algorithm powerSetHelper(p, S)
  if S.empty() then
    newS <- new Sequence
    
    newS.insertLast(S)

    newS1 <- new Sequence
    newS1.insertLast(p)

    newS.insertLast(newS1)

    return newS
  
  subS <- S.last()
  S.remove(subS)
  newS <- powerSetHelper(p, S)
  newS.insertLast(subS)
  clonedSubS <- cloneSequence(subS)
  clonedSubS.insertLast(p)
  newS.insertLast(clonedSubS)

  return newS

Algorithm cloneSequence(S)
  newS <- new Sequence
  
  p <- S.last()
  while !S.isFirst(p) do
    newS.insertLast(p)
    p <- S.before(p)
  
  return newS
*/