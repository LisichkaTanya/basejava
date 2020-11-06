package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Sorted array based storage for Resumes
 */

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void addElement(Resume resume, Integer index) {
        Integer insertIdx = -index - 1;
        System.arraycopy(storage, insertIdx, storage, insertIdx + 1, size - insertIdx);
        storage[insertIdx] = resume;
    }

    @Override
    protected void removeElement(Integer index) {
        System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
    }

    @Override
    protected Integer getSearchKey(String uuid) {
        Resume resume = new Resume(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }

}
