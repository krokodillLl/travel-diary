package com.krokodillLl.traveldiary.model.out;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class PostOut {
    private UUID id;
    private String text;
    private Instant createDate;
    private Instant updateDate;
}
