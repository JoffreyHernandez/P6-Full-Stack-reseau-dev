package com.openclassrooms.mddapi.mapper;

import com.openclassrooms.mddapi.dto.PostDto;
import com.openclassrooms.mddapi.entity.Post;
import com.openclassrooms.mddapi.service.TopicService;
import com.openclassrooms.mddapi.service.UserService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public abstract class PostMapper implements EntityMapper<PostDto, Post> {
    @Autowired
    TopicService topicService;
    @Autowired
    UserService userService;

    @Mappings({
            @Mapping(target = "user", expression = "java(postDto.getUser_id() != null ? this.userService.getById(postDto.getUser_id()) : null)"),
            @Mapping(target = "topic", expression = "java(postDto.getTopic_id() != null ? this.topicService.getById(postDto.getTopic_id()) : null)"),
    })
    public abstract Post toEntity(PostDto postDto);


    @Mappings({
            @Mapping(source = "post.user.id", target = "user_id"),
            @Mapping(source = "post.topic.id", target = "topic_id"),
    })
    public abstract PostDto toDto(Post post);


}
