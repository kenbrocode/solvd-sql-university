package com.solvd.university.patterns.builder;

import java.sql.Date;

public class Faculty {


    private Long id;
    private String title;
    private String description;
    private String dekan;
    private Long universityId;

    private Date dateCreatingFaculty;


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDekan() {
        return dekan;
    }

    public Long getUniversityId() {
        return universityId;
    }

    public Date getDateCreatingFaculty() {
        return dateCreatingFaculty;
    }

    public static Builder builder() {
        return new Builder(new Faculty());
    }

    public static class Builder {
        private final Faculty faculty;

        public Builder(Faculty faculty) {
            this.faculty = faculty;
        }

        public Builder title(String title) {
            faculty.title = title;
            return this;
        }

        public Builder description(String description) {
            faculty.description = description;
            return this;
        }

        public Builder dekan(String dekan) {
            faculty.dekan = dekan;
            return this;
        }

        public Builder universityId(Long universityId) {
            faculty.universityId = universityId;
            return this;
        }

        public Builder dateCreatingFaculty(Date dateCreatingFaculty) {
            faculty.dateCreatingFaculty = dateCreatingFaculty;
            return this;
        }

        public Faculty build() {
            return faculty;
        }
    }
}

