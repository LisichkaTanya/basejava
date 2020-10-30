package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {

    protected final List<Resume> list = new ArrayList<>();

    @Override
    public void save(Resume resume) {
        if (list.contains(resume)) {
            throw new ExistStorageException(resume.getUuid());
        } else {
            list.add(resume);
        }
    }

    @Override
    protected void updateElement(Resume resume, int index) {
        list.set(index, resume);
        System.out.println("Update '" + list.get(index) + "' is completed");
    }

    @Override
    public Resume[] getAll() {
        Resume[] resumes = new Resume[list.size()];
        for (int i = 0; i < list.size(); i++) {
            resumes[i] = list.get(i);
        }
        return resumes;
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return list.indexOf(resume);
    }

    @Override
    protected Resume returnForGet(int index) {
        return list.get(index);
    }

    @Override
    protected void removeElement(int index) {
        list.remove(index);
    }
}
