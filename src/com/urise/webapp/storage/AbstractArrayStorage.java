package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public abstract class AbstractArrayStorage implements Storage {
    protected final static int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;


    public int size() {
        return size;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Have no this resume '" + uuid + "'");
            return null;
        }
        return storage[index];
    }

    public void save(Resume resume) {
        if (size >= storage.length) {
            System.out.println("Storage is full");
        } else {
            int index = getIndex(resume.getUuid());
            if (index >= 0) {
                System.out.println("This resume '" + resume.getUuid() + "' is already exists");
            } else {
                addElement(resume, index);
                size++;
            }
        }
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Have no this resume '" + resume.getUuid() + "' to update");
        } else {
            storage[index] = resume;
            System.out.println("Update '" + storage[index] + "' is completed");
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Have no this resume '" + uuid + "' to delete");
        } else {
            removeElement(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /**
     * This method should check whether the object is already in storage
     */
    protected abstract int getIndex(String uuid);

    protected abstract void addElement(Resume resume, int index);

    protected abstract void removeElement(int index);
}
