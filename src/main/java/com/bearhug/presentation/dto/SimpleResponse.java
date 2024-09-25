package com.bearhug.presentation.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;

@JsonPropertyOrder({"message", "data"})
public record SimpleResponse<T>(
        @NotBlank
        String message,
        @NotNull
        T data
) {
}
