package com.krokodillLl.traveldiary.domain.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.UUID;

@Document(collection = "post")
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class PostEntity {
    @Id
    private UUID id;
    private String text;
    private Instant createDate = Instant.now();
    private Instant updateDate;
}
