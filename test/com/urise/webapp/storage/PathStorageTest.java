package com.urise.webapp.storage;

import com.urise.webapp.storage.stream.ObjectStreamStorage;

public class PathStorageTest extends AbstractStorageTest {

    public PathStorageTest() {
        super(new PathStorage(STORAGE_DIRECTORY, new ObjectStreamStorage()));
    }
}