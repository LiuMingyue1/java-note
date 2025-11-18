package week2;

import java.lang.annotation.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 *  CompletableFuture
 */
class Day5CompletableFutureExample {
    private static ExecutorService pool = Executors.newCachedThreadPool();

    public static void func1() throws Exception {
        Future<Integer> futureRes = pool.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {}
            System.out.println(Thread.currentThread().getName());
            return 5;
        });
        int res = futureRes.get(); //block main thread
        System.out.println(res + "," + Thread.currentThread().getName());
    }

    public static void func2() {
        CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {}
                    System.out.println(Thread.currentThread().getName());
                    return 5;
                }, pool)
                .thenApply(x -> x * 2)
                .thenAccept(System.out::println)
                .join();
    }

    public static void func3() {
        int res1 = endpointAPI(1000);
        System.out.println(res1);
        int res2 = endpointAPI(2000);
        System.out.println(res2);
        int res3 = endpointAPI(1000);
        System.out.println(res3);
    }

    public static void func4() {
        List<Future<Integer>> futureList = new ArrayList<>();
        futureList.add(pool.submit(() -> endpointAPI(1000)));
        futureList.add(pool.submit(() -> endpointAPI(2000)));
        futureList.add(pool.submit(() -> endpointAPI(1000)));
        List<Integer> ans = futureList.stream()
                .map(f -> {
                    int val = 0;
                    try {
                        val = f.get();
                        return val;
                    } catch (Exception ex) {}
                    return val;
                })
                .collect(Collectors.toList());
        System.out.println(ans);
    }

    public static void func5() {
        List<CompletableFuture<Integer>> futureList = new ArrayList<>();
        futureList.add(CompletableFuture.supplyAsync(() -> endpointAPI(1000), pool));
        futureList.add(CompletableFuture.supplyAsync(() -> endpointAPI(2000), pool));
        futureList.add(CompletableFuture.supplyAsync(() -> endpointAPI(1000), pool));
        //solution1
//        List<Integer> ans1 = futureList.stream()
//                .map(CompletableFuture::join)
//                .collect(Collectors.toList());
//        System.out.println(ans1);

        //solution2
        List<Integer> ans2 = CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0]))
                .thenApply(Void -> futureList.stream().map(CompletableFuture::join).toList())
                .orTimeout(1100, TimeUnit.MILLISECONDS)
                .handle((res, ex) -> {
                    if(ex != null) {
                        return null;
                    }
                    return res;
                })
                .exceptionally(ex -> null)
                .join();
        System.out.println(ans2);
    }

    private static int endpointAPI(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException ex) {}
        return sleepTime;
    }

    public static void main(String[] args) {
//        func2();
//        System.out.println("this is main function");
        func5();
    }
}

/**
 *  Design Patterns
 *      Singleton
 *          1. keep 1 instance in memory -> reuse same instance
 *          2. save memory usage
 */
class ReflectionIssue {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = EagerLoadingThreadSafeSingleton.class;
        Constructor[] constructors = clazz.getDeclaredConstructors();
        Constructor constructor = constructors[0];
        System.out.println(constructor);
        constructor.setAccessible(true);
        EagerLoadingThreadSafeSingleton instance = (EagerLoadingThreadSafeSingleton) constructor.newInstance();
        System.out.println(instance == EagerLoadingThreadSafeSingleton.getInstance());
    }
}
class EagerLoadingThreadSafeSingleton {
    private static final EagerLoadingThreadSafeSingleton instance = new EagerLoadingThreadSafeSingleton();
    private EagerLoadingThreadSafeSingleton() {}
    public static EagerLoadingThreadSafeSingleton getInstance() {
        return instance;
    }
}
class LazyLoadingThreadSafeSingleton {
    private static volatile LazyLoadingThreadSafeSingleton instance;
    private LazyLoadingThreadSafeSingleton() {}
    public static LazyLoadingThreadSafeSingleton getInstance() {
        //t1, t2, t3
        //t4 -> instance is not null
        if(instance == null) {
            //t1, t2, t3
            synchronized (LazyLoadingThreadSafeSingleton.class) {
                //t2 acquire lock
                //t1 acquire lock  -> instance is not null
                if(instance == null) {
                    //t2
                    instance = new LazyLoadingThreadSafeSingleton();
                }
            }
            //t2 release lock
        }
        return instance;
    }
}
enum EnumSingleton {
    INSTANCE1;

    public static void main(String[] args) {
        EnumSingleton instance = EnumSingleton.INSTANCE1;
    }
}
/**
 *      Factory
 *          1. loose coupling
 *          2. hidde initialization
 */
class Day5Student {
    public static Day5Student getStudent() {
        return new Day5Student();
    }
}

/**
 *      Builder
 *          StringBuilder().append()
 *          1. dynamic
 *          2. flexible
 */
class Day5Student2 {
    private String name;
    private int id;

