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
    public void update(Resume resume) {
        int index = list.indexOf(resume);
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            list.set(index, resume);
        }
    }

    @Override
    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return list.get(index);
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            list.remove(index);
        }
    }

    @Override
    public void clear() {
        list.clear();
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
    public int size() {
        return list.size();
    }

    private int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        return list.indexOf(resume);
    }
}
