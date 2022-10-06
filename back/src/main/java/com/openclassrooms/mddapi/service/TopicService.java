package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.entity.Topic;
import com.openclassrooms.mddapi.entity.User;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class TopicService {

    private final TopicRepository topicRepository;
    private final UserRepository userRepository;

    TopicService(TopicRepository topicRepository, UserRepository userRepository) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
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

    public User follow(long topicId, long userId) {
        User user = this.userRepository.findById(userId).orElse(null);
        if (user != null) {
            Topic topic = this.topicRepository.findById(topicId).orElse(null);
            if (topic != null && user.getTopics().stream().noneMatch(t -> t.getId().equals(topic.getId()))) {
                user.getTopics().add(topic);
                return this.userRepository.save(user);
            }
        }
        return user;
    }

    public User unfollow(long topicId, long userId) {
        User user = this.userRepository.findById(userId).orElse(null);
        if (user != null) {
            Topic topic = this.topicRepository.findById(topicId).orElse(null);
            if (topic != null && user.getTopics().stream().anyMatch(t -> t.getId().equals(topic.getId()))) {
                user.setTopics(user.getTopics().stream()
                        .filter(x -> !x.getId().equals(topic.getId()))
                        .collect(Collectors.toList()));
                return this.userRepository.save(user);
            }
        }
        return user;
    }
}
