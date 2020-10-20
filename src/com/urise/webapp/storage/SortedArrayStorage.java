package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Sorted array based storage for Resumes
 */

public class SortedArrayStorage extends AbstractArrayStorage {

    public void save(Resume resume) {
        if (size >= storage.length) {
            System.out.println("Storage is full");
        } else {
            int index = getIndex(resume.getUuid());
            if (index > 0) {
                System.out.println("This resume '" + resume.getUuid() +"' is already exists");
            } else {
                index = -(index) - 1;
                System.arraycopy(storage, index, storage, index + 1, size);
                storage[index] = resume;
                size++;
            }
        }
    }


    protected int getIndex(String uuid) {
        Resume resume = new Resume();
        resume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, resume);
    }

}
