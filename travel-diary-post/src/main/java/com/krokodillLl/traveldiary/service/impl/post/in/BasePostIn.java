package com.krokodillLl.traveldiary.service.impl.post.in;

import com.krokodillLl.traveldiary.service.api.post.in.PostIn;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class BasePostIn implements PostIn {
    private UUID id;
    private String text;
}
