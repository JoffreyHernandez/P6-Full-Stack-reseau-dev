package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.entity.Comment;
import com.openclassrooms.mddapi.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {


    private final CommentRepository commentRepository;

    CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment create(Comment comment) {
        return this.commentRepository.save(comment);
    }

    public void delete(Long id) {
        this.commentRepository.deleteById(id);
    }

    public List<Comment> findAll() {
        return this.commentRepository.findAll();
    }

    public Comment getById(Long id) {
        return this.commentRepository.findById(id).orElse(null);
    }

    public Comment update(Long id, Comment comment) {
        comment.setId(id);
        return this.commentRepository.save(comment);
    }
}
