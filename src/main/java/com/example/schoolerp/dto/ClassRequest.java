package com.example.schoolerp.dto;

import jakarta.validation.constraints.NotBlank;

public class ClassRequest {

    @NotBlank
    private String name;

    public ClassRequest() {
    }

    public ClassRequest(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
