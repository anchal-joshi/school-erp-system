package com.example.schoolerp.entity;

import jakarta.persistence.*;

import java.time.Year;

@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "year", nullable = false)
    private Year year;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    public Exam() {
    }

    public Exam(Long id, String name, Year year, School school) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.school = school;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
