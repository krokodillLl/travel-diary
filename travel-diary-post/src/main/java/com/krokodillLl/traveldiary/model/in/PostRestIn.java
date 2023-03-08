package com.krokodillLl.traveldiary.model.in;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class PostRestIn {
    private UUID id;
    @NotNull
    private String text;

}
