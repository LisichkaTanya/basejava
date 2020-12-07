package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.util.Arrays;
import java.util.List;

public abstract class AbstractArrayStorage extends AbstractStorage<Integer> {
    protected final static int STORAGE_LIMIT = 10_000;
    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    protected abstract void addElement(Resume resume, Integer index);

    @Override
    protected boolean isExist(Integer index) {
        return index >= 0;
    }

    @Override
    public void doSave(Resume resume, Integer index) {
        if (size >= storage.length) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            addElement(resume, index);
            size++;
        }
    }

    @Override
    protected void doUpdate(Resume resume, Integer index) {
        storage[index] = resume;
        System.out.println("Update '" + storage[index] + "' is completed");
    }

    @Override
    protected Resume doGet(Integer index) {
        return storage[index];
    }

    public List<Resume> doCopyAll() {
        return Arrays.asList(Arrays.copyOf(storage, size));
    }

    @Override
    public void doDelete(Integer index) {
        removeElement(index);
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
