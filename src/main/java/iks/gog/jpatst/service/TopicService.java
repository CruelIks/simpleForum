package iks.gog.jpatst.service;

import iks.gog.jpatst.forms.TopicForm;
import iks.gog.jpatst.model.Message;
import iks.gog.jpatst.model.Role;
import iks.gog.jpatst.model.Topic;
import iks.gog.jpatst.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class TopicService {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserService userService;

    public Topic addTopic(TopicForm form) {
        Topic topic = new Topic();
        topic.setDescription(form.getDescription());
        Date date = new Date();
        topic.setCreatedWhen(date);
        topic.setUpdatedWhen(date);
        topic.setUser(userService.getCurrentUser());
        topic.setMessages(new ArrayList<Message>());
        return topicRepository.save(topic);
    }

    public Topic saveTopic(Topic topic){
        return topicRepository.save(topic);
    }

    public Iterable<Topic> getTopics(){
        return topicRepository.findAll();
    }

    public Topic findTopicById(Long id){
        return topicRepository.findOne(id);
    }

    public Page<Topic> getTopicsPaged(int page, int size){
        Sort sort = new Sort(Sort.Direction.DESC,  "updatedWhen");

        PageRequest request = new PageRequest(page, size, sort);
        return topicRepository.findAll(request);
    }

    public void deleteTopic(Long topicId){
        Topic topic = topicRepository.findOne(topicId);

        if (topic == null || userService.getCurrentUser().getRole() != Role.ADMIN){
            return;
        }

        topicRepository.delete(topic);
    }

}
