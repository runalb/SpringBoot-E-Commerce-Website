package com.runalb.major.repository;

import com.runalb.major.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Integer> {
}
