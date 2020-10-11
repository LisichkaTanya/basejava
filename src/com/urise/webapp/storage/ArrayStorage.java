package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    public boolean checkIsEmpty() {
        if(size == 0) {
            System.out.println("Storage is empty!");
            return true;
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    public void save(Resume r) {
        if(size <= 10000) {
            for (int i = 0; i <= size; i++) {
                if(storage[i] == null) {
                    storage[i] = r;
                    size++;
                    break;
                }
                if (storage[i].getUuid().equals(r.getUuid())) {
                    System.out.println("This resume is already exists");
                    break;
                }
            }
        } else {
            System.out.println("storage is full");
        }
    }

    public void update(Resume resume) {
        if(!checkIsEmpty()) {
            for (int i = 0; i < size; i++) {
                if (resume.equals(storage[i])) {
                    storage[i] = resume;
                    System.out.println("The update " + storage[i] + " is completed");
                    break;
                }
            }
        }
    }

    public Resume get(String uuid) {
        Resume resume = null;
        if(!checkIsEmpty()) {
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].getUuid())) {
                    resume = storage[i];
                    break;
                }
            }
        }
        return resume;
    }

    public void delete(String uuid) {
        if(!checkIsEmpty()) {
            for (int i = 0; i < size; i++) {
                if (uuid.equals(storage[i].getUuid())) {
                    if (i == size - 1) {
                        storage[i] = null;
                    } else {
                        while (i < size - 1) {
                            storage[i] = storage[i + 1];
                            i++;
                        }
                    }
                }
            }
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        for (int i = 0; i < size; i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    public int size() {
        return size;
    }
}
