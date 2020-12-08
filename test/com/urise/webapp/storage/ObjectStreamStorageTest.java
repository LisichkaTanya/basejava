package com.urise.webapp.storage;

import static org.junit.Assert.*;

public class ObjectStreamStorageTest extends AbstractStorageTest{

    public ObjectStreamStorageTest() {
        super(new ObjectStreamStorage(STORAGE_DIRECTORY));
    }
}