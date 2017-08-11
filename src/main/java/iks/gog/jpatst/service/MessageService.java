package iks.gog.jpatst.service;

import iks.gog.jpatst.forms.MessageForm;
import iks.gog.jpatst.model.Message;
import iks.gog.jpatst.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void addMessage(MessageForm form){

        Message message = new Message();
        message.setTextMessage(form.getDescription());
        message.setCreatedWhen(new Date());

        message.setTopic(topicService.findTopicById(form.getTopicId()));
        message.setUser(userService.getCurrentUser());

        messageRepository.save(message);

    }


}
