package com.example.schoolerp.dto;

public class TeacherResponse {

    private Long id;
    private Long userId;
    private String email;
    private String name;
    private String phone;

    public TeacherResponse() {
    }

    public TeacherResponse(Long id, Long userId, String email, String name, String phone) {
        this.id = id;
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
