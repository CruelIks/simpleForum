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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    //@RequestMapping(value = "/login", method = RequestMethod.GET)
    @RequestMapping(value = "/login")
    public ModelAndView getUserLoginPage(@RequestParam(name = "error", defaultValue = "false", required = false) String error) {
        ModelAndView model = new ModelAndView("login", "form", new LoginForm());
        if (error.equals("true")) {
            model.addObject("error", true);
        }
        return model;
    }


}
