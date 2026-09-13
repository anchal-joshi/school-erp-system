package com.example.schoolerp.entity;

import jakarta.persistence.*;

@Entity
public class TeacherSubjectAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne
    @JoinColumn (nullable = false)
    private SectionSubject sectionSubject;

    @ManyToOne
    @Column(nullable = false)
    private School school;

    public TeacherSubjectAssignment() {
    }

    public TeacherSubjectAssignment(Long id, Teacher teacher, SectionSubject sectionSubject, School school) {
        this.id = id;
        this.teacher = teacher;
        this.sectionSubject = sectionSubject;
        this.school = school;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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
