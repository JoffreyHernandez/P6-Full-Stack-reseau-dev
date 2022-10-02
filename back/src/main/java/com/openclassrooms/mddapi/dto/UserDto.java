package com.openclassrooms.mddapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @Size(max = 40)
    private String username;

    @Size(max = 40)
    private String email;

    @JsonIgnore
    @Size(max = 120)
    private String password;

    private List<TopicDto> topics;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
