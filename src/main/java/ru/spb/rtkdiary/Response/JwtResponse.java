package ru.spb.rtkdiary.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class JwtResponse {

    private String jwtToken;
    private Long id;
    private String username;
    List<String> roles;

    public JwtResponse(String jwtToken, Long id, String username, List<String> roles) {
        this.jwtToken = jwtToken;
        this.id = id;
        this.username = username;
        this.roles = roles;
    }
}