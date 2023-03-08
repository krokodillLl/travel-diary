package com.krokodillLl.traveldiary.domain.repository;

import com.krokodillLl.traveldiary.domain.entity.PostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface PostRepository extends MongoRepository<PostEntity, UUID> {
}
