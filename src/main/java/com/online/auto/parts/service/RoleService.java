package com.online.auto.parts.service;

import com.online.auto.parts.entity.Role;
import com.online.auto.parts.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository repository;

    public void save(Role role) {
        repository.save(role);
    }

}
