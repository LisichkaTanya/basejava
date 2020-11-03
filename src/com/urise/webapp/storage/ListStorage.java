package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    private final List<Resume> list = new ArrayList<>();

    @Override
    protected void doSave(Resume resume, int index) {
        list.add(resume);
    }

    @Override
    protected void updateElement(Resume resume, int index) {
        list.set(index, resume);
        System.out.println("Update '" + list.get(index) + "' is completed");
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return list.indexOf(resume);
    }

    @Override
    protected Resume getElement(int index) {
        return list.get(index);
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[size()]);
    }

    @Override
    protected void doDelete(int index) {
        list.remove(index);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }
}
