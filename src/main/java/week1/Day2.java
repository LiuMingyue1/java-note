package week1;


/**
 * Collection
 *      ordered ->  List
 *                      1. ArrayList -> based on Object[]
 *                             1. insert to head of arraylist -> O(N)
 *                             2. insert to the end of arraylist -> O(1) / O(N)
 *                             3. get by index -> O(1)
 *                      2. LinkedList -> based on LinkedNode / Node
 *                             1. insert to head / tail -> O(1)
 *                             2. insert to middle -> O(N)
 *                                      find position : loop from head / tail to that index
 *                                      insert to that index
 *                             3. get by index -> O(N)
 *                                      find position : loop from head / tail to that index
 *                  TreeMap /  TreeSet
 *                      1. based on red black tree
 *                      2. time complexity O(LogN)
 *                  Queue / Deque / Stack
 *                  PriorityQueue
 *                      1. heap: offer / poll
 *                      2. for loop -> no order
 *                  LinkedHashMap
 *      unordered  -> HashSet / HashMap
 *                  HashMap
 *                      1. insert / update / delete -> O(1)
 *
 *      fail safe / fail fast(modCount)
 *
 */