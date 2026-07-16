package com.restaurant.foodorderingsystem.dto.response;

import com.restaurant.foodorderingsystem.enums.MealCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MealResponseDto {

    private Long id;

    private String name;

    private String description;

    private MealCategory category;

    private BigDecimal price;
}
