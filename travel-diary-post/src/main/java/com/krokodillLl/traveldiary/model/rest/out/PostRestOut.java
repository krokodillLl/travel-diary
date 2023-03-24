package com.krokodillLl.traveldiary.model.rest.out;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
@Schema(description = "Объект поста")
public class PostRestOut {
    @Schema(description = "Текст поста")
    private String text;
    @Schema(description = "Дата создания поста")
    private Instant createDate;
    @Schema(description = "Дата редактирования поста")
    private Instant updateDate;
}
