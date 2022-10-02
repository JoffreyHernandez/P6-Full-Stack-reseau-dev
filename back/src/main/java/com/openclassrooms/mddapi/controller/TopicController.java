package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.mapper.TopicMapper;
import com.openclassrooms.mddapi.payload.response.MessageResponse;
import com.openclassrooms.mddapi.service.TopicService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/topic")
@Log4j2
public class TopicController {

    private final TopicMapper topicMapper;
    private final TopicService topicService;


    public TopicController(TopicService topicService,
                           TopicMapper topicMapper) {
        this.topicMapper = topicMapper;
        this.topicService = topicService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") String id) {
        try {
            Topic topic = this.topicService.getById(Long.valueOf(id));

            if (topic == null) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok().body(this.topicMapper.toDto(topic));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        List<Topic> topics = this.topicService.findAll();

        return ResponseEntity.ok().body(this.topicMapper.toDto(topics));
    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid @RequestBody TopicDto topicDto) {
        if (topicService.existsByName(topicDto.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Topic name already exist !"));
        }

        Topic topic = this.topicService.create(this.topicMapper.toEntity(topicDto));

        return ResponseEntity.ok().body(this.topicMapper.toDto(topic));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable("id") String id, @Valid @RequestBody TopicDto topicDto) {
        try {
            Topic topic = this.topicService.update(Long.parseLong(id), this.topicMapper.toEntity(topicDto));

            return ResponseEntity.ok().body(this.topicMapper.toDto(topic));
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> save(@PathVariable("id") String id) {
        try {
            Topic topic = this.topicService.getById(Long.valueOf(id));

            if (topic == null) {
                return ResponseEntity.notFound().build();
            }

            this.topicService.delete(Long.parseLong(id));
            return ResponseEntity.ok().build();
        } catch (NumberFormatException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
