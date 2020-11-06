package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private final Map<String, Resume> map = new HashMap<>();

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        map.put((String) searchKey, resume);
    }

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        map.put((String) searchKey, resume);
        System.out.println("Update '" + map.get(searchKey) + "' is completed");
    }

    @Override
    protected Resume doGet(Object searchKey) {
        return map.get(searchKey);
    }

    @Override
    protected void doDelete(Object searchKey) {
        map.remove(searchKey);
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return map.containsKey(searchKey);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    public Resume[] getAll() {
        return map.values().toArray(new Resume[size()]);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public int size() {
        return map.size();
    }
}
