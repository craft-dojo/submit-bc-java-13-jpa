package com.cybersoft.assign_13_jpa.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequestDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private Long categoryId;
}
