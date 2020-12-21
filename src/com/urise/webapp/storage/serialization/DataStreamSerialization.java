package com.urise.webapp.storage.serialization;

import com.urise.webapp.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerialization implements StreamSerialization {

    @Override
    public void doWrite(Resume resume, OutputStream outputStream) throws IOException {
        try(DataOutputStream dos = new DataOutputStream(outputStream)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> pair: contacts.entrySet()) {
                dos.writeUTF(pair.getKey().name());
                dos.writeUTF(pair.getValue());
            }

            Map<SectionType, AbstractSection> sections = resume.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, AbstractSection> pair : sections.entrySet()) {
                SectionType type = pair.getKey();
                AbstractSection section = pair.getValue();
                dos.writeUTF(type.name());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) section).getInformation());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        dos.writeInt((((ListSection) section).getParagraphs().size()));
                        for (String paragraph : ((ListSection) section).getParagraphs()) {
                            dos.writeUTF(paragraph);
                        }
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        break;
                }
            }
        }

    }

    @Override
    public Resume doRead(InputStream inputStream) throws IOException {
        try(DataInputStream dis = new DataInputStream((inputStream))) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int sizeContacts = dis.readInt();
            for (int i = 0; i < sizeContacts; i++) {
                resume.setContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            int sizeSections = dis.readInt();
            for (int i = 0; i < sizeSections; i++) {
                SectionType type = SectionType.valueOf(dis.readUTF());
                switch (type) {
                    case PERSONAL:
                    case OBJECTIVE:
                        resume.setSection(type, new TextSection (dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        int sizeListSection = dis.readInt();
                        List<String> ls = new ArrayList<>(sizeListSection);
                        for (int j = 0; j < sizeListSection; j++) {
                            ls.add(dis.readUTF());
                        }
                        resume.setSection(type, new ListSection(ls));
                        break;
                }
            }

            return resume;
        }
    }
}
