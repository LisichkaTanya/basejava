package com.urise.webapp.util;

public class LazySingleton {

    volatile private static LazySingleton INSTANCE;
    double sin = Math.sin(13.);

    private LazySingleton() {
    }

    private static class LazySingletonHolder {
        private static final LazySingleton INSTANCE = new LazySingleton();
    }

    //Это лучшая замена паттерну double-check-locking, и это рабочая версия
    public static LazySingleton getInstance() {
        return LazySingletonHolder.INSTANCE;
    }

    //Чтобы при многопоточном использовании не создалось несколько INSTANCE-ов, используем synchronized
    //недостаток, что очень долго ждать очереди
//    public static synchronized LazySingleton getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new LazySingleton();
//        }
//        return INSTANCE;
//    }

    //Поэтому будем использовать паттерн double-check-locking
    //Но такая модель тоже не рабочая, если у нас есть еще переменные, например double sin;
//    public static LazySingleton getInstance() {
//        if (INSTANCE == null) {
//            synchronized (LazySingleton.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new LazySingleton();
//                }
//            }
//        }
//        return INSTANCE;
//    }
}
