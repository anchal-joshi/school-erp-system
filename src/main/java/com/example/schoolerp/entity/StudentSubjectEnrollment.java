package com.example.schoolerp.entity;

import jakarta.persistence.*;

@Entity
public class StudentSubjectEnrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "section_subject_id", nullable = false)
    private SectionSubject sectionSubject;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    public StudentSubjectEnrollment() {
    }

    public StudentSubjectEnrollment(Long id, Student student, SectionSubject sectionSubject, School school) {
        this.id = id;
        this.student = student;
        this.sectionSubject = sectionSubject;
        this.school = school;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public SectionSubject getSectionSubject() {
        return sectionSubject;
    }

    public void setSectionSubject(SectionSubject sectionSubject) {
        this.sectionSubject = sectionSubject;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
