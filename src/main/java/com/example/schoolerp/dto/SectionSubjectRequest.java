package com.example.schoolerp.dto;

import jakarta.validation.constraints.NotNull;

public class SectionSubjectRequest {

    @NotNull
    private Long sectionId;

    @NotNull
    private Long subjectId;

    public SectionSubjectRequest() {
    }

    public SectionSubjectRequest(Long sectionId, Long subjectId) {
        this.sectionId = sectionId;
        this.subjectId = subjectId;
    }

    public Long getSectionId() {
        return sectionId;
    }

    public void setSectionId(Long sectionId) {
        this.sectionId = sectionId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }
}
