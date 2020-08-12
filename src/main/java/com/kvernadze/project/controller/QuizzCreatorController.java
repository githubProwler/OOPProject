package com.kvernadze.project.controller;

import com.kvernadze.project.model.Question;
import com.kvernadze.project.model.Quizz;
import com.kvernadze.project.model.User;
import com.kvernadze.project.service.ControllerServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Controller
public class QuizzCreatorController {
    private final ControllerServices dbServ;

    public QuizzCreatorController() {
        dbServ = new ControllerServices();
    }

    @GetMapping("/quizzcreator")
    public ModelAndView createQuizz(HttpSession ses,
                                    HttpServletResponse resp) throws IOException {
        ModelAndView ret = new ModelAndView("quizzcreator");
        User user = (User) ses.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("/login");
            return null;
        }

        if (user.getUserType() != 0) {
            ret.addObject("error", "Not authorized");
        }

        return ret;
    }

    @PostMapping("/quizzcreator/addquestion")
    public ModelAndView addQuestion(HttpSession ses,
                                    @RequestParam String question,
                                    @RequestParam String answer) throws IOException {
        ModelAndView ret = new ModelAndView("quizzcreator");
        User user = (User) ses.getAttribute("user");

        Collection<Question> questions;
        questions = (Collection<Question>) ses.getAttribute("quizz");
        if (questions == null) {
            questions = new ArrayList<Question>();
        }

        Question newQuestion = new Question(question, answer);
        questions.add(newQuestion);
        ses.removeAttribute("quizz");
        ses.setAttribute("quizz", questions);

        return ret;
    }

    @PostMapping("/quizzcreator")
    public ModelAndView addQuizz(HttpSession ses,
                                    @RequestParam String name,
                                    @RequestParam String description) throws IOException {
        ModelAndView ret = new ModelAndView("quizzcreator");
        User user = (User) ses.getAttribute("user");

        if (user == null || user.getUserType() != 0) {
            return null;
        }

        Collection<Question> questions;
        questions = (Collection<Question>) ses.getAttribute("quizz");

        if (questions == null) {
            questions = new ArrayList<>();
        }

        Quizz newQuizz = new Quizz(name, description, user.getId());
        dbServ.addQuizz(newQuizz);
        newQuizz = dbServ.getQuizz(name);
        dbServ.addQuestions(questions, newQuizz.getId());

        ses.removeAttribute("quizz");

        return ret;
    }

    @PostMapping("/quizzcreator/reset")
    public ModelAndView discardQuizz(HttpSession ses) throws IOException {
        ModelAndView ret = new ModelAndView("quizzcreator");
        ses.removeAttribute("quizz");
        return ret;
    }
}
