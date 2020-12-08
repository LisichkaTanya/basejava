package com.urise.webapp.storage;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.io.ObjectInputStream;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ListStorageTest.class,
        MapUuidStorageTest.class,
        MapResumeStorageTest.class,
        ObjectInputStream.class
})
public class AllStorageTest {
}
