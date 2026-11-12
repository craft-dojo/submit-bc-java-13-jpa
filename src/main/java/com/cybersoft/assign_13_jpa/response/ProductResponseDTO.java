package com.cybersoft.assign_13_jpa.response;

import com.cybersoft.assign_13_jpa.entity.ProductEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class ProductResponseDTO {
    private Long id;
    private String name;
    private BigDecimal price;
    private CategoryResponseDTO category;

    public static ProductResponseDTO mapToDTO(ProductEntity entity) {
        CategoryResponseDTO categoryResponseDTO = CategoryResponseDTO.mapToDTO(entity.getCategory());

        return ProductResponseDTO
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .category(categoryResponseDTO)
                .build();
    }
}
