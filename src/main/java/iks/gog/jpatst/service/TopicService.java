package iks.gog.jpatst.service;

import iks.gog.jpatst.model.Topic;
import iks.gog.jpatst.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    public void addTopic(String description) {
        Topic topic = new Topic();
        topic.setDescription(description);
        topic.setCreatedWhen(new Date());
        topicRepository.save(topic);
    }

    public Iterable<Topic> getTopics(){
        return topicRepository.findAll();
    }

    public Topic findTopicById(Long id){
        return topicRepository.findOne(id);
    }

}
