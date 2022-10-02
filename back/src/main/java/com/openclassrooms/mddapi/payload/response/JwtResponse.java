package com.openclassrooms.mddapi.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {
    private final String type = "Bearer";
    private String token;
    private Long id;
    private String username;
    private String email;

    public JwtResponse(String token, Long id, String username, String email) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.username = username;
    }
}
