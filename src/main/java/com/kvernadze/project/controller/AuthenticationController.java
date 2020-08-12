package com.kvernadze.project.controller;

import com.kvernadze.project.model.User;
import com.kvernadze.project.service.ControllerServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class AuthenticationController {
    private ControllerServices authServ;

    public AuthenticationController() {
        authServ = new ControllerServices();
    }

    @GetMapping("/logout")
    String logout(HttpSession ses) {
        ses.invalidate();
        return "logout";
    }

    @GetMapping("/login")
    private ModelAndView login(HttpServletRequest req,
                               HttpServletResponse resp,
                               HttpSession ses) throws IOException {

        ModelAndView ret = new ModelAndView("login");
        User user = (User) ses.getAttribute("user");

        if (user != null) {
            resp.sendRedirect("/home");
            return null;
        }

        if (req.getAttribute("error") != null) {
            ret.addObject("error", "You are not logged in");
        }
        return ret;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView authenticate(HttpServletResponse resp,
                                     HttpSession ses,
                                     @RequestParam String username,
                                     @RequestParam String password) throws IOException {
//        AuthenticationService
        User user = authServ.getUser(username);
        ModelAndView ret = new ModelAndView("login");

        if(user == null || !authServ.checkPassword(username, password)) {
            ret.addObject("error", "Incorrect username or password");
            ret.addObject("username", username);
            return ret;
        }
        ses.setAttribute("user", user);
        resp.sendRedirect("/home");
        System.out.println("[Login] User: " + user.getUsername() + "; UserType: " + user.getUserType());
        return ret;
    }
}
