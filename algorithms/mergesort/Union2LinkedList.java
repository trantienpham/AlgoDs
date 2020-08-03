package algorithms.mergesort;

/**
 * 2 linked list can contain duplicated numbers, how to merge and create a new list with unique numbers
 * step 1: merge 2 list => using merge sort
 * step 2: remove duplicate => use 2 while loop to find and remove
 */
public class Union2LinkedList {
  
}

/**
Algorithm merge(L1, L2)
  D <- new Dictionary
  while !L1.empty() do
    e <- D.findElement(L1.first().element())
    if e = NO_SUCH_KEY then
      k <- L1.remove(L1.first())
      D.insertElement(k, k)
  while !L2.empty() do
    e <- D.findElement(L2.first().element())
    if e = NO_SUCH_KEY then
      k <- L2.remove(L2.first())
      D.insertElement(k, k)
  return D.elements()
*/