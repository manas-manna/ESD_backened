package com.manasmann.yummy.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manasmann.yummy.validation.ValidationGroups.RegistrationGroup;
import com.manasmann.yummy.validation.ValidationGroups.LoginGroup;
import jakarta.validation.constraints.*;

public record CustomerRequest(
        @NotNull(message = "Customer should be present", groups = RegistrationGroup.class)
        @NotEmpty(message = "Customer should be present", groups = RegistrationGroup.class)
        @NotBlank(message = "Customer should be present", groups = RegistrationGroup.class)
        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName,

        @NotNull(message="Customer email is required", groups = RegistrationGroup.class)
        @Email(message = "Email must be in correct format", groups = RegistrationGroup.class)
        @JsonProperty("email")
        String email,

        @NotNull(message = "Password should be present",groups = {LoginGroup.class, RegistrationGroup.class})
        @NotEmpty(message = "Password should be present",groups = {LoginGroup.class, RegistrationGroup.class})
        @NotBlank(message = "Password should be present",groups = {LoginGroup.class, RegistrationGroup.class})
        @Size(min = 6, max = 12, groups = {LoginGroup.class, RegistrationGroup.class})
        @JsonProperty("password")
        String password
) {

}
