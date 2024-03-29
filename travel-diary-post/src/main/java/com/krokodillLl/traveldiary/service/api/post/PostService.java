package com.krokodillLl.traveldiary.service.api.post;


import com.krokodillLl.traveldiary.model.service.in.PostIn;
import com.krokodillLl.traveldiary.model.service.out.PostOut;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<PostOut> findAll();
    PostOut createPost(PostIn postIn);
    PostOut updatePost(PostIn postIn);
    Boolean deletePost(UUID id);
}
