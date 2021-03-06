package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.SqlHelper;

import java.sql.*;
import java.util.*;

public class SqlStorage implements Storage {

    public final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public void save(Resume resume) {
        sqlHelper.transactionalExecute(connection -> {
            try (PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO resume (uuid, full_name) VALUES (?, ?)")) {
                ps.setString(1, resume.getUuid());
                ps.setString(2, resume.getFullName());
                ps.execute();
            }
            insertContacts(connection, resume);
            return null;
        });
    }

    @Override
    public void update(Resume resume) {
        sqlHelper.transactionalExecute(connection -> {
            String uuid = resume.getUuid();
            try (PreparedStatement ps = connection.prepareStatement(
                    "UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                ps.setString(1, resume.getFullName());
                ps.setString(2, uuid);
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(uuid);
                }
            }
            deleteContacts(connection, uuid);
            insertContacts(connection, resume);
            return null;
        });
    }

    @Override
        public Resume get (String uuid){
            return sqlHelper.execute("" +
                    "SELECT * FROM resume r" +
                    " LEFT JOIN contact c ON r.uuid = c.resume_uuid" +
                    " WHERE r.uuid = ?", ps -> {
                ps.setString(1, uuid);
                ResultSet rs = ps.executeQuery();
                if (!rs.next()) {
                    throw new NotExistStorageException(uuid);
                }
                Resume resume = new Resume(uuid, rs.getString("full_name"));
                do {
                    addContact(rs,resume);
                } while (rs.next());
                return resume;
            });
        }

        @Override
        public List<Resume> getAllSorted () {
            return sqlHelper.execute("" +
                    "    SELECT * FROM resume r" +
                    " LEFT JOIN contact c ON r.uuid = c.resume_uuid" +
                    "  ORDER BY full_name", ps -> {
                ResultSet rs = ps.executeQuery();
                Map<String, Resume> resumes = new LinkedHashMap<>();
                while (rs.next()) {
                    String uuid = rs.getString("uuid");
                    Resume resume = resumes.get(uuid);
                    if (resume == null) {
                        resume = new Resume(uuid, rs.getString("full_name"));
                        resumes.put(uuid, resume);
                    }
                    addContact(rs, resume);
                }
                return new ArrayList<>(resumes.values());
            });
        }

        @Override
        public void delete (String uuid){
            sqlHelper.execute("DELETE FROM resume WHERE uuid = ?", ps -> {
                ps.setString(1, uuid);
                if (ps.executeUpdate() == 0) {
                    throw new NotExistStorageException(uuid);
                }
                return null;
            });
        }

        @Override
        public void clear () {
            sqlHelper.execute("DELETE FROM resume");
        }

        @Override
        public int size () {
            return sqlHelper.execute("SELECT count(*) FROM resume", ps -> {
                ResultSet rs = ps.executeQuery();
                return rs.next() ? rs.getInt(1) : 0;
            });
        }

    private void deleteContacts(Connection connection, String uuid) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(
                "DELETE FROM contact WHERE resume_uuid = ?")) {
            ps.setString(1, uuid);
            ps.execute();
        }
    }

    private void insertContacts(Connection connection, Resume resume) throws SQLException {
        if (!resume.getContacts().isEmpty()) {
            try (PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
                for (Map.Entry<ContactType, String> e : resume.getContacts().entrySet()) {
                    ps.setString(1, resume.getUuid());
                    ps.setString(2, e.getKey().name());
                    ps.setString(3, e.getValue());
                    ps.addBatch();
                }
                ps.executeBatch();
            }
        }
    }

        private void addContact (ResultSet rs, Resume resume) throws SQLException {
            String value = rs.getString("value");
            if (value != null) {
                resume.setContact(ContactType.valueOf(rs.getString("type")), value);
            }
        }
    }
