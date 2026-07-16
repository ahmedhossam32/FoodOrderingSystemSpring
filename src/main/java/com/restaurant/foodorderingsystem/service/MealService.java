package com.restaurant.foodorderingsystem.service;

import com.restaurant.foodorderingsystem.dto.request.MealRequestDto;
import com.restaurant.foodorderingsystem.dto.response.MealResponseDto;
import com.restaurant.foodorderingsystem.entity.Meal;
import com.restaurant.foodorderingsystem.exception.ResourceNotFoundException;
import com.restaurant.foodorderingsystem.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;

    public MealResponseDto createMeal(MealRequestDto request) {
        Meal meal = toEntity(request);
        Meal savedMeal = mealRepository.save(meal);
        return toResponseDto(savedMeal);
    }

    public MealResponseDto getMealById(Long id) {
        Meal meal = mealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found with id: " + id));
        return toResponseDto(meal);
    }

    public List<MealResponseDto> getAllMeals() {
        return mealRepository.findAll().stream()
                .map(this::toResponseDto)
                .toList();
    }

    public MealResponseDto updateMeal(Long id, MealRequestDto request) {
        Meal existingMeal = mealRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Meal not found with id: " + id));

        existingMeal.setName(request.getName());
        existingMeal.setDescription(request.getDescription());
        existingMeal.setCategory(request.getCategory());
        existingMeal.setPrice(request.getPrice());

        Meal savedMeal = mealRepository.save(existingMeal);
        return toResponseDto(savedMeal);
    }

    public void deleteMeal(Long id) {
        if (!mealRepository.existsById(id)) {
            throw new ResourceNotFoundException("Meal not found with id: " + id);
        }
        mealRepository.deleteById(id);
    }

    private Meal toEntity(MealRequestDto dto) {
        return Meal.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .price(dto.getPrice())
                .build();
    }

    private MealResponseDto toResponseDto(Meal meal) {
        return MealResponseDto.builder()
                .id(meal.getId())
                .name(meal.getName())
                .description(meal.getDescription())
                .category(meal.getCategory())
                .price(meal.getPrice())
                .build();
    }
}
