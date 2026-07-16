package com.restaurant.foodorderingsystem.service;

import com.restaurant.foodorderingsystem.dto.request.OrderItemRequestDto;
import com.restaurant.foodorderingsystem.dto.request.OrderRequestDto;
import com.restaurant.foodorderingsystem.dto.response.OrderItemResponseDto;
import com.restaurant.foodorderingsystem.dto.response.OrderResponseDto;
import com.restaurant.foodorderingsystem.entity.Customer;
import com.restaurant.foodorderingsystem.entity.Meal;
import com.restaurant.foodorderingsystem.entity.Order;
import com.restaurant.foodorderingsystem.entity.OrderItem;
import com.restaurant.foodorderingsystem.exception.ResourceNotFoundException;
import com.restaurant.foodorderingsystem.repository.CustomerRepository;
import com.restaurant.foodorderingsystem.repository.MealRepository;
import com.restaurant.foodorderingsystem.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final MealRepository mealRepository;

    public OrderResponseDto createOrder(OrderRequestDto request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + request.getCustomerId()));

        BigDecimal totalPrice = BigDecimal.ZERO;
        List<OrderItem> orderItems = new java.util.ArrayList<>();

        for (OrderItemRequestDto itemRequest : request.getItems()) {
            Meal meal = mealRepository.findById(itemRequest.getMealId())
                    .orElseThrow(() -> new ResourceNotFoundException("Meal not found with id: " + itemRequest.getMealId()));

            BigDecimal priceAtOrder = meal.getPrice();

            OrderItem orderItem = OrderItem.builder()
                    .meal(meal)
                    .quantity(itemRequest.getQuantity())
                    .priceAtOrder(priceAtOrder)
                    .build();

            totalPrice = totalPrice.add(priceAtOrder.multiply(BigDecimal.valueOf(itemRequest.getQuantity())));
            orderItems.add(orderItem);
        }

        Order order = Order.builder()
                .customer(customer)
                .orderDate(LocalDateTime.now())
                .totalPrice(totalPrice)
                .orderItems(orderItems)
                .build();

        orderItems.forEach(item -> item.setOrder(order));

        Order savedOrder = orderRepository.save(order);
        return toResponseDto(savedOrder);
    }

    public OrderResponseDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        return toResponseDto(order);
    }

    public List<OrderResponseDto> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::toResponseDto)
                .toList();
    }

    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }

    private OrderResponseDto toResponseDto(Order order) {
        List<OrderItemResponseDto> itemDtos = order.getOrderItems().stream()
                .map(this::toItemResponseDto)
                .toList();

        return OrderResponseDto.builder()
                .id(order.getId())
                .customerId(order.getCustomer().getId())
                .customerName(order.getCustomer().getName())
                .orderDate(order.getOrderDate())
                .totalPrice(order.getTotalPrice())
                .items(itemDtos)
                .build();
    }

    private OrderItemResponseDto toItemResponseDto(OrderItem item) {
        BigDecimal subtotal = item.getPriceAtOrder().multiply(BigDecimal.valueOf(item.getQuantity()));

        return OrderItemResponseDto.builder()
                .mealId(item.getMeal().getId())
                .mealName(item.getMeal().getName())
                .quantity(item.getQuantity())
                .priceAtOrder(item.getPriceAtOrder())
                .subtotal(subtotal)
                .build();
    }
}
