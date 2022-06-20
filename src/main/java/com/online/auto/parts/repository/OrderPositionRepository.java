package com.online.auto.parts.repository;

import com.online.auto.parts.entity.OrderPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPositionRepository extends JpaRepository<OrderPosition, Long> {

}
