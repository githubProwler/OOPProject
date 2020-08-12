package com.kvernadze.project.controller;

import com.kvernadze.project.model.User;
import com.kvernadze.project.service.ControllerServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class QuizzViewerController {
    private final ControllerServices dbServ;

    public QuizzViewerController() {
        dbServ = new ControllerServices();
    }

    @GetMapping("/quizzviewer/{quizzid}")
    public ModelAndView quizzViewer(HttpSession ses,
                                    HttpServletResponse resp,
                                    @PathVariable int quizzid) throws IOException {
        ModelAndView ret = new ModelAndView("quizzviewer");

        User user = (User) ses.getAttribute("user");

        if (user == null) {
            ret = new ModelAndView("login");
            resp.sendRedirect("/login");
            return null;
        }

        ret.addObject("quizze", dbServ.getQuizzById(quizzid));
        ret.addObject("questions", dbServ.getQuestionsForQuizz(quizzid));

        return ret;
    }
}
