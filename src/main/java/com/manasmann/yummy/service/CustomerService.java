package com.manasmann.yummy.service;

import com.manasmann.yummy.dto.CustomerRequest;
import com.manasmann.yummy.entity.Customer;
import com.manasmann.yummy.mapper.CustomerMapper;
import com.manasmann.yummy.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Customer created";
    }

    public String loginCustomer(CustomerRequest request) {
        Customer customer = repo.findByEmail(request.email());

        if (customer == null) {
            return "User not found";
        }

        // Check if the password matches
        if (request.password().equals(customer.getPassword())) {
            return "Logged in successfully";
        } else {
            return "Wrong password";
        }
    }
}
