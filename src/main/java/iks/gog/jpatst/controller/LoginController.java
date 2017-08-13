package iks.gog.jpatst.controller;

import iks.gog.jpatst.forms.LoginForm;
import iks.gog.jpatst.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public ModelAndView getUserLoginPage(@RequestParam(name = "error", defaultValue = "false", required = false) String error) {
        ModelAndView model = new ModelAndView("login", "form", new LoginForm());
        if (error.equals("true")) {
            model.addObject("error", true);
        }
        return model;
    }


}
