package com.example.schoolerp.entity;

import jakarta.persistence.*;

@Entity
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private School school;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Exam exam;

    @ManyToOne
    private Student student;

    @Column(nullable = false)
    private Double marks;

    @Column(nullable = false, name = "full_marks")
    private Double fullMarks;

    public Marks() {
    }

    public Marks(Long id, School school, Subject subject, Exam exam, Student student, Double marks, Double fullMarks) {
        this.id = id;
        this.school = school;
        this.subject = subject;
        this.exam = exam;
        this.student = student;
        this.marks = marks;
        this.fullMarks = fullMarks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        this.marks = marks;
    }

    public Double getFullMarks() {
        return fullMarks;
    }

    public void setFullMarks(Double fullMarks) {
        this.fullMarks = fullMarks;
    }
}
