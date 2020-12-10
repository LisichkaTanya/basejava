package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.urise.webapp.ResumeTestData.creatResume;
import static org.junit.Assert.assertEquals;

public abstract class AbstractStorageTest {
    protected static final String STORAGE_DIRECTORY = "C:\\Project\\basejava\\storage";
    protected final Storage storage;
    private static final String UUID_1 = "uuid1";
    private static final Resume r1 = creatResume(UUID_1, "name1");
    private static final String UUID_2 = "uuid2";
    private static final Resume r2 = creatResume(UUID_2, "name2");
    private static final String UUID_3 = "uuid3";
    private static final Resume r3 = creatResume(UUID_3, "name3");


    public AbstractStorageTest(Storage storage) {
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
    public void save() {
        Resume r4 = creatResume("uuid4","name4");
        storage.save(r4);
        assertEquals(4, storage.size());
        assertEquals(r4, storage.get("uuid4"));
    }

    @Test(expected = ExistStorageException.class)
    public void saveAlreadyExist() {
        storage.save(r3);
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "name1");
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(storage.get("dummy"));
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
    public void getAllSorted() {
        List<Resume> actualResumes = storage.getAllSorted();
        assertEquals(Arrays.asList(r1, r2, r3), actualResumes);
        assertEquals(3, actualResumes.size());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_2);
        assertEquals(2, storage.size());
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete("dummy");
    }

    @Test
    public void clear() {
        storage.clear();
        assertEquals(0, storage.getAllSorted().size());
    }

    @Test
    public void size() {
        assertEquals(3, storage.size());
    }
}