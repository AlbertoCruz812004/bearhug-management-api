package com.bearhug.presentation.dto.user;

import java.util.UUID;

public record EmployeeResponse(
        UUID folio,
        String name,
        String lastname,
        Byte age,
        String curp
) {
}
