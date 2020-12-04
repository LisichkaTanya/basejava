package com.urise.webapp.model;

public enum ContactType {
    PHONE_NUMBER("Телефон"),
    SKYPE("Skype"),
    EMAIL("Почта"),
    LINKED_IN("Профиль LinkedIn"),
    GIT_HUB("Профиль GitHub"),
    STACK_OVERFLOW("Профиль StackOverflow"),
    HOME_PAGE("Домашняя страница");

    private final String title;

    ContactType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}
