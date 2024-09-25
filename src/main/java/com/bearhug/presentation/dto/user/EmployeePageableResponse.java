package com.bearhug.presentation.dto.user;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.UUID;

@JsonPropertyOrder({"folio", "name", "lastname", "curp", "age"})
public record EmployeePageableResponse(
        UUID folio,
        String name,
        String lastname,
        String curp,
        Byte age
) {
}
