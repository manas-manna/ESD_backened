package com.manasmann.esdprogram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manasmann.esdprogram.validation.ValidationGroups.*;
import jakarta.validation.constraints.*;

public record CustomerRequest(
        @NotBlank(message = "Customer should be present", groups = {CustomerRegistrationGroup.class, CustomerUpdateGroup.class})
        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName,

        @NotBlank(message="Customer email is required",groups = {CustomerRegistrationGroup.class, CustomerLoginGroup.class})
        @Email(message = "Email must be in correct format")
        @JsonProperty("email")
        String email,

        @NotBlank(message = "Password should be present",groups = {CustomerRegistrationGroup.class, CustomerLoginGroup.class})
        @Size(min = 6, max = 12)
        @JsonProperty("password")
        String password
) {
}
