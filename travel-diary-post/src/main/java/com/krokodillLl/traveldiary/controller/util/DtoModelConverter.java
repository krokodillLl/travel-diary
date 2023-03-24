package com.krokodillLl.traveldiary.controller.util;

import com.krokodillLl.traveldiary.model.rest.in.PostRestIn;
import com.krokodillLl.traveldiary.model.service.out.PostOut;
import com.krokodillLl.traveldiary.model.rest.out.PostRestOut;
import com.krokodillLl.traveldiary.model.service.in.PostIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DtoModelConverter {
    public Optional<PostRestOut> convert(PostOut postOut) {
        if (postOut == null) {
            return Optional.empty();
        }
        return Optional.of(
                PostRestOut.builder()
                        .text(postOut.getText())
                        .createDate(postOut.getCreateDate())
                        .updateDate(postOut.getUpdateDate())
                        .build()
        );
    }

    public PostIn convert(PostRestIn postIn) {
        return PostIn.builder()
                .id(postIn.getId())
                .text(postIn.getText())
                .build();
    }
}
