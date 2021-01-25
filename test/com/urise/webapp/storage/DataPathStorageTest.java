package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.DataStreamSerialization;

public class DataPathStorageTest extends AbstractStorageTest {

    public DataPathStorageTest() {
        super(new PathStorage(STORAGE_DIRECTORY.getAbsolutePath(), new DataStreamSerialization()));
    }
}