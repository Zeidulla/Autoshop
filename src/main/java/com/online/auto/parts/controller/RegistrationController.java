package com.online.auto.parts.controller;

import com.online.auto.parts.entity.Role;
import com.online.auto.parts.entity.RoleType;
import com.online.auto.parts.entity.User;
import com.online.auto.parts.repository.UserRepository;
import com.online.auto.parts.service.RoleService;
import com.online.auto.parts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

import static com.online.auto.parts.entity.RoleType.*;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/registration")
    public String register(Model model) {
        User user = new User();

        model.addAttribute("user", user);

        return "registration";
    }

    @PostMapping("/registration")
    public String postRegister(@ModelAttribute("user") User user) {
        Role role = user.getRole().equals(RoleType.CUSTOMER.getRoleName()) ? RoleType.CUSTOMER.getRole() : RoleType.SELLER.getRole();
        user.addRole(role);

        roleService.save(role);
        userService.save(user);

        return "redirect:/login";
    }

}
