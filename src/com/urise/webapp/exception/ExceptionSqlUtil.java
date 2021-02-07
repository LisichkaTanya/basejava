package com.urise.webapp.exception;

import org.postgresql.util.PSQLException;

import java.sql.SQLException;

public class ExceptionSqlUtil {
    private ExceptionSqlUtil() {
    }

    public static StorageException convertException(SQLException e) {
        if (e instanceof PSQLException) {

//            http://www.postgresql.org/docs/9.3/static/errcodes-appendix.html
            if (e.getSQLState().equals("23505")) {
                return new ExistStorageException(null);
            }
        }
        return new StorageException(e);
    }
}
