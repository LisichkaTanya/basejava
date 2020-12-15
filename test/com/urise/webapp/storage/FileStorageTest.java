package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.ObjectStreamSerialization;

import java.io.File;


public class FileStorageTest extends AbstractStorageTest {
    public FileStorageTest() {
        super(new FileStorage(new File(STORAGE_DIRECTORY), new ObjectStreamSerialization()));
    }
}