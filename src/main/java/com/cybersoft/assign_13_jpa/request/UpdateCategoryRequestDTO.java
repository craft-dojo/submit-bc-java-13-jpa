package com.cybersoft.assign_13_jpa.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryRequestDTO {
    private Long id;
    private String name;
}
