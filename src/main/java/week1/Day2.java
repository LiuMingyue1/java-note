package week1;

import jdk.swing.interop.SwingInterOpUtils;

import java.lang.reflect.Constructor;
import java.util.*;
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
 *
 *  how does hashmap work
 *       equals
 *       hashcode
 *       check source code
 *
 *  HashMap get/put
 *               Node
 *                |
 *              Node
 *               |
 *      [][][][Node][][][][][][][]  array
 *      Get
 *      1. calculate hashing value based on hashcode
 *      2. calculate index of this key/value pair from array length + hashing value
 *      3. compare key in LinkedList/RedBlackTree
 *            1. ==
 *            2. equals
 *
 *      Put
 *      1. calculate hashing value based on hashcode
 *      2. calculate index of this key/value pair from array length + hashing value
 *      3. compare key in LinkedList/RedBlackTree
 *      4. if we find entry/node with same key -> we replace value in that node
 *      5. if we cannot find this node/entry , append it to the end of the linkedlist
 *         if LinkedList is too long -> convert it to red black tree
 *      6. if total entry size > threshold -> trigger resize
 *
 *
 *      override equals / not hashcode
 *      1. with diff hashcode -> get diff hash value -> diff index of bucket
 *
 *      override hashcode / not equals
 *      1. same hashcode -> same hash value -> same bucket
 *      2. equals(default is ==) -> return false
 */
class Day2Student {
    private String id;
    public Day2Student(String id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "Day2Student{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day2Student that = (Day2Student) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static void main(String[] args) {
        Map<Day2Student, Integer> stuMap = new HashMap<>();
        Day2Student s1 = new Day2Student("1");
        Day2Student s2 = new Day2Student("1");
        stuMap.put(s1, 1);
        System.out.println(stuMap.get(s2)); //return null
    }
}


/**
 *  modCount
 *  ConcurrentModificationException
 *
 *  for each / iterator
 */
class ModCountExample {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(); //modCount = 0
        list.add(1); //modCount = 1
        list.add(2); //modCount = 2
        list.add(3); //modCount = 3
//        Iterator<Integer> itr = list.iterator();
//        while(itr.hasNext()) {
//            int val = itr.next();
//            System.out.println(val);
//            list.remove(0);
//        }
//        System.out.println(list);

        //iterator
//        for(int num: list) { //expectedModCount(is 3) != modCount(is 4)
//            System.out.println(num); //1
//            list.add(4); // modCount++
//        }
//        for (int i = 0; i < list.size(); i++) { //i -> 1
//            System.out.println(list.get(i)); //list[1, 2, 3] print 1
//            if(i == 0) {
//                list.remove(0);//list[2, 3]
//            }
//        }
//        int idx = 0;
//        while(idx < list.size()) {
//            idx++;
//        }
    }
}

/**
 *  javac -> compile .java -> class file
 *  java  -min/max of your area  -XXheapdump  -XXyounggenerationsize=xx  filename -> execute those class file
 *
 *  Heap (shared main memory)
 *      1. store objects / instances
 *
 *      [  eden area  ][s0][s1] Young Generation
 *      [                     ] Old Generation
 *      metaspace
 *
 *      GC
 *          1. minor GC
 *              mirror copying
 *          2. major GC
 *              CMS(concurrent mark and sweep)
 *              1. initial marks (stop the world)
 *              2. mark objects  (concurrent)
 *              2. confirm objects (stop the world)
 *              3. remove objects (concurrent)
 *              mark sweep + compact / compress
 *          3. G1 GC
 *              [][][][][][][][]
 *              [][][][][][][][]
 *              [][][][][][][][]
 *              [][][][][][][][]
 *      out of memory error
 *          1. check memory leak with heap dump
 *              1. generate heap dump
 *              2. open heap dump -> java mission control / Jprofiler / memory analyzer
 *          2. restart application
 *          3. allocate large space for young generation / old generation
 *              vertical scaling
 *          4. space complexity
 *          5. Reference Type
 *              Strong Reference (default ref type)
 *              Soft Reference
 *              Weak Reference
 *              Phantom Reference + Reference Queue(?)
 *
 *
 *
 *
 *
 *  Stack
 *      1. store local value
 *      2. push method frame into stack
 *
 *         dfs(n) -> time complexity -> O(N)
 *                   space complexity ->
 *  Thread 1 : 1 Stack
 *
 */

class HeapStackExample {
    public static void main(String[] args) {
        dfs(3);
    }
    public static void dfs(int input) {
        if(input == 0) {
            return;
        }
        System.out.println(input);
        dfs(input - 1);
    }
}

/**
 * reflection example
 */
class Day2ReflectionExample {
    private int a = 10;

    public Day2ReflectionExample(int a) {
        this.a = a;
    }

    @Override
    public String toString() {
        return "Day2ReflectionExample{" +
                "a=" + a +
                '}';
    }

    public static void main(String[] args) throws Exception {
        Class<Day2ReflectionExample> clazz = Day2ReflectionExample.class;
        System.out.println(clazz);
        Constructor[] constructors = clazz.getDeclaredConstructors();
        Constructor constructor =constructors[0];
        System.out.println(constructor);
        Day2ReflectionExample ins = (Day2ReflectionExample) constructor.newInstance(5);
        System.out.println(ins);
    }
}