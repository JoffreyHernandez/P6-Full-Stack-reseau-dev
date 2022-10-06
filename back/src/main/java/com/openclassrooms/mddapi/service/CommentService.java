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

    public List<Comment> getByPost_id(Long postId) {
        return this.commentRepository.findByPost_id(postId);
    }
}
