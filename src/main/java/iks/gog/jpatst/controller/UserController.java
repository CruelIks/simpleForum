package iks.gog.jpatst.controller;

import iks.gog.jpatst.forms.UserCreateForm;
import iks.gog.jpatst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /*@Autowired
    private UserCreateFormValidator userCreateFormValidator;*/

    /*@InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }*/

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getUserCreatePage(UserCreateForm userCreateForm){
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            userService.create(userCreateForm);
        } catch (DataIntegrityViolationException e) {
            bindingResult.reject("name.exists", "Name already exists");
            return "register";
        }
        return "redirect:/";
    }
}
