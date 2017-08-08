package iks.gog.jpatst.controller;

import iks.gog.jpatst.forms.TopicForm;
import iks.gog.jpatst.model.Topic;
import iks.gog.jpatst.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
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
    private TopicForm topicForm;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String showForum(Model model) {
        System.out.println("mapping \" / \" ");
        model.addAttribute("allTopics", topicService.getTopics());
        model.addAttribute("topicForm", topicForm);
        return "forum";
    }

    @GetMapping("/topic")
    public String getTopic(@RequestParam(value="id", required=true) Long id, Model model) {

        Topic topic = topicService.findTopicById(id);
        model.addAttribute("currentTopic", topic);
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

        topicService.addTopic(topicForm.getDescription());
        return "redirect:/";
    }


}
