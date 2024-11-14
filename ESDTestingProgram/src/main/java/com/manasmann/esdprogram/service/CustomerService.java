package com.manasmann.esdprogram.service;

import com.manasmann.esdprogram.dto.CustomerRequest;
import com.manasmann.esdprogram.dto.CustomerResponse;
import com.manasmann.esdprogram.dto.LoginRequest;
import com.manasmann.esdprogram.entity.Customer;
import com.manasmann.esdprogram.exception.CustomerNotFoundException;
import com.manasmann.esdprogram.helper.EncryptionService;
import com.manasmann.esdprogram.helper.JWTHelper;
import com.manasmann.esdprogram.mapper.CustomerMapper;
import com.manasmann.esdprogram.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepo customerRepo;
    private final CustomerMapper customerMapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public String createCustomer(CustomerRequest request) {
        Customer customer = customerMapper.toCustomer(request);
        customer.setPassword(encryptionService.encode(customer.getPassword()));
        customerRepo.save(customer);
        return "Customer Created Successfully";
    }

    public Customer getCustomer(String email) {
        return customerRepo.findByEmail(email)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update Customer:: No customer found with the provided ID:: %s", email)
                ));
    }

    public List<CustomerResponse> retrieveAllCustomer() {
        List<Customer> customers = customerRepo.findAll();

        // Map the list of Customer entities to CustomerResponse DTOs
        List<CustomerResponse> customerResponses = customers.stream()
                .map(customerMapper::toCustomerResponse)  // Assuming toCustomerResponse maps a Customer to CustomerResponse
                .collect(Collectors.toList());

        // Return the list of CustomerResponse DTOs
        return customerResponses;
    }

    public CustomerResponse retrieveCustomer(String email) {
        Customer customer = getCustomer(email);
        return customerMapper.toCustomerResponse(customer);
    }

    public String login(LoginRequest request) {
        Customer customer = getCustomer(request.email());
        if (!encryptionService.validates(request.password(), customer.getPassword())) {
            return "Wrong Password or Email";
        }


        return jwtHelper.generateToken(request.email());
    }
}