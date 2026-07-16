package com.restaurant.foodorderingsystem.repository;

import com.restaurant.foodorderingsystem.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
