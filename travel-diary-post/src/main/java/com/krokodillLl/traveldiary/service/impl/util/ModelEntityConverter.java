package com.krokodillLl.traveldiary.service.impl.util;

import com.krokodillLl.traveldiary.domain.entity.PostEntity;
import com.krokodillLl.traveldiary.model.service.in.PostIn;
import com.krokodillLl.traveldiary.model.service.out.PostOut;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ModelEntityConverter {
    public Optional<PostOut> convert(PostEntity postEntity) {
        if(postEntity == null) {
            return Optional.empty();
        }
        return Optional.of(
                PostOut.builder()
                        .id(postEntity.getId())
                        .text(postEntity.getText())
                        .createDate(postEntity.getCreateDate())
                        .updateDate(postEntity.getUpdateDate())
                        .build()
        );
    }

    public PostEntity convert(PostIn postIn) {
        var postEntity = new PostEntity();
        postEntity.setId(
                Optional.of(postIn)
                        .map(PostIn::getId)
                        .orElse(UUID.randomUUID())
        );
        postEntity.setText(postIn.getText());

        return postEntity;
    }
}
