package com.urise.webapp.model;

import java.util.List;
import java.util.Objects;

public class ListSection extends AbstractSection {
    private final List<String> paragraphs;

    public ListSection(List<String> paragraphs) {
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

        ListSection that = (ListSection) o;

        return paragraphs.equals(that.paragraphs);
    }

    @Override
    public int hashCode() {
        return paragraphs.hashCode();
    }
}
