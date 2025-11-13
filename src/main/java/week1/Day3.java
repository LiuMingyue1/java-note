package week1;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * Thread
 *  1. new Thread(runnable)
 *  2. extends Thread + override run function
 *  3. thread pool -> thread
 *
 * LifeCycle
 *  1. new thread -> runnable stage
 *  2. running stage
 *  3. sleep -> doesn't release lock
 *  4. wait -> release lock + put thread into waiting list
 *  5. destroy
 */

/**
 *  Thread Safe
 *      1. race condition
 *
 *  Cpu1            Cpu2
 *  read num = 0    read num = 0
 *  num + x         num + x
 *  write it        write it back to main memory
 *
 *      main memory
 *
 *      2. dead lock
 *          T1 lock A, try to get lock B
 *          T2 lock B, try to get lock A
 *
 *        solution1: lock in order
 *        solution2: setup timeout
 *        solution3: lookup table
 *
 */
class ThreadSafety1 {
    private static int num = 0;
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 10000; i++) {
                num++;
            }
        });
        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 10000; i++) {
                num++;
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(num);
    }
}
/**
 * volatile
 *  1. read from shared memory
 *                    at t2
 *                      |
 *   timeline  ----------------------->
 *               |
 *          write at t1
 *
 *  2. write the value back to shared memory
 *  3. barrier / fence
 *
 *  cpu1                cpu2
 *  read num
 *  num+1
 *                      read num=0
 *  write it
 *
 *
 *      num = 0 -> 1
 *
 */
class ThreadSafety2 {
    private static volatile int num = 0;
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 10000; i++) {
                num++;
            }
        });
        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 10000; i++) {
                num++;
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(num);
    }
}
/**
 * CAS operation parameters
 *  1. object address
 *  2. field address
 *  3. old value / expected value
 *  4. new value
 *  5. return true / false
 *
 * Atomic Library
 *  1. Atomic Integer
 *          do {
 *             v = getIntVolatile(o, offset); //get newest current value in that field
 *                <- other threads have executed CAS successfully
 *         } while (!weakCompareAndSetInt(o, offset, v, newValue));
 *
 */
class ThreadSafety3 {
    private static final AtomicInteger num = new AtomicInteger(0);
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            for(int i = 0; i < 10000; i++) {
                num.getAndAdd(1);
            }
        });
        Thread t2 = new Thread(() -> {
            for(int i = 0; i < 10000; i++) {
                num.getAndAdd(1);
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(num);
    }
}
/**
 *  Synchronized
 *      1. scope is object
 *      2. synchronized static -> Class object
 *      3. synchronized non-static -> This object (new instance)
 *  1 CPU -> Context switching
 *
 *   t1 -----   ---       --
 *   t2      ---      --
 *   t3            ---  --
 */
class SyncExample {
    public void get1() {
        synchronized(this) {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
            }
        }
    }
    public synchronized void get2() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(3000);
        } catch (InterruptedException ex) {}
    }
    public static void main(String[] args) throws Exception {
        SyncExample ins1 = new SyncExample();
        SyncExample ins2 = new SyncExample();
        Thread t1 = new Thread(() -> ins1.get2());
        Thread t2 = new Thread(() -> ins2.get2());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("finished");
    }
}
/**
 * Concurrent HashMap
 */

/**
 * ReentrantLock
 *     1. state : 0 unlock or >= 1 locked
 *     2. CurrentLockHolder : Thread
 *     3. if thread is not owner + cannot acquire lock
 *          enqueue thread in a LinkedList by using CAS
 *          park that thread
 *
 *      Unfair Lock
 *      lock owner t1 (holding lock)   waiting list t2 <-> t3 <-> t5
 *      1. t1 release lock -> signal t2
 *      2. t2 exists parking stage -> try acquire
 *         t6 run lock() at same time -> t6 may get lock before t2
 *
 *      Fair Lock
 *      lock owner t1 (holding lock)   waiting list t2 <-> t3 <-> t5
 *      1. t1 release lock -> signal t2
 *      2. t2 exists parking stage -> try acquire
 *         t6 run lock() at same time ->
 *              t6 will check if waiting list is empty
 *              if LinkedList is not empty
 *                  enqueue t6
 *              else
 *                  t6 try lock
 *
 *       Multiple Waiting List
 *       1. lock.newCondition()
 *
 *       ReadWrite Lock
 *       read lock not blocking read lock , read lock blocks write lock
 *       write lock blocks everything(write + read)
 *
 *       TryLock()
 *       1. return true if acquire lock successfully
 *       2. return false means , cannot get lock
 *
 *       Lock multiple times
 *       1. if the thread is lock owner , thread can lock multiple times
 *
 * AbstractQueuedSynchronizer
 *      1. Thread safe LinkedList
 */



/**
 * Blocking Queue
 *
 * producer(t1) -> blocking queue -> consumer(worker threads)
 *
 * Object[] queue = new Object[]
 * for producer logic:
 *      lock.lock();
 *      try {
 *          while(queue is full) {
 *              producer.wait();
 *          }
 *          add object in array
 *          notify consumer waiting list
 *      } finally {
 *          lock.unlock();
 *      }
 *
 * for consumer logic:
 *      lock.lock();
 *      try {
 *          while(queue is empty) {
 *              consumer.wait();
 *          }
 *          remove one object from array
 *          notify producer waiting list
 *          return obj
 *      } finally {
 *          lock.unlock();
 *      }
 */





