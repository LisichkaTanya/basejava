package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private final Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void save(Resume resume) {
        if (size <= storage.length) {
            if (getIndex(resume.getUuid()) == -1) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("This resume '" + resume.getUuid() +"' is already exists");
            }
        } else {
            System.out.println("storage is full");
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Have no this resume '" + resume.getUuid() + "' to update");
        } else {
            storage[index] = resume;
            System.out.println("Update '" + storage[index] + "' is completed");
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index != -1) {
            if (size - 1 - index >= 0) {
                System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            }
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Have no this resume '" + uuid + "' to delete");
        }
    }

    /**
     * This method checks whether the object is already in storage
     *
     * @return number of index if resume in storage and return "-1" if resume is not in storage
     */
    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
