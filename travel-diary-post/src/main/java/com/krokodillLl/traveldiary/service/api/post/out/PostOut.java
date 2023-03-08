package com.krokodillLl.traveldiary.service.api.post.out;

import java.time.Instant;
import java.util.UUID;

public interface PostOut {
    UUID getId();
    String getText();
    Instant getCreateDate();
    Instant getUpdateDate();
}
