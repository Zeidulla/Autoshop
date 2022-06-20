package com.online.auto.parts.controller;

import com.online.auto.parts.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession(true).invalidate();
        return "redirect:/";
    }
}
