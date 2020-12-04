package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.Month;

public class ResumeTestData {
    public static void main(String[] args) {
        System.out.println(addResume("001", "Some Person"));
    }

    public static Resume addResume(String uuid, String fullName) {
        Resume resume = new Resume(uuid, fullName);
        resume.setContact(ContactType.PHONE_NUMBER, "phone number");
        resume.setContact(ContactType.SKYPE, "skype");
        resume.setContact(ContactType.EMAIL, "email");
        resume.setContact(ContactType.LINKED_IN, "linkedin");
        resume.setContact(ContactType.GIT_HUB, "github");
        resume.setContact(ContactType.STACK_OVERFLOW, "stack-overflow");
        resume.setContact(ContactType.HOME_PAGE, "home page1");
        resume.setSection(SectionType.PERSONAL, new TextSection("personal"));
        resume.setSection(SectionType.OBJECTIVE, new TextSection("objective"));
        resume.setSection(SectionType.ACHIEVEMENT, new ListSection("achievement1", "achievement2"));
        resume.setSection(SectionType.QUALIFICATIONS, new ListSection("qualification1", "qualification2"));
        resume.setSection(SectionType.EXPERIENCE, new OrganizationList(
                new Organization("Job1", "www.job1.ru",
                        new Organization.Position(2017, Month.MARCH, 2019, Month.MAY, "specialist", "some description")),
                new Organization("Job2", "www.job2.ru",
                        new Organization.Position(2019, Month.APRIL, "specialist", "some description"))));
        resume.setSection(SectionType.EDUCATION, new OrganizationList(
                new Organization("University1", null,
                        new Organization.Position(2000, Month.SEPTEMBER, 2005, Month.MAY, "student", "no description")),
                new Organization("University2", null,
                        new Organization.Position(2018, Month.OCTOBER, 2019, Month.APRIL, "student", null))));

        return resume;
    }
}
