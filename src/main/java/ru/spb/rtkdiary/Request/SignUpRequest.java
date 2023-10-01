package ru.spb.rtkdiary.Request;

import java.util.Set;

public class SignUpRequest {
    private String username;
    private String password;

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    private Set<String> roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SignUpRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
