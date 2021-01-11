package com.urise.webapp;

import java.util.ArrayList;
import java.util.List;

public class MainConcurrency {
    private static int counter;
    //Объект, к которому мы будем ставить потоки в очередь для синхронизации
    private static final Object LOCK = new Object();
    public static final int THREADS_NUMBER = 10000;

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        // Первый способ создания потока
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
            }
        };
        thread0.start();

        //Второй способ (через new Runnable()) более предпочтительный, так как получается мы передаем как параметр,
        //а не переопределяем метод.
        new Thread(() -> System.out.println(Thread.currentThread().getName() +
                ", " + Thread.currentThread().getState())).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
        for (int i = 0; i < THREADS_NUMBER; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.increment();
                }
            });
            thread.start();
            threads.add(thread);
        }

        //Поставим ожидание, пока наш поток завершит заполнять counter, и только потом выведем на экран
//        Thread.sleep(500);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(counter);
    }

    //Если мы пишем synchronized в сигнатуре статического метода (перед void), то это значит мы встаем в очередь(waitSet)
    // к объекту класса, в котором метод, то есть тоже самое, что synchronized(MainConcurrency.class) {}
    private synchronized void increment() {
//        synchronized(LOCK) {
        counter++;
//        }
    }
}
