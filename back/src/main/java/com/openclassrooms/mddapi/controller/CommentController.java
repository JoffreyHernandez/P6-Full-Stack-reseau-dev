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

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        try {
            Comment comment = this.commentService.getById(Long.valueOf(id));

            if (comment == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(this.commentMapper.toDto(comment));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Comment> comments = this.commentService.findAll();

        return ResponseEntity.ok().body(this.commentMapper.toDto(comments));
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody CommentDto commentDto) {

        Comment comment = this.commentService.create(this.commentMapper.toEntity(commentDto));

        return ResponseEntity.ok().body(this.commentMapper.toDto(comment));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @Valid @RequestBody CommentDto commentDto) {
        try {
            Comment comment = this.commentService.update(Long.parseLong(id), this.commentMapper.toEntity(commentDto));

            return ResponseEntity.ok().body(this.commentMapper.toDto(comment));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> save(@PathVariable("id") String id) {
        try {
            Comment comment = this.commentService.getById(Long.valueOf(id));

            if (comment == null) {
                return ResponseEntity.notFound().build();
            }

            this.commentService.delete(Long.parseLong(id));
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
