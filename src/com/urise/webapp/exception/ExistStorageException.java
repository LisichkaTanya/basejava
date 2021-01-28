package com.urise.webapp.exception;

public class ExistStorageException extends StorageException {
    private static final long serialVersionUID = 1L;

    public ExistStorageException(String uuid) {
        super("Resume " + uuid + " already exist", uuid);
    }

    public ExistStorageException(String uuid, Exception e) {
        super("Resume " + uuid + " already exist", uuid, e);
    }
}
