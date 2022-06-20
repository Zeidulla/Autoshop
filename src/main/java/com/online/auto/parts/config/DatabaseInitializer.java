package com.online.auto.parts.config;

import com.online.auto.parts.entity.AutoPart;
import com.online.auto.parts.entity.Role;
import com.online.auto.parts.entity.User;
import com.online.auto.parts.service.AutoPartService;
import com.online.auto.parts.service.RoleService;
import com.online.auto.parts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.online.auto.parts.entity.RoleType.*;

@Component
public class DatabaseInitializer {
    private UserService userService;
    private RoleService roleService;
    private AutoPartService autoPartService;

    @Autowired
    public DatabaseInitializer(UserService userService, RoleService roleService, AutoPartService autoPartService) {
        this.userService = userService;
        this.roleService = roleService;
        this.autoPartService = autoPartService;
    }
}
