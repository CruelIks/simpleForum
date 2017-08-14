package iks.gog.jpatst.service;

import iks.gog.jpatst.forms.MessageForm;
import iks.gog.jpatst.model.Message;
import iks.gog.jpatst.model.Role;
import iks.gog.jpatst.model.Topic;
import iks.gog.jpatst.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TopicService topicService;

    public Message addMessage(MessageForm form){

        Message message = new Message();
        message.setTextMessage(form.getDescription());
        Date currentDate = new Date();
        message.setCreatedWhen(currentDate);

        Topic topic = topicService.findTopicById(form.getTopicId());
        message.setTopic(topic);
        message.setUser(userService.getCurrentUser());

        topic.setUpdatedWhen(currentDate);
        Message newMessage = messageRepository.save(message);
        topicService.saveTopic(topic);
        return newMessage;
    }

    public Page<Message> findByTopicId(Long topicId, int page, int size){
        Sort sort = new Sort(Sort.Direction.ASC,  "createdWhen");

        PageRequest request = new PageRequest(page, size, sort);
        return messageRepository.findBytopic_id(topicId, request);
    }

    public void deleteMessage(Long messageId) {
        Message message = messageRepository.findOne(messageId);

        if (message == null) {
            return;
        }

        if (userService.getCurrentUser().getRole() == Role.ADMIN ||
                userService.getCurrentUser() == message.getUser()) {
            messageRepository.delete(message);
        }
    }

    public Message findMessageById(Long id){
        return messageRepository.findOne(id);
    }

    public void deleteMessage(Message message){
        if (message == null) {
            return;
        }

        if (userService.getCurrentUser().getRole() == Role.ADMIN ||
                userService.getCurrentUser() == message.getUser()) {
            messageRepository.delete(message);
        }
    }

}
