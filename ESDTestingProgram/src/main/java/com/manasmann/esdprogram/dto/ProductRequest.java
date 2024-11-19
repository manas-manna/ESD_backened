package com.manasmann.esdprogram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.manasmann.esdprogram.validation.ValidationGroups.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequest(
        @NotBlank(message = "Product name cannot be blank",groups = {ProductCreateGroup.class})
        @JsonProperty("name")
        String name,


        @NotNull(message = "Product price is required",groups = {ProductUpdateGroup.class})
        @JsonProperty("price")
        double price
) {
}
