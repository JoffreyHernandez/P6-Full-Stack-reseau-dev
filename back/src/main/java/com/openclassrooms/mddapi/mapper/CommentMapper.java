package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.CommentDto; 
import com.openclassrooms.mddapi.entity.Comment;
import com.openclassrooms.mddapi.service.PostService;
import com.openclassrooms.mddapi.service.UserService;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapper;

@Component
@Mapper(componentModel = "spring")
public abstract class CommentMapper implements EntityMapper<CommentDto, Comment> {
    @Autowired
    PostService postService;
    @Autowired
    UserService userService;

    @Mappings({
            @Mapping(target = "user", expression = "java(commentDto.getUser_id() != null ? this.userService.getById(commentDto.getUser_id()) : null)"),
            @Mapping(target = "post", expression = "java(commentDto.getPost_id() != null ? this.postService.getById(commentDto.getPost_id()) : null)"),
    })
    public abstract Comment toEntity(CommentDto commentDto);


    @Mappings({
            @Mapping(source = "comment.user.id", target = "user_id"),
            @Mapping(source = "comment.post.id", target = "post_id"),
    })
    public abstract CommentDto toDto(Comment comment);
}
