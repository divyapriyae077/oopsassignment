package assignment;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.TimeUnit;

class TryLockExample {
    private final ReentrantLock lock = new ReentrantLock();

    public void task(String name) {
        System.out.println(name + " is trying to get the lock...");

        try {
            // Try to acquire the lock within 2 seconds
            if (lock.tryLock(2, TimeUnit.SECONDS)) {
                try {
                    System.out.println(name + " acquired the lock.");
                    Thread.sleep(1000); // Simulate some work
                } finally {
                    lock.unlock();
                    System.out.println(name + " released the lock.");
                }
            } else {
                System.out.println(name + " could not get the lock, timed out.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Assignment3 {
    public static void main(String[] args) {
        TryLockExample example = new TryLockExample();

        Thread t1 = new Thread(() -> example.task("Thread 1"));
        Thread t2 = new Thread(() -> example.task("Thread 2"));

        t1.start();
        t2.start();
    }
}
