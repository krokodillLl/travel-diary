package com.krokodillLl.traveldiary.model.in;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PostIn {
    private UUID id;
    private String text;
}
