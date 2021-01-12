package com.urise.webapp;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Deadlock {
    public static void main(String[] args) {
        final Lock lock1 = new ReentrantLock();
        final Lock lock2 = new ReentrantLock();
        Thread thread1 = new Thread(() -> runDeadlock(lock1, lock2));
        Thread thread2 = new Thread(() -> runDeadlock(lock2, lock1));

        thread1.start();
        thread2.start();
    }

    static void runDeadlock(Lock firstLock, Lock secondLock) {
        synchronized (firstLock) {
            System.out.println("Hold " + Thread.currentThread().getName() + ", wait " + secondLock);
            synchronized (secondLock) {
                System.out.println("Hold both locks");
            }
        }
    }
}


