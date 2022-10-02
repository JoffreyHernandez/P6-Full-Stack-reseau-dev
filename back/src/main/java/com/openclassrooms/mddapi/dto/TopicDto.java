package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicDto {
    private Long id;

    @Size(max = 50)
    private String name;

    @Size(max = 2000)
    private String description;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
