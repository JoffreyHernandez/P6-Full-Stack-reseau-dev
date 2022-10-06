package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.CommentDto;
import com.openclassrooms.mddapi.entity.Comment;
import com.openclassrooms.mddapi.mapper.CommentMapper;
import com.openclassrooms.mddapi.service.CommentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
@Log4j2
public class CommentController {
    private final CommentMapper commentMapper;
    private final CommentService commentService;


    public CommentController(CommentService commentService,
                             CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
        this.commentService = commentService;
    }

    @GetMapping("post/{postId}")
    public ResponseEntity<?> findByPostId(@PathVariable("postId") String postId) {
        try {
            List<Comment> comment = this.commentService.getByPost_id(Long.valueOf(postId));

            if (comment == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(this.commentMapper.toDto(comment));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody CommentDto commentDto) {

        Comment comment = this.commentService.create(this.commentMapper.toEntity(commentDto));

        return ResponseEntity.ok().body(this.commentMapper.toDto(comment));
    }
}
