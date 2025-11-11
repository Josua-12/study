package com.example.userservice.dto;


public class SignUpRequestDto {
    private String email;
    private String name;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
