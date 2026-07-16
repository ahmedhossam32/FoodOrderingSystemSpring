package com.restaurant.foodorderingsystem.controller;

import com.restaurant.foodorderingsystem.dto.request.MealRequestDto;
import com.restaurant.foodorderingsystem.dto.response.MealResponseDto;
import com.restaurant.foodorderingsystem.service.MealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/meals")
@RequiredArgsConstructor
public class MealController {

    private final MealService mealService;

    @PostMapping
    public ResponseEntity<MealResponseDto> createMeal(@Valid @RequestBody MealRequestDto request) {
        MealResponseDto response = mealService.createMeal(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MealResponseDto> getMealById(@PathVariable Long id) {
        MealResponseDto response = mealService.getMealById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<MealResponseDto>> getAllMeals() {
        List<MealResponseDto> response = mealService.getAllMeals();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MealResponseDto> updateMeal(@PathVariable Long id, @Valid @RequestBody MealRequestDto request) {
        MealResponseDto response = mealService.updateMeal(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeal(@PathVariable Long id) {
        mealService.deleteMeal(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
