package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class SectionList extends Section {
    private final List<String> paragraphs;

    public SectionList(List<String> paragraphs) {
        Objects.requireNonNull(paragraphs, "paragraphs must not be null");
        this.paragraphs = paragraphs;
    }

    @Override
    public String toString() {
        return paragraphs.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SectionList that = (SectionList) o;

        return paragraphs.equals(that.paragraphs);
    }

    @Override
    public int hashCode() {
        return paragraphs.hashCode();
    }
}
