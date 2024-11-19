package com.manasmann.esdprogram.controller;

import com.manasmann.esdprogram.dto.CustomerRequest;
import com.manasmann.esdprogram.service.CustomerService;
import com.manasmann.esdprogram.validation.ValidationGroups.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final CustomerService customerService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid @Validated(CustomerLoginGroup.class) CustomerRequest request) {
        return ResponseEntity.ok(customerService.login(request));
    }
}
