package com.kvernadze.project.controller;

import com.kvernadze.project.model.User;
import com.kvernadze.project.service.ControllerServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class RegistrationController {
    private ControllerServices authServ;

    public RegistrationController() {
        authServ  = new ControllerServices();
    }


    @GetMapping("/register")
    public String renderForm() {
        return "register";
    }

    @PostMapping("register")
    public ModelAndView register(HttpServletResponse resp,
                                 @RequestParam String username,
                                 @RequestParam String password) throws IOException {
        User user = authServ.getUser(username);
        ModelAndView ret = new ModelAndView("register");
        if (user != null) {
            ret.addObject("error", "Username " + username + " is already taken");
            ret.addObject("username", username);
            return ret;
        }

        if (!authServ.create(new User(username, password, 1))) {
            ret.addObject("error", "Internal error: try again");
            ret.addObject("username", username);
            return ret;
        }

        resp.sendRedirect("/login");
        return null;
    }
}
