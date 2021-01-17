package com.urise.webapp;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainConcurrency {
    private static int counter;
    private final AtomicInteger atomicCounter = new AtomicInteger();
    //Объект, к которому мы будем ставить потоки в очередь для синхронизации
//    private static final Object LOCK = new Object();
    public static final int THREADS_NUMBER = 10000;
    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static final Lock WRITE_LOCK = reentrantReadWriteLock.writeLock();
    private static final Lock READ_LOCK = reentrantReadWriteLock.readLock();

//        private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
//        @Override
//        protected SimpleDateFormat initialValue() {
//            return new SimpleDateFormat();
//        }
//    };
//    можно написать через лямбду:
    private static final ThreadLocal<SimpleDateFormat> threadLocal =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("dd.MM.yyyy HH:mm:ss"));
//    А лучше использовать потокобезопасный DateTimeFormatter Java 8 Time API:
//    private static final DateTimeFormatter threadLocal = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        // Первый способ создания потока
        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
//                throw new IllegalStateException();
            }
        };
        thread0.start();

        //Второй способ (через new Runnable()) более предпочтительный, так как получается мы передаем как параметр,
        //а не переопределяем метод.
        new Thread(() -> System.out.println(Thread.currentThread().getName() +
                ", " + Thread.currentThread().getState())).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();

        //вместо создания листа с Thread-ами и join-иться к ним мы можем использовать CountDownLatch
        //latch - это замок с обратным отсчетом
//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);
//        for (int i = 0; i < THREADS_NUMBER; i++) {
//            Thread thread = new Thread(() -> {
//                for (int j = 0; j < 100; j++) {
//                    mainConcurrency.increment();
//                }
//            });
//            thread.start();
//            threads.add(thread);
//        }
//
//        //Поставим ожидание, пока наш поток завершит заполнять counter, и только потом выведем на экран
////        Thread.sleep(500);
//        threads.forEach(t -> {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });

        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
        ExecutorService executorService = Executors.newCachedThreadPool();
        //Если мы подгружаем html страницу с картинками, чтобы картинки показывались по мере подгружения
        //можно использовать CompletionService, это интерфейс и он позволяет брать первую законченную задачу(task)
        //то есть покажется первая загруженная картинка
//        CompletionService completionService = new ExecutorCompletionService(executorService);
//        completionService.poll(); //возвращает Future
        for (int i = 0; i < THREADS_NUMBER; i++) {
//            Thread thread = new Thread(() -> {
            Future<Integer> future = executorService.submit(() -> {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.increment();
                    System.out.println(threadLocal);
                }
                latch.countDown();
                return 5;
            });
//            thread.start();
        }
        latch.await();
        executorService.shutdown();
        System.out.println(mainConcurrency.atomicCounter);
    }

    //Если мы пишем synchronized в сигнатуре статического метода (перед void), то это значит мы встаем в очередь(waitSet)
    // к объекту класса, в котором метод, то есть тоже самое, что synchronized(MainConcurrency.class) {}
    private void increment() {
//        synchronized(LOCK) {
//        counter++;
//        }
        atomicCounter.incrementAndGet();
    }
}
