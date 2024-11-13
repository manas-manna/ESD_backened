package com.manasmann.yummy.controller;

import com.manasmann.yummy.dto.CustomerRequest;
import com.manasmann.yummy.entity.Customer;
import com.manasmann.yummy.service.CustomerService;
import com.manasmann.yummy.validation.ValidationGroups;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import com.manasmann.yummy.validation.ValidationGroups.RegistrationGroup;
import com.manasmann.yummy.validation.ValidationGroups.LoginGroup;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid @Validated(RegistrationGroup.class) CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginCustomer(@RequestBody @Valid @Validated(LoginGroup.class) CustomerRequest request) {
        return ResponseEntity.ok(customerService.loginCustomer(request));
    }
}
