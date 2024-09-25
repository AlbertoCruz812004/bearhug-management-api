package com.bearhug.presentation.dto.user;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PersonUpdateRequest(
        @NotBlank @Size(min = 18, max = 18, message = "The curp provided is not valid")
        String curp,
        @NotBlank @Size(max = 50, message = "The name cannot be longer than 50 characters")
        String name,
        @NotBlank @Size(max = 50, message = "Surnames cannot be longer than 50 characters")
        String lastname,
        @Max(value = 100, message = "Maximum age is 100 years")
        @Min(value = 18, message = "Minimum age is 18")
        Byte age
){}
