package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@XmlAccessorType(XmlAccessType.FIELD)
public class ListSection extends AbstractSection {
    private static final long serialVersionUID = 1L;
    private List<String> paragraphs;

    public ListSection() {}

    public ListSection(String... paragraphs) {
        this(Arrays.asList(paragraphs));
    }

    public ListSection(List<String> paragraphs) {
        Objects.requireNonNull(paragraphs, "paragraphs must not be null");
        this.paragraphs = paragraphs;
    }

    public List<String> getParagraphs() {
        return paragraphs;
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
