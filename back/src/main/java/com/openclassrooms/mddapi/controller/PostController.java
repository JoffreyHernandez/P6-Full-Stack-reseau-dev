package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.entity.Post;
import com.openclassrooms.mddapi.mapper.PostMapper;
import com.openclassrooms.mddapi.service.PostService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/post")
@Log4j2
public class PostController {
    private final PostMapper postMapper;
    private final PostService postService;


    public PostController(PostService postService,
                          PostMapper postMapper) {
        this.postMapper = postMapper;
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        try {
            Post post = this.postService.getById(Long.valueOf(id));

            if (post == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(this.postMapper.toDto(post));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Post> posts = this.postService.findAll();

        return ResponseEntity.ok().body(this.postMapper.toDto(posts));
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody PostDto postDto) {
        log.info(postDto);

        Post post = this.postService.create(this.postMapper.toEntity(postDto));

        log.info(post);
        return ResponseEntity.ok().body(this.postMapper.toDto(post));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @Valid @RequestBody PostDto postDto) {
        try {
            Post post = this.postService.update(Long.parseLong(id), this.postMapper.toEntity(postDto));

            return ResponseEntity.ok().body(this.postMapper.toDto(post));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> save(@PathVariable("id") String id) {
        try {
            Post post = this.postService.getById(Long.valueOf(id));

            if (post == null) {
                return ResponseEntity.notFound().build();
            }

            this.postService.delete(Long.parseLong(id));
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
 
