package com.krokodillLl.traveldiary.service.impl.post;

import com.krokodillLl.traveldiary.domain.entity.PostEntity;
import com.krokodillLl.traveldiary.domain.repository.PostRepository;
import com.krokodillLl.traveldiary.service.api.post.PostService;
import com.krokodillLl.traveldiary.service.api.post.in.PostIn;
import com.krokodillLl.traveldiary.service.api.post.out.PostOut;
import com.krokodillLl.traveldiary.service.impl.util.ModelEntityConverter;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelEntityConverter converter;

    @PostConstruct
    private void fillDb() {
        if (!postRepository.findAll().isEmpty()) {
            return;
        }

        List<PostEntity> posts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            var post = new PostEntity();
            post.setId(UUID.randomUUID());
            post.setText("Random string " + (i + 1));
            post.setCreateDate(Instant.now());
            posts.add(post);
        }
        postRepository.saveAll(posts);
    }

    @Override
    public List<PostOut> findAll() {
        return postRepository.findAll().stream()
                .map(converter::convert)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    @Override
    public PostOut createPost(PostIn postIn) {
        return converter.convert(
                postRepository.save(
                        converter.convert(postIn)
                )
        ).orElse(null);
    }

    @Override
    public PostOut updatePost(PostIn postIn) {
        var postEntity = converter.convert(postIn);
        var dbPostOptional = postRepository.findById(postEntity.getId());
        var updatedPost = postRepository.save(
                dbPostOptional.map(dbPost -> {
                            dbPost.setText(postEntity.getText());
                            dbPost.setUpdateDate(Instant.now());

                            return dbPost;
                        })
                        .orElseThrow(() -> new NotFoundException("Post with id " + postIn.getId() + " not found"))
        );

        return converter.convert(updatedPost)
                .orElse(null);
    }

    @Override
    public Boolean deletePost(UUID id) {
        postRepository.deleteById(id);

        return true;
    }
}
