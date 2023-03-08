package com.krokodillLl.traveldiary.service.impl.post.out;

import com.krokodillLl.traveldiary.service.api.post.out.PostOut;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
public class BasePostOut implements PostOut {
    private UUID id;
    private String text;
    private Instant createDate;
    private Instant updateDate;
}
