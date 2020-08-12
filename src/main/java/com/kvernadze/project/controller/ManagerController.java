package com.kvernadze.project.controller;

import com.kvernadze.project.model.User;
import com.kvernadze.project.service.ControllerServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class ManagerController {
    private final ControllerServices dbServ;

    public ManagerController() {
        dbServ = new ControllerServices();
    }

    @GetMapping("/manager")
    public ModelAndView manager(HttpSession ses,
                                HttpServletRequest req,
                                HttpServletResponse resp) throws ServletException, IOException {
        ModelAndView ret = new ModelAndView("manager");
        User user = (User) ses.getAttribute("user");

        if (user == null) {
            resp.sendRedirect("/login");
            return null;
        }

        if (user.getUserType() != 0) {
            ret.addObject("error", "Not authorized");
        }

        ret.addObject("users", dbServ.getAllUsersExceptSelf(user.getUsername()));

        return ret;
    }

    @PostMapping("/manager/delete/{username}")
    public String deleteUser(HttpSession ses,
                             @PathVariable String username) {
        User user = (User) ses.getAttribute("user");
        if (user != null && user.getUserType() == 0) {
            dbServ.deleteUser(username);
        }

        return "redirect:/manager";
    }

    @PostMapping("/manager/upgrade/{username}")
    public String upgradeUser(HttpSession ses,
                             @PathVariable String username) {
        User user = (User) ses.getAttribute("user");
        if (user != null && user.getUserType() == 0) {
            dbServ.upgradeUserType(username);
        }

        return "redirect:/manager";
    }

    @PostMapping("/manager/downgrade/{username}")
    public String downgradeUser(HttpSession ses,
                              @PathVariable String username) {
        User user = (User) ses.getAttribute("user");
        if (user != null && user.getUserType() == 0) {
            dbServ.downgradeUserType(username);
        }

        return "redirect:/manager";
    }
}