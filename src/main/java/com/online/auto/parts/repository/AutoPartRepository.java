package com.online.auto.parts.repository;

import com.online.auto.parts.entity.AutoPart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoPartRepository extends JpaRepository<AutoPart, Long> {

}
