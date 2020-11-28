package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume1 = new Resume("001", "Григорий Кислин");

        Contact phoneNumber = new Contact("+7(921) 855-0482");
        Contact skype = new Contact("grigory.kislin");

        Map<ContactType, Contact> contacts = new EnumMap<>(ContactType.class);
        contacts.put(ContactType.PHONE_NUMBER, phoneNumber);
        contacts.put(ContactType.SKYPE, skype);


        Organization RIT_Center = new Organization("RIT Center", "Java архитектор",
                LocalDate.of(2012,04,1), LocalDate.of(2014, 10, 1),
                "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");

        Organization Wrike = new Organization("Wrike", "Старший разработчик (backend)",
                LocalDate.of(2014,10,1), LocalDate.of(2016,01,1), "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");

        Organization Luxoft = new Organization("Luxoft", "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                LocalDate.of(2011,03,1), LocalDate.of(2011,04,1),null);

        Organization Coursera = new Organization("Coursera", "\"Functional Programming Principles in Scala\" by Martin Odersky",
                LocalDate.of(2013, 03, 1), LocalDate.of(2013, 05, 1), null);

        List<Organization> work = new ArrayList<>();
        work.add(RIT_Center);
        work.add(Wrike);
        OrganizationList workList = new OrganizationList(work);

        List<Organization> studies = new ArrayList<>();
        studies.add(Luxoft);
        studies.add(Coursera);
        OrganizationList studiesList = new OrganizationList(studies);

        TextSection personal = new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        TextSection objective = new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");

        List<String> achievements = new ArrayList<>();
        achievements.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievements.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        ListSection achievement = new ListSection(achievements);

        List<String> qualifications = new ArrayList<>();
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        ListSection qualification = new ListSection(qualifications);

        Map<SectionType, AbstractSection> sections = new EnumMap<>(SectionType.class);
        sections.put(SectionType.OBJECTIVE, objective);
        sections.put(SectionType.PERSONAL, personal);
        sections.put(SectionType.ACHIEVEMENT, achievement);
        sections.put(SectionType.QUALIFICATIONS, qualification);
        sections.put(SectionType.EXPERIENCE, workList);
        sections.put(SectionType.EDUCATION, studiesList);

        resume1.setContacts(contacts);
        resume1.setSections(sections);

        System.out.println(resume1);
        for(Map.Entry<ContactType, Contact> pair : contacts.entrySet()) {
            System.out.println(pair.getKey().getTitle() + ": " + pair.getValue());
        }
        for(Map.Entry<SectionType, AbstractSection> pair : sections.entrySet()) {
            System.out.println(pair.getKey().getTitle() + ": " + pair.getValue());
        }

    }
}
