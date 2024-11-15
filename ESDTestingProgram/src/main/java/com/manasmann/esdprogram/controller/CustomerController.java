package com.manasmann.esdprogram.controller;

import com.manasmann.esdprogram.dto.CustomerRequest;
import com.manasmann.esdprogram.dto.CustomerResponse;
import com.manasmann.esdprogram.dto.CustomerUpdateRequest;
import com.manasmann.esdprogram.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/fetch")
    public ResponseEntity<List<CustomerResponse>> getAllCustomer() {
        return ResponseEntity.ok(customerService.retrieveAllCustomer());
    }

    @GetMapping("/{email}")
    public ResponseEntity<CustomerResponse> getCustomer(@PathVariable("email") String email) {
        return ResponseEntity.ok(customerService.retrieveCustomer(email));
    }

    @PostMapping
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
        return ResponseEntity.ok(customerService.createCustomer(request));
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("email") String email) {
        return ResponseEntity.ok(customerService.deleteCustomer(email));
    }

    @PatchMapping("/{email}")
    public ResponseEntity<String> updateCustomer(
            @PathVariable String email,
            @RequestBody @Valid CustomerUpdateRequest updateRequest) {
        return ResponseEntity.ok(customerService.updateCustomer(email, updateRequest));
    }

}
