package com.urise.webapp.storage;

import com.urise.webapp.storage.serialization.XmlStreamSerialization;

public class XmlPathStorageTest extends AbstractStorageTest {

    public XmlPathStorageTest() {
        super(new PathStorage(STORAGE_DIRECTORY, new XmlStreamSerialization()));
    }
}