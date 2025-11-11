package week1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;
/**
 * renqing.yang@antra.com
 *
 * OOP
 *      polimorphism
 *          List<Integer> list = new ArrayList<>();
 *      abstraction
 *      inheritance
 *          one class extends one parent class
 *          one class impls multiple interfaces
 *          one interface extends multiple interfaces
 *      encapsulation
 *          private
 *          default
 *          protected
 *          public
 * SOLID
 *      Single responsibility
 *      Open close
 *          open to extend
 *          close to modify
 *      Liskov substitution
 *      Interface segregation
 *          example: interface {50 functions}
 *                   parent interface {10 functions}
 *                interface 1 extends parent interface
 *                interface 2 extends interface 1
 *      Dependency inversion
 *          new Car().drive()
 *          class B {
 *              private final Car car;
 *              //constructor injection
 *          }
 * Object class
 *      toString()
 *      clone()
 *      equals()
 *      hashcode()
 *      wait + notify (in synchronize block)
 *
 * primitive type vs Object type(instance)
 * primitive type: int, char, boolean, long, double, float
 *      int: 0
 *      char: ..
 *      boolean : false
 *      long : 0
 *      ..
 * Object type: Integer, Character, Boolean, Long, Double, Float
 *      default value: null
 *
 */
class ObjectExample1 {
    private static Integer v1 = 128;
    private static Integer v2 = 128; //constant integer pool -128 ~ 127
    private static int v3 = 5, v4 = 5;

    public static int getNumber() {
        return v1;
    }
    public static void main(String[] args) {
        System.out.println(v1);
        int[] arr1 = {1, 2, 3};
        int[] arr2 = new int[]{1, 2, 3};
        System.out.println(Arrays.toString(arr1));

        System.out.println(v3 == v4); //true
        System.out.println(v1 == v2); //false
        System.out.println(v1.equals(v2)); //true
    }
}
/**
 * String comparison
 */
class Day1String {
    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1 == s2);
        String s3 = new String("abc");
        System.out.println(s1 == s3);
    }
}


/**
 * deep clone vs shallow clone
 * Student1
 *      stuTemplate instance
 *      stuTemplate -> stu1
 *      stuTemplate -> stu2
 *
 *      shallow clone ->
 *          stu1 != stu2
 *          stu1.lessonList == stu2.lessonList
 *      deep clone -> stu1.lessonList != stu2.lessonList
 */
class Student1 {
    private final List<Lesson1> lessonList = new ArrayList<>();
}

class Lesson1 {

}


/**
 * equals hashcode
 *
 */
class Teacher1 {
    private String id;

    public Teacher1(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher1 teacher1 = (Teacher1) o;
        return Objects.equals(id, teacher1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static void main(String[] args) {
        Teacher1 t1 = new Teacher1("1");
        Teacher1 t2 = new Teacher1("1");
        System.out.println(t1 == t2);
        System.out.println(t1.equals(t2));
    }
}
/**
 * java passes by value
 */
class Day1JavaPassByValueExample {
    public static void main(String[] args) {
        int val = 10;
        // 00xx88 myList[xx55aa]
        // xx55aa new ArrayList<>();
        List<Integer> myList = new ArrayList<>();  // 00xx88 myList [
        myList.add(1);
        //func(xx55aa, 10)
        func(myList, val);
        System.out.println(myList); // A. [],  B. [1, 5],  C. [1]
        System.out.println(val);   // A. 10,  B. 5,  C. 0
    }
    // 33yy33 list[xx55aa]
    private static void func(List<Integer> list, int v) {
        list.add(5);
        //0000x new ArrayList<>()
        //33yy33 list[0000x]
        list = new ArrayList<>();
        list.add(1);
        v = 5;
    }
}


/**
 * Final
 *      1. final variable
 *              final primitive type
 *              final object
 *      2. final method
 *              cannot override
 *      3. final class
 *              cannot extend
 */
class Day1FinalExample {
    private static final List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        list.add(1);
        System.out.println(list);
    }
}

/**
 * Immutable
 *      1. final class
 *      2. getter, not setter
 *      3. private access modifier
 *      4. deep coup in getter
 *      5. use constructor to do deep copy
 */
class Day1ImmutableClass {
    private final List<Integer> list;
    private final String name;

    public Day1ImmutableClass(List<Integer> list, String name) {
        //deep copy list to this.list
        this.list = list;
        this.name = name;
    }

    public List<Integer> getList() {
        //return deep copy list
        return list;
    }

    public String getName() {
        return name;
    }
}

/**
 *          Throwable
 *        /         \
 *      Error       Exception(checked exception / compile time exception)
 *                      |
 *                   RuntimeException (runtime exception / unchecked exception)
 */
class MyRuntimeException extends RuntimeException {
    public MyRuntimeException(String message) {
        super(message);
    }
}
class ExceptionExample {
    private static void func1() {
        System.out.println("this is func1");
        throw new MyRuntimeException("func1 has some issues");
    }

    private static void func2() throws Exception{
        System.out.println("this is func2");
        throw new Exception("func2 has some issues");
    }

    public static void main(String[] args) throws Exception {
        try {
//            func1();
            func2();
        } catch (Exception ex) {
            throw ex;
        } finally {
            System.out.println("this is finally block");
        }
    }

//    public static int longestSubstringWithNoDup(String input) {
//        if(input == null) {
//            throw new IllegalArgumentException("input cannot be null");
//        }
//        Map
//    }
}
