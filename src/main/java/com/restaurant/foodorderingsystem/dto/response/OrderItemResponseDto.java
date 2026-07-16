package com.restaurant.foodorderingsystem.dto.response;

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
public class OrderItemResponseDto {

    private Long mealId;

    private String mealName;

    private Integer quantity;

    private BigDecimal priceAtOrder;

    private BigDecimal subtotal;
}
