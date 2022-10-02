package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicService {

    private final TopicRepository topicRepository;

    TopicService(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    public Topic create(Topic topic) {
        return this.topicRepository.save(topic);
    }

    public void delete(Long id) {
        this.topicRepository.deleteById(id);
    }

    public List<Topic> findAll() {
        return this.topicRepository.findAll();
    }

    public Topic getById(Long id) {
        return this.topicRepository.findById(id).orElse(null);
    }

    public Topic update(Long id, Topic topic) {
        topic.setId(id);
        return this.topicRepository.save(topic);
    }

    public boolean existsByName(String name) {
        return this.topicRepository.existsByName(name);
    }
}
