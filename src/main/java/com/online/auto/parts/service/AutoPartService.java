package com.online.auto.parts.service;


import com.online.auto.parts.entity.AutoPart;
import com.online.auto.parts.repository.AutoPartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AutoPartService {

    @Autowired
    private AutoPartRepository repo;

    public void save(AutoPart autoPart) {
        repo.save(autoPart);
    }

    public List<AutoPart> findAll() {
        return repo.findAll();
    }

    public AutoPart findAutoPartById(Long id) {
        return repo.findById(id).get();
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public void update(AutoPart autoPart) {
        repo.save(autoPart);
    }

}
