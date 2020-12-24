package com.urise.webapp.storage.serialization;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataStreamSerialization implements StreamSerialization {

    @Override
    public void doWrite(Resume resume, OutputStream outputStream) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(outputStream)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            writeCollection(dos, resume.getContacts().entrySet(), contacts -> {
                dos.writeUTF(contacts.getKey().name());
                dos.writeUTF(contacts.getValue());
            });

            writeCollection(dos, resume.getSections().entrySet(), sections -> {
                SectionType type = sections.getKey();
                AbstractSection section = sections.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getInformation());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(dos, ((ListSection) section).getParagraphs(), dos::writeUTF);
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        writeCollection(dos, ((OrganizationList) section).getOrganisations(), organizations -> {
                            dos.writeUTF(organizations.getHomePage().getName());
                            dos.writeUTF(organizations.getHomePage().getUrl());
                            writeCollection(dos, organizations.getPositions(), positions -> {
                                writeDate(dos, positions.getStartDate());
                                writeDate(dos, positions.getEndDate());
                                dos.writeUTF(positions.getTitle());
                                dos.writeUTF(positions.getDescription());
                            });

                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        try (DataInputStream dis = new DataInputStream((inputStream))) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readComponent(dis, () -> resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readComponent(dis, () -> readSectionType(dis, resume));
            return resume;
        }
    }

    private void readSectionType(DataInputStream dis, Resume resume) throws IOException {
        SectionType type = SectionType.valueOf(dis.readUTF());
        switch (type) {
            case PERSONAL:
            case OBJECTIVE:
                resume.setSection(type, new TextSection(dis.readUTF()));
                break;
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                resume.setSection(type, new ListSection(readCollection(dis, dis::readUTF)));
                break;
            case EXPERIENCE:
            case EDUCATION:
                resume.setSection(type, new OrganizationList(
                        readCollection(dis, () -> new Organization(new Link(dis.readUTF(), dis.readUTF()),
                                readCollection(dis, () -> new Organization.Position(
                                        readDate(dis), readDate(dis),
                                        dis.readUTF(), dis.readUTF()))))));
                break;
        }
    }

    private void writeDate(DataOutputStream dos, LocalDate localDate) throws IOException {
        dos.writeInt(localDate.getYear());
        dos.writeInt(localDate.getMonthValue());
    }

    private LocalDate readDate(DataInputStream dis) throws IOException {
        return LocalDate.of(dis.readInt(), dis.readInt(), 1);
    }

    @FunctionalInterface
    private interface WriteElementCollection<T> {
        void accept(T t) throws IOException;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, WriteElementCollection<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T t : collection) {
            writer.accept(t);
        }
    }

    @FunctionalInterface
    private interface ReadElementCollection<T> {
        T read() throws IOException;
    }

    private <T> List<T> readCollection(DataInputStream dis, ReadElementCollection<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    @FunctionalInterface
    private interface ReadElement {
        void read() throws IOException;
    }

    private void readComponent(DataInputStream dis, ReadElement reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }
}
