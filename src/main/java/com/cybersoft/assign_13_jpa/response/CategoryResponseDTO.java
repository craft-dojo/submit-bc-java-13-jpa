package com.cybersoft.assign_13_jpa.response;

import com.cybersoft.assign_13_jpa.entity.CategoryEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryResponseDTO {

    private Long id;
    private String name;

    public static CategoryResponseDTO mapToDTO(CategoryEntity entity) {
        return CategoryResponseDTO
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
    }
}
