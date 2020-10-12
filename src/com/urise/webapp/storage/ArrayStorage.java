package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public boolean checkIsEmpty() {
        if (size == 0) {
            System.out.println("Storage is empty!");
            return true;
        }
        return false;
    }

    public void save(Resume resume) {
        if (size <= storage.length) {
            if (getIndex(resume.getUuid()) == -1) {
                storage[size] = resume;
                size++;
            } else {
                System.out.println("This resume is already exists");
            }
        } else {
            System.out.println("storage is full");
        }
    }

    public void update(Resume resume) {
        if (!checkIsEmpty()) {
            int index = getIndex(resume.getUuid());
            if (index == -1) {
                System.out.println("Have no this resume to update");
            } else {
                storage[index] = resume;
                System.out.println("Update " + storage[index] + " is completed");
            }
        }
    }

    public Resume get(String uuid) {
        Resume resume = new Resume();
        if (!checkIsEmpty()) {
            if (getIndex(uuid) != -1) {
                resume.setUuid(uuid);
                return resume;
            }
        }
        return resume;
    }

    public void delete(String uuid) {
        if (!checkIsEmpty()) {
            int index = getIndex(uuid);
            if (index != -1) {
                if (index == size - 1) {
                    storage[index] = null;
                } else {
                    for (int i = index; i < size - 1; i++) {
                        storage[i] = storage[i + 1];
                    }
                }
                storage[size - 1] = null;
                size--;
            } else {
                System.out.println("Have no this resume to delete");

            }
        }
    }

    /**
     * This method checks whether the object is already in storage
     * @return number of index if resume in storage and return "-1" if resume is not in storage
     */
    public int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        if (!checkIsEmpty()) {
            Arrays.fill(storage, null);
            size = 0;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        Resume[] resumes = new Resume[size];
        resumes = Arrays.copyOf(storage, size);
        return resumes;
    }

    public int size() {
        return size;
    }
}
