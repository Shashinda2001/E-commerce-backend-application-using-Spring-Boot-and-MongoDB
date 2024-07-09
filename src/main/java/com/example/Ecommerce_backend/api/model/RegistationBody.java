package com.example.Ecommerce_backend.api.model;

import jakarta.validation.constraints.*;

public class RegistationBody {
    @NotNull
    @NotBlank(message = "Username is mandatory")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;
    @NotNull
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    @NotNull
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")
    @NotBlank(message = "Password is mandatory")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    @NotNull
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    @NotNull
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }



}
