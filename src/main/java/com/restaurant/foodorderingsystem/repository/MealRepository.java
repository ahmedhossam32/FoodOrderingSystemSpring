package com.restaurant.foodorderingsystem.repository;

import com.restaurant.foodorderingsystem.entity.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealRepository extends JpaRepository<Meal, Long> {
}
