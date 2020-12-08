package com.urise.webapp.model;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class OrganizationList extends AbstractSection {
    private static final long serialVersionUid = 1L;
    private final List<Organization> organisations;

    public OrganizationList(Organization... organizations) {
        this(Arrays.asList(organizations));
    }

    public OrganizationList(List<Organization> organisations) {
        Objects.requireNonNull(organisations, "organizations must not be null");
        this.organisations = organisations;
    }

    public List<Organization> getOrganisations() {
        return organisations;
    }

    @Override
    public String toString() {
        return organisations.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationList that = (OrganizationList) o;

        return organisations.equals(that.organisations);
    }

    @Override
    public int hashCode() {
        return organisations.hashCode();
    }
}
