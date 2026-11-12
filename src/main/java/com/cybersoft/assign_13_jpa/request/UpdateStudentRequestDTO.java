package com.cybersoft.assign_13_jpa.request;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStudentRequestDTO {

    private Long id;
    private String name;
    private String email;
    private String major;
}