    public Day5Student2() {
    }

    public Day5Student2(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Day5Student2 setName(String name) {
        this.name = name;
        return this;
    }

    public int getId() {
        return id;
    }

    public Day5Student2 setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "Day5StudentBuilder1{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public static void main(String[] args) {
        Day5Student2 stu1 = new Day5Student2().setId(1).setName("Tom");
        System.out.println(stu1);
    }
}

class Day5StudentBuilder2 {
    private String name;
    private int id;

    public Day5StudentBuilder2 setName(String name) {
        this.name = name;
        return this;
    }

    public Day5StudentBuilder2 setId(int id) {
        this.id = id;
        return this;
    }
    public Day5Student2 build() {
        return new Day5Student2(name, id);
    }

    public static void main(String[] args) {
        Day5Student2 stu = new Day5StudentBuilder2().setId(5).setName("Tom").build();
    }
}

/**
 *      Prototype
 *      Strategy
 */
@FunctionalInterface
interface Calculator {
    int execute(int a, int b);
}
class Day5Calculator1 {
    public int calculate(Calculator calculator, int a, int b) {
        return calculator.execute(a, b);
    }
    public static void main(String[] args) {
        Day5Calculator1 cal = new Day5Calculator1();
        System.out.println(cal.calculate((a, b) -> a + b, 5, 10));
    }
}
/**
 *      Composition
 */
class Day5Calculator2 {
    private Calculator calculator;

    public Day5Calculator2() {
        this.calculator = (a, b) -> a + b;
    }

    public int calculate(int a, int b) {
        return calculator.execute(a, b);
    }
}
/**
 *      Observer
 */
class Day5Topic {
    private final List<Day5Subscriber> subscribers = new ArrayList<>();
    public void subscribe(Day5Subscriber subscriber) {
        subscribers.add(subscriber);
    }
    public void publish(String msg) {
        subscribers.forEach(s -> s.receive(msg));
    }
}
class Day5Subscriber {
    public void receive(String msg) {
        System.out.println(msg);
    }
}
/**
 *      Facade
 *      Adapter
 */
interface Day5Func1 {
    void print();
}
class Day5Func1Impl1 implements Day5Func1 {
    @Override
    public void print() {
        System.out.println("this is day5 func1 impl1");
    }
}
interface Day5Func2 {
    void print();
}
class MyAdapter implements Day5Func2 {
    private final Day5Func1 instance;

    public MyAdapter(Day5Func1 instance) {
        this.instance = instance;
    }

    @Override
    public void print() {
        System.out.println("this is my day 5 adapter");
        instance.print();
    }

    public static void main(String[] args) {
        new MyAdapter(new Day5Func1Impl1()).print();
    }
}

/**
 *      Static Proxy / Decorator
 */
interface Day5Car {
    void print();
    void get();
}
class Day5CarImpl1 implements Day5Car {
    @MyAnnotation(val = "dddddd")
    public void print() {
        System.out.println("this is car");
    }

    @Override
    public void get() {
        System.out.println("this is get");
    }
}
class Day5CarInheritanceProxy extends Day5CarImpl1 implements Day5Car{
    public void print() {
        System.out.println("before");
        super.print();
        System.out.println("after");
    }
}
//-----------------------
class Day5CarCompositionProxy implements Day5Car {
    private final Day5CarImpl1 obj;

    public Day5CarCompositionProxy(Day5CarImpl1 obj) {
        this.obj = obj;
    }

    @Override
    public void print() {
        System.out.println("before");
        obj.print();
        System.out.println("after");
    }

    @Override
    public void get() {
        //..
    }
}
/**
 *      Dynamic Proxy
 */
class MyInvocationHandler implements InvocationHandler {
    private final Object obj;
    public MyInvocationHandler(Object obj) {
        this.obj = obj;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before");
        Object res = method.invoke(obj, args);
        System.out.println("after");
        return res;
    }
}
class TestDynamicProxyDay5 {
    public static void main(String[] args) {
        Day5Car car = (Day5Car) Proxy.newProxyInstance(
                TestDynamicProxyDay5.class.getClassLoader(),
                new Class[]{Day5Car.class},
                new MyInvocationHandler(new Day5CarImpl1())
        );
        car.print();
        car.get();
    }
}

/**
 * reflection, annotation + method
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
    String val() default "abc";
}
class ReflectionDay5 {
    @MyAnnotation
    private String a;
    public static void main(String[] args) throws Exception {
        Day5CarImpl1 car = new Day5CarImpl1();
        car.print();

        Class<?> clazz = Day5CarImpl1.class;
        Method method = clazz.getDeclaredMethod("print");
//        method.invoke(null);

        Annotation[] annotation = method.getDeclaredAnnotations();
        MyAnnotation myAnnotation = (MyAnnotation)annotation[0];
        System.out.println(myAnnotation.val());
    }
}
