package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.JsonStreamSerialization;

public class JsonPathStorageTest extends AbstractStorageTest {

    public JsonPathStorageTest() {
        super(new PathStorage(STORAGE_DIRECTORY, new JsonStreamSerialization()));
    }
}