package com.urise.webapp.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ResumeTestData {
    public static void main(String[] args) {
        Resume resume1 = new Resume("001", "Григорий Кислин");

        Contact phoneNumber = new Contact("+7(921) 855-0482");
        Contact skype = new Contact("grigory.kislin");

        resume1.contacts.put(ContactType.PHONE_NUMBER, phoneNumber);
        resume1.contacts.put(ContactType.SKYPE, skype);

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
        List<Organization> studies = new ArrayList();
        studies.add(Luxoft);
        studies.add(Coursera);
        OrganizationList studiesList = new OrganizationList(studies);

        SectionText personal = new SectionText("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        SectionText objective = new SectionText("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");

        List<String> achievements = new ArrayList<>();
        achievements.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        achievements.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");

        SectionList achievement = new SectionList(achievements);

        List<String> qualifications = new ArrayList<>();
        qualifications.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        qualifications.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        SectionList qualification = new SectionList(qualifications);

        resume1.sections.put(SectionType.OBJECTIVE, objective);
        resume1.sections.put(SectionType.PERSONAL, personal);
        resume1.sections.put(SectionType.ACHIEVEMENT, achievement);
        resume1.sections.put(SectionType.QUALIFICATIONS, qualification);
        resume1.sections.put(SectionType.EXPERIENCE, workList);
        resume1.sections.put(SectionType.EDUCATION, studiesList);

        System.out.println(resume1.toString() + "\n" +
                ContactType.PHONE_NUMBER.getTitle() + " " + resume1.getContact(ContactType.PHONE_NUMBER) + "\n" +
                ContactType.SKYPE.getTitle() + " " + resume1.getContact(ContactType.SKYPE) + "\n" +
                SectionType.OBJECTIVE.getTitle() + " " + resume1.getSection(SectionType.OBJECTIVE) + "\n" +
                SectionType.PERSONAL.getTitle() + " " + resume1.getSection(SectionType.PERSONAL) + "\n" +
                SectionType.ACHIEVEMENT.getTitle() + " " + resume1.getSection(SectionType.ACHIEVEMENT) + "\n" +
                SectionType.QUALIFICATIONS.getTitle() + " " + resume1.getSection(SectionType.QUALIFICATIONS) + "\n" +
                SectionType.EXPERIENCE.getTitle() + " " + resume1.getSection(SectionType.EXPERIENCE) + "\n" +
                SectionType.EDUCATION.getTitle() + " " + resume1.getSection(SectionType.EDUCATION));

    }
}
