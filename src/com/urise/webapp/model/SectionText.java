package com.urise.webapp.model;

import java.util.Objects;

public class SectionText extends Section {
    private final String information;

    public SectionText(String information) {
        Objects.requireNonNull(information, "information must not be null");
        this.information = information;
    }

    public String getInformation() {
        return information;
    }

    @Override
    public String toString() {
        return information;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SectionText that = (SectionText) o;

        return information.equals(that.information);
    }

    @Override
    public int hashCode() {
        return information.hashCode();
    }
}
