package com.devkaran.user_authentication;

import java.util.List;

public class RegistrationDto {
    private String username;
    private String password;
    private List<String> roles; // Add this

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<String> getRoles() { return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }
}
