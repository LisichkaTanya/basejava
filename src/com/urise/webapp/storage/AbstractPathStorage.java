package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public abstract class AbstractPathStorage extends AbstractStorage<Path> {

    private final Path directory;

    protected AbstractPathStorage(String dir) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + "is not directory or is not writable");
        }
    }


//    protected abstract void doWrite(Resume resume, OutputStream outputStream) throws IOException;
//
//    protected abstract Resume doRead(InputStream inputStream) throws IOException;
//
//    @Override
//    protected Path getSearchKey(String uuid) {
//        return Paths.get(uuid);
//    }
//
//    @Override
//    protected boolean isExist(Path path) {
//        return Files.exists(path);
//    }
//
//    @Override
//    protected void doSave(Resume resume, Path path) {
//        try {
//            Files.createFile(path);
//        } catch (IOException e) {
//            throw new StorageException("Path create error" + path.getFileName(), path.getParent().toString(), e);
//        }
//        doUpdate(resume, path);
//    }
//
//    @Override
//    protected void doUpdate(Resume resume, Path path) {
//        try {
//            doWrite(resume, new BufferedOutputStream(new PathOutputStream(path)));
//        } catch (IOException e) {
//            throw new StorageException("File write error", path.getFileName().toString(), e);
//        }
//    }
//
//    @Override
//    protected Resume doGet(Path path) {
//        try {
//            return doRead(new BufferedInputStream(new FileInputStream(path)));
//        } catch (IOException e) {
//            throw new StorageException("File read error", path.getName(), e);
//        }
//    }
//
//    @Override
//    protected List<Resume> doCopyAll() {
//        try {
//            List<Path> pathList = Files.list(directory).collect(Collectors.toList());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Path[] paths = directory.listFiles();
//        if (paths == null) {
//            throw new StorageException("Directory is not available", directory.getFileName().toString());
//        }
//        List<Resume> list = new ArrayList<>(paths.length);
//        for (Path path : paths) {
//            list.add(doGet(path));
//        }
//        return list;
//    }
//
//    @Override
//    protected void doDelete(Path path) {
//        try {
//            Files.delete(path);
//        } catch (IOException e) {
//            throw new StorageException("Path delete error", path.getFileName().toString());
//        }
//    }

    @Override
    public void clear() {
        try {
            Files.list(directory).forEach(this::doDelete);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null, e);
        }
    }

//    @Override
//    public int size() {
//        String[] list = directory.list();
//        if (list == null) {
//            throw new StorageException("Directory is not available", directory.getFileName().toString());
//        }
//        return list.length;
//    }
}
