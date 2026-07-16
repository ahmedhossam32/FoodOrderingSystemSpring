package com.restaurant.foodorderingsystem.repository;

import com.restaurant.foodorderingsystem.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}