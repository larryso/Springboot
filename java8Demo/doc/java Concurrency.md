# Java Concurrency

## Syncchronized Keyword in Java
The traditional way to achieve thread synchronization in java is by using synchronized keyword.
We can use the synchronized keyword on different levels:
* Instance method
* static methods
* code blocks
When we use a synchronized block, java internally used a Monitor, also known as a monitor lock or intrinsic lock to provide syncrhoniztion. These monitors are bound to an Object, therefore, all synchronized blocks of the same object can have only one thread executing them at the same time.

### Synchrronized instance methods
```java
public class SynchronizedMethods {
    private int sum = 0;
    public synchronized void synchronizedCalculate(){
        setSum(getSum()+1);
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }
}

    public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        SynchronizedMethods summation = new SynchronizedMethods();
        IntStream.range(0, 1000).forEach(count -> service.submit(summation::synchronizedCalculate));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);
        System.out.println(summation.getSum());
    }
```
### synchronized static methods
```java
    public static synchronized void synchronizedCalculate(){
        setSum(getSum()+1);
    }

public static void main(String[] args) throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        IntStream.range(0, 1000).forEach(count -> service.submit(SynchronizedMethods::synchronizedCalculate));
        service.awaitTermination(1000, TimeUnit.MILLISECONDS);
        System.out.println(summation.getSum());
        }
```
Static synchronized method are synchronized on the Class Object associated with the class, since only one Class Object exists per JVM per class, only one thread can execute inside a static synchronized method per class.
当synchronized作用于静态方法时，其锁就是当前类的class对象锁。由于静态成员不专属于任何一个实例对象，是类成员，因此通过class对象锁可以控制静态 成员的并发操作。需要注意的是如果一个线程A调用一个实例对象的非static synchronized方法，而线程B需要调用这个实例对象所属类的静态 synchronized方法，是允许的，不会发生互斥现象，因为访问静态 synchronized 方法占用的锁是当前类的class对象，而访问非静态 synchronized 方法占用的锁是当前实例对象锁，

### Synchronized Blocks within Methods
```java
public void performSynchronizedTask(){
        synchronized (this){
            setSum(getSum()+1);
        }
    }

```
We passed a parameter this to the Synchronized block, this is the monitor object, the code inside the block gets synchronized on the monitor object, only one thread per monitor object can execute inside the code block.
if the method was static, we would pass the class name in place of the object reference. and the class would be a monitor for synchonization of the block.
```
public void performSynchronizedTask(){
        synchronized (SynchronizedMethods.class){
            setSum(getSum()+1);
        }
    }
```
## Reentrant Lock in Java
The ReentrantLock class implements the Lock interface and provides synchronization to methods while accessing shared resources. The code which manipulates the shared resource is surrounded by calls to lock and unlock method.
This gives a lock to the current working thread and blocks all other threads which are trying to take a lock on the shared resource.

Like Synchronized, ReetrantLock allows threads to enter into the lock on a resource more than once, When the thread first enters into the lock, a hold count is set to one. Before unlocking the thread can re-enter into lock again and every time hold count is incremented by one. For every unlocks request, hold count is decremented by one and when hold count is 0, the resource is unlocked.
重入锁，同一个线程，首次获得锁，在释放锁前，可以多次获取这把锁，同时也要释放相同的次数，否则其他线程无法进入临界区。
```java
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "c.Test")
public class Test {

    // 创建锁重入对象
    private static ReentrantLock lock = new ReentrantLock();
    
    public static void main(String[] args) throws InterruptedException {
        
        // 加锁
        lock.lock();
        try {
            log.debug("enter  main");
            m1();
        } finally {
            // 解锁
            lock.unlock();
        }
    }
    
    public static void m1() {
        // 加锁
        lock.lock();
        try {
            log.debug("enter  m1");
            m2();
        } finally {
            // 解锁
            lock.unlock();
        }
    }
    
    public static void m2() {
        // 加锁
        lock.lock();
        try {
            log.debug("enter  m2");
        } finally {
            // 解锁
            lock.unlock();
        }
    }
}

```
Reentrant Locks also offer a fairness parameter, by which the lock would abide by the order of the lock request i.e. after a thread unlocks the resource, the lock would go to the thread which has been waiting for the longest time. This fairness mode is set up by passing true to the constructor of the lock.
公平锁是指当锁可用时,在锁上等待时间最长的线程将获得锁的使用权。而非公平锁则随机分配这种使用权。和synchronized一样，默认的ReentrantLock实现是非公平锁,因为相比公平锁，非公平锁性能更好。当然公平锁能防止饥饿,某些情况下也很有用。在创建ReentrantLock的时候通过传进参数true创建公平锁,如果传入的是false或没传参数则创建的是非公平锁
`ReentrantLock lock = new ReentrantLock(true);`

## ThreadLocal in Java
ThreadLocal allow us to store data that will be accessible only by a specific thread.
Let's say we want to have an integer value that will be bundled with the specific thread:
`ThreadLocal<Integer> threadLocalValue = new ThreadLocal<>()`
When we want to use this value from the thread, we only need to call get() or set() method. Simply put, we can imagine that ThreadLocal Stores data inside a map with the thread as the key.
We can construct ThreadLocal by using withInitial() static method:
`ThreadLocal<Integer> threadLocal = ThreadLocithInitial(()->1)`
To remove the value, we can call remove() method.


