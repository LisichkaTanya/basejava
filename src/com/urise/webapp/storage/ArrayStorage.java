package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addElement(Resume resume, Integer index) {
        storage[size] = resume;
    }

    @Override
    protected void removeElement(Integer index) {
        storage[index] = storage[size - 1];
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer)searchKey >= 0;
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

}
