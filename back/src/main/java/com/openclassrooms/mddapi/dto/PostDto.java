package com.openclassrooms.mddapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id;

    @Size(max = 60)
    private String title;

    @Size(max = 2000)
    private String content;

    private Long user_id;

    private Long topic_id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
