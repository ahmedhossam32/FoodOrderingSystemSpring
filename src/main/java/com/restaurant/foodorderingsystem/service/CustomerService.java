package com.restaurant.foodorderingsystem.service;

import com.restaurant.foodorderingsystem.dto.request.CustomerRequestDto;
import com.restaurant.foodorderingsystem.dto.response.CustomerResponseDto;
import com.restaurant.foodorderingsystem.entity.Customer;
import com.restaurant.foodorderingsystem.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerResponseDto createCustomer(CustomerRequestDto request) {
        Customer customer = toEntity(request);
        Customer savedCustomer = customerRepository.save(customer);
        return toResponseDto(savedCustomer);
    }

    public CustomerResponseDto getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        return toResponseDto(customer);
    }

    public List<CustomerResponseDto> getAllCustomers() {
        return customerRepository.findAll().stream()
                .map(this::toResponseDto)
                .toList();
    }

    public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto request) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        existingCustomer.setName(request.getName());
        existingCustomer.setEmail(request.getEmail());
        existingCustomer.setPhoneNumber(request.getPhoneNumber());
        existingCustomer.setAddress(request.getAddress());

        Customer savedCustomer = customerRepository.save(existingCustomer);
        return toResponseDto(savedCustomer);
    }

    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }

    private Customer toEntity(CustomerRequestDto dto) {
        return Customer.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .address(dto.getAddress())
                .build();
    }

    private CustomerResponseDto toResponseDto(Customer customer) {
        return CustomerResponseDto.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .build();
    }
}
