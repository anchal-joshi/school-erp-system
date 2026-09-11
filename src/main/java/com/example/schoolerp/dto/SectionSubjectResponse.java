package com.example.schoolerp.dto;

public class SectionSubjectResponse {

    private Long id;
    private Long sectionId;
    private Long subjectId;
    private Long schoolId;

    public SectionSubjectResponse() {
    }

    public SectionSubjectResponse(Long id, Long sectionId, Long subjectId, Long schoolId) {
        this.id = id;
        this.sectionId = sectionId;
        this.subjectId = subjectId;
        this.schoolId = schoolId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }
}
