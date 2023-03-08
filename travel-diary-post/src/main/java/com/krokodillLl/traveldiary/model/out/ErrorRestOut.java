package com.krokodillLl.traveldiary.model.out;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@Builder
public class ErrorRestOut {
    private final String uri;
    private final HttpMethod httpMethod;
    private final HttpStatus status;
    private final String timestamp;
    private final String message;
    private final Map<String, Object> additionalParams;
}
