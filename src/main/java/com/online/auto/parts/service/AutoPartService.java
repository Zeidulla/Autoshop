package com.online.auto.parts.service;


import com.online.auto.parts.entity.AutoPart;
import com.online.auto.parts.repository.AutoPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoPartService {

    private AutoPartRepository repo;

    @Autowired
    public AutoPartService(AutoPartRepository repo) {
        this.repo = repo;
    }

    public void save(AutoPart autoPart) {
        repo.save(autoPart);
    }

}
