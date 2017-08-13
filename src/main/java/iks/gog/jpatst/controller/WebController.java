package iks.gog.jpatst.controller;

import iks.gog.jpatst.forms.MessageForm;
import iks.gog.jpatst.forms.TopicForm;
import iks.gog.jpatst.model.Message;
import iks.gog.jpatst.model.Topic;
import iks.gog.jpatst.service.MessageService;
import iks.gog.jpatst.service.TopicService;
import iks.gog.jpatst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

@Controller
public class WebController extends WebMvcConfigurerAdapter {

    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private TopicForm topicForm;
    @Autowired
    private MessageForm messageForm;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showForum(
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
            Model model) {
        model.addAttribute("allTopics", topicService.getTopicsPaged(page, size));
        model.addAttribute("topicForm", topicForm);
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "forum";
    }

    @RequestMapping(value = "/topic", method = RequestMethod.GET)
    public String getTopic(@RequestParam(value = "id", required = true) Long id,
                           @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
                           @RequestParam(name = "size", defaultValue = "10", required = false) Integer size,
                           Model model) {

        Topic topic = topicService.findTopicById(id);
        model.addAttribute("currentTopic", topic);
        Page<Message> currentPage = messageService.findByTopicId(id, page, size);
        model.addAttribute("currentPage", currentPage);
        messageForm.setTopicId(id);
        model.addAttribute("messageForm", messageForm);
        model.addAttribute("currentUser", userService.getCurrentUser());
        return "topic";
    }

    @RequestMapping(value = "/addTopic", method = RequestMethod.GET)
    public String showForm(TopicForm topicForm) {
        return "addTopic";
    }

    @RequestMapping(value = "/addTopic", method = RequestMethod.POST)
    public String checkTopicData(@Valid TopicForm topicForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "addTopic";
        }

        Topic newTopic = topicService.addTopic(topicForm);
        return "redirect:/topic?id=" + newTopic.getId();
    }

    @RequestMapping(value = "/deleteTopic", method = RequestMethod.GET)
    public String deleteTopic(@RequestParam(value = "id", required = true) Long topicId){
        topicService.deleteTopic(topicId);
        return "redirect:/";
    }

    @RequestMapping(value = "/deleteMessage", method = RequestMethod.GET)
    public String deleteMessage(@RequestParam(value = "id", required = true) Long messageId){
        Message message = messageService.findMessageById(messageId);
        Long topicId = message.getTopic().getId();

        messageService.deleteMessage(message);
        return "redirect:/topic?id=" + topicId;
    }

    @RequestMapping(value = "/addMessage")
    public String checkMessageData(@Valid MessageForm messageForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }

        Message newMessage = messageService.addMessage(messageForm);

        if (newMessage == null){
            return "redirect:/";
        }

        return "redirect:/topic?id=" + newMessage.getTopic().getId();
    }


}
