package com.urise.webapp;


public class Deadlock {
    public static void main(String[] args) {
        final String lock1 = "lock1";
        final String lock2 = "lock2";
        runDeadlock(lock1, lock2);
        runDeadlock(lock2, lock1);
    }

    static void runDeadlock(String firstLock, String secondLock) {
        new Thread(() -> {
            synchronized (firstLock) {
                System.out.println(Thread.currentThread().getName() + " hold " + firstLock + " and wait " + secondLock);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (secondLock) {
                    System.out.println("Hold both locks");
                }
            }
        }).start();
    }
}


