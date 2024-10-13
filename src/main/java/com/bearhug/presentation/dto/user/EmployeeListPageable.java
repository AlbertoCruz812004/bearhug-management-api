package com.bearhug.presentation.dto.user;

import java.util.List;

public record EmployeeListPageable(
        List<EmployeePageableResponse> employees,
        Long size,
        Long totalPage
) {
}
