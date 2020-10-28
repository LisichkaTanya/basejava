package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final Resume r1 = new Resume(UUID_1);
    private static final String UUID_2 = "uuid2";
    private static final Resume r2 = new Resume(UUID_2);
    private static final String UUID_3 = "uuid3";
    private static final Resume r3 = new Resume(UUID_3);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.save(r1);
        storage.save(r2);
        storage.save(r3);
    }

    @After
    public void tearDown() {
        storage.clear();
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }

    @Test
    public void getAll() {
        Resume[] resumes = new Resume[]{r1, r2, r3};
        assertArrayEquals(resumes, storage.getAll());
    }

    @Test
    public void get() {
        assertEquals(r2, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("dummy");
    }

    @Test
    public void save() {
        Resume r4 = new Resume("uuid4");
        storage.save(r4);
        assertEquals(4, storage.size());
        assertEquals(r4, storage.get("uuid4"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() {
        storage.save(r3);
    }

    @Test(expected = StorageException.class)
    public void saveOverFlow() {
        try {
            for (int i = 3; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            fail("overflow happened ahead of time");
        }
        storage.save(new Resume());
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1);
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(storage.get("dummy"));
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        assertEquals(2, storage.size());
        storage.get(UUID_2);
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.getAll().length);
    }

}