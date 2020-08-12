package com.kvernadze.project.controller;

import com.kvernadze.project.model.User;
import com.kvernadze.project.service.ControllerServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class HomeController {
    private final ControllerServices dbServ;

    public HomeController() {
        dbServ = new ControllerServices();
    }

    @GetMapping("/home")
    public ModelAndView home(HttpServletResponse resp,
                            HttpServletRequest req,
                            HttpSession ses) throws IOException, ServletException {
        User user = (User) ses.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("/login");
            return null;
        }

        ModelAndView ret = new ModelAndView("home");
        ret.addObject("quizzes", dbServ.getAllQuizzes());
        return ret;
    }
}