package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {
    protected final static int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract void addElement(Resume resume, Integer index);

    @Override
    public void doSave(Resume resume, Object index) {
        if (size >= storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            addElement(resume, (Integer) index);
            size++;
        }
    }

    @Override
    protected void doUpdate(Resume resume, Object index) {
        storage[(Integer) index] = resume;
        System.out.println("Update '" + storage[(Integer) index] + "' is completed");
    }

    @Override
    protected Resume doGet(Object index) {
        return storage[(Integer) index];
    }

    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    @Override
    public void doDelete(Object index) {
        removeElement((Integer) index);
        storage[size - 1] = null;
        size--;
    }

    protected abstract void removeElement(Integer index);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

}
