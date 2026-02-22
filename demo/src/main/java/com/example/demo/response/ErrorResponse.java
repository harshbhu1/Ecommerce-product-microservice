package com.example.demo.response;
import lombok.*;

import  java.time.Instant;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorResponse {

    private  int status;
    private  String message;
    private  Instant timestamp;

    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = Instant.now();
    }
}
