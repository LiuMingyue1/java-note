package week1;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 *  ThreadPool + ExecutorService / Executors
 *          1. ThreadPoolExecutor(diff parameters)
 *              fixedThreadPool : core pool size == max pool size
 *              cachedThreadPool :
 *              scheduledThreadPool
 *          2. ForkJoinPool
 *                  [][][][][][][]  -> worker1 [a1][a2][][][]
 *                                     worker2 [a2]
 *
 *  Thread usage = service calculation + IO block
 *
 *  task1: 100% calculation / cpu usage -> thread pool size = cpu core number + 1
 *
 *  task2 80% io block + 20% calculation
 *          for 1 thread -> 1s -> 0.2s on calculation , 0.8s wait for io response
 *          1 cpu core : 1 / 0.2 = 5 threads + 1(backup)
 *
 *  java21 : virtual thread
 *  non block server / non blocking io / nodejs / netty :
 *      task -> event loop -> worker thread
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  CountDownLatch
 *  Semaphore
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  new HashMap<>()
 *  1. load HashMap class object into jvm
 *  2. create hashmap instance
 *  HashMap.class
 *  Class.forName();
 *
 *  class XX {
 *      {
 *
 *      }
 *  }
 *
 *
 *  Java 8 new features
 *      1. default method in interface
 *      2. HashMap -> red black tree
 *      3. Functional interface + Lambda Expression
 *              Function interface: one input, one output ,    (x) -> {},   x -> x + 3
 *              Consumer interface: one input, no output ,     x -> System.out.println(x)
 *              Supplier interface: no input, one output ,    () -> new HashMap<>()
 *              Predicate interface: one input, boolean output  x -> x > 3
 *              Runnable : no input, no output -> Thread
 *              Callable : no input, one output -> ThreadPool
 *      4. Stream API
 *              intermediate operation
 *              map(Function)
 *              filter(Predicate)
 *              sorted(Comparator)
 *              distinct()
 *              flatMap(List::stream):   Stream<List<List<Integer>>> -> Stream<List<Integer>>
 *              ...
 *
 *              Terminal Operation
 *              collect(Collectors.toList())
 *              collect(Supplier, BiConsumer, BiConsumer)
 *              reduce(T, BiFunction)
 *              forEach(Consumer)
 *
 *              list.stream()
 *                 .map(x -> x + 1)
 *                 .distinct()
 *                 .sorted((a, b) -> b - a)
 *                 .collect(Collectors.toList());
 *
 *             ReferencePipeline1(() -> list.iterator())
 *                      |
 *             ReferencePipeline2(map..)
 *                      |
 *                     ..
 *                     |
 *                  collect
 *
 *
 *            Sink -> Sink -> Sink -> Sink
 *      5. Method Reference
 *      6. Optional
 *      7. parallel stream
 */
class Day4StreamTest {
    //plus 1 on each elem
    public static void func1(List<Integer> list) {
        if(list == null) {
            throw new IllegalArgumentException("input is not valid");
        }
//        list.stream().map(new Function<Integer, Integer>() {
//            @Override
//            public Integer apply(Integer integer) {
//                return integer + 1;
//            }
//        }).forEach(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) {
//                System.out.println(integer);
//            }
//        });
        int ans = list.stream().map(x -> x + 1).reduce(0, (res, ele) -> res + ele);
        System.out.println(ans);

        List<Integer> ansList = list.stream()
                .map(x -> x + 1)
                .distinct()
                .sorted((a, b) -> b - a)
                .collect(Collectors.toList());
        System.out.println(ansList);


        Map<Integer, Long> ansMap1 = list.stream()
                .map(x -> x + 1)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(ansMap1);

        Map<Integer, Integer> ansMap2 = list.stream()
                .map(x -> x + 1)
                .collect(
                        HashMap::new,
                        (finalMap, ele) -> finalMap.merge(ele, 1, Integer::sum),
                        (m1, m2) -> {}
                );
        System.out.println(ansMap2);


        Optional<Integer> res = list.stream()
                .distinct()
                .map(x -> x + 1)
                .filter(x -> x > 0)
                .findFirst();
        System.out.println(res.orElse(5));

    }

    public static void func2() {
        int[] arr = {1, 2, 3};
        List<Integer> list1 = Arrays.stream(arr)
                .map(x -> x + 2)
                .mapToObj(x -> x)
                .collect(Collectors.toList());

        List<Integer> list2 = IntStream.range(0, arr.length)
                .map(i -> arr[i])
                .mapToObj(x -> x)
                .collect(Collectors.toList());

        String str = "abc";

       str.chars()
               .mapToObj(c -> (char)c)
               .collect(Collectors.toList())
               .forEach(System.out::println);

    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
//        func1(list);
        func2();
    }
}



@FunctionalInterface
interface Day4FunctionalInterfaceTest {
    void get();
}
class Day4FITest {
    public static void main(String[] args) {
        Day4FunctionalInterfaceTest d1 = new Day4FunctionalInterfaceTest() {
            @Override
            public void get() {
                System.out.println("535");
            }
        };
        d1.get();

        Day4FunctionalInterfaceTest d2 = () -> System.out.println("535");
        d2.get();
    }
}


class Test {
    static {
        System.out.println("abc");
    }

    public static void main(String[] args) {
        new Test();
    }
}