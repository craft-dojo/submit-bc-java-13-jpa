package com.cybersoft.assign_13_jpa.response;

import com.cybersoft.assign_13_jpa.entity.StudentEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String major;

    public static StudentResponseDTO toDTO(StudentEntity entity) {
        return StudentResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .major(entity.getMajor())
                .build();
    }
}
