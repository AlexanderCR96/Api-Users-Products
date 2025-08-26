package com.api.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @Positive
    private Double price;

    @PositiveOrZero
    private Integer stock;

    @Size(max = 500)
    private String description;
}

