package com.online.auto.parts.repository;

import com.online.auto.parts.entity.OrderPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderPositionRepository extends JpaRepository<OrderPosition, Long> {
}
