package com.restaurant.foodorderingsystem.repository;

import com.restaurant.foodorderingsystem.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
