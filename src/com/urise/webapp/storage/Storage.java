package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

public interface Storage {
    void save(Resume resume);
    void update(Resume resume);
    Resume get(String uuid);
    void delete(String uuid);
    void clear();
    Resume[] getAll();
    int size();
}
