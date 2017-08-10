package iks.gog.jpatst.controller;

import iks.gog.jpatst.forms.LoginForm;
import iks.gog.jpatst.forms.UserCreateForm;
import iks.gog.jpatst.model.User;
import iks.gog.jpatst.service.UserService;
import iks.gog.jpatst.validator.UserCreateFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

/*
    @Autowired
    private UserCreateFormValidator userCreateFormValidator;

    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }
*/

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getUserLoginPage() {
        return new ModelAndView("login", "form", new LoginForm());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleUserLoginPage(@Valid @ModelAttribute("form") LoginForm form, BindingResult bindingResult) {

       if (bindingResult.hasErrors()) {
            return "register";
        }


        return "redirect:/";
    }
}
