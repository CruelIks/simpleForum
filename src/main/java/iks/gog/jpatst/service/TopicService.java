package iks.gog.jpatst.service;

import iks.gog.jpatst.forms.TopicForm;
import iks.gog.jpatst.model.Message;
import iks.gog.jpatst.model.Topic;
import iks.gog.jpatst.model.User;
import iks.gog.jpatst.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserService userService;

    public void addTopic(TopicForm form) {
        Topic topic = new Topic();
        topic.setDescription(form.getDescription());
        topic.setCreatedWhen(new Date());
        topic.setUser(userService.getCurrentUser());
        topic.setMessages(new ArrayList<Message>());
        topicRepository.save(topic);
    }

    public Iterable<Topic> getTopics(){
        return topicRepository.findAll();
    }

    public Topic findTopicById(Long id){
        return topicRepository.findOne(id);
    }

    public Iterable<Topic> getTopicsOrdered(){
        return topicRepository.findAllOrdered();
    }

}
