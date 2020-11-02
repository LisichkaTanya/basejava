package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MapStorage extends AbstractStorage {

    private final Map<Integer, Resume> map = new HashMap<>();

    @Override
    protected void addElement(Resume resume, int index) {
        map.put(size(), resume);
    }

    @Override
    protected void updateElement(Resume resume, int index) {
        map.put(index, resume);
        System.out.println("Update '" + map.get(index) + "' is completed");
    }

    @Override
    protected int getIndex(String uuid) {
        Resume resume = new Resume(uuid);
        Set<Map.Entry<Integer, Resume>> entrySet = map.entrySet();
        for (Map.Entry<Integer, Resume> pair : entrySet) {
            if (resume.equals(pair.getValue())) {
                return pair.getKey();
            }
        }
        return -1;
    }

    @Override
    protected Resume getElement(int index) {
        return map.get(index);
    }

    @Override
    protected void removeElement(int index) {
        map.remove(index);
    }

    @Override
    public Resume[] getAll() {
        Resume [] resumes = new Resume[map.size()];
        for (int i = 0; i < resumes.length; i++) {
            resumes[i] = map.get(i);
        }
        return resumes;
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
