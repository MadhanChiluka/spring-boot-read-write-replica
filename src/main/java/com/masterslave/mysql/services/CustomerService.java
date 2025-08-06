package com.masterslave.mysql.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.masterslave.mysql.entities.Customer;
import com.masterslave.mysql.model.CustomerRequest;
import com.masterslave.mysql.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(CustomerRequest customerRequest) {
        Customer customer = Customer.builder().name(customerRequest.getName())
                .address(customerRequest.getAddress())
                .email(customerRequest.getEmail())
                .phone(customerRequest.getPhone())
                .createdAt(LocalDateTime.now())
                .build();
        return customerRepository.save(customer);
    }

    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}
