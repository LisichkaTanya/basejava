package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public abstract class AbstractArrayStorage implements Storage {
    protected final static int STORAGE_LIMIT = 100_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("Have no this resume '" + uuid + "'");

        }
        return null;
    }

    /**
     * This method should check whether the object is already in storage
     *
     * @return number of index if resume in storage and return "-1" if resume is not in storage
     */
    protected abstract int getIndex(String uuid);
}
