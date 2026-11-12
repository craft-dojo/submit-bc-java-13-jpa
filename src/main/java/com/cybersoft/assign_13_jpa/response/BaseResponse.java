package com.cybersoft.assign_13_jpa.response;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

    private int code;
    private String message;
    private Object data;
    private LocalDateTime timestamp;

    public static BaseResponse success(Object data) {
        return BaseResponse
                .builder()
                .code(HttpStatus.OK.value())
                .data(data)
                .message("Success")
                .timestamp(LocalDateTime.now())
                .build();
    }
}
