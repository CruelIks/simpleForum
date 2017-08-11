package iks.gog.jpatst.controller;

import iks.gog.jpatst.forms.MessageForm;
import iks.gog.jpatst.forms.TopicForm;
import iks.gog.jpatst.model.Topic;
import iks.gog.jpatst.service.MessageService;
import iks.gog.jpatst.service.TopicService;
import iks.gog.jpatst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/")
    public String showForum(Model model) {
        model.addAttribute("allTopics", topicService.getTopicsOrdered());
        model.addAttribute("topicForm", topicForm);
        model.addAttribute("username", userService.getCurrentUser().getName());
        return "forum";
    }

    @GetMapping("/topic")
    public String getTopic(@RequestParam(value="id", required=true) Long id, Model model) {

        Topic topic = topicService.findTopicById(id);
        model.addAttribute("currentTopic", topic);

        messageForm.setTopicId(id);
        model.addAttribute("messageForm", messageForm);
        return "topic";
    }

    @GetMapping("/addTopic")
    public String showForm(TopicForm topicForm) {
        return "addTopic";
    }

    @PostMapping("/addTopic")
    public String checkTopicData(@Valid TopicForm topicForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "addTopic";
        }

        topicService.addTopic(topicForm);
        return "redirect:/";
    }

    @PostMapping("/addMessage")
    public String checkMessageData(@Valid MessageForm messageForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "redirect:/";
        }

        messageService.addMessage(messageForm);

        return "redirect:/";
    }



}
