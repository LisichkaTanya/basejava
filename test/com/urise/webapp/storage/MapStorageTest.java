package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.Test;
import static org.junit.Assert.*;


public class MapStorageTest extends AbstractStorageTest {

    public MapStorageTest() {
        super(new MapStorage());
    }
}