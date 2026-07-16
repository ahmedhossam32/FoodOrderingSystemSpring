package com.restaurant.foodorderingsystem.dto.request;

import com.restaurant.foodorderingsystem.enums.MealCategory;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class MealRequestDto {

    @NotBlank
    private String name;

    @NotBlank
    private String description;

    @NotNull
    private MealCategory category;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;
}
