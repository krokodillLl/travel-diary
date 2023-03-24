package com.krokodillLl.traveldiary.controller.post;

import com.krokodillLl.traveldiary.controller.util.DtoModelConverter;
import com.krokodillLl.traveldiary.model.rest.in.PostRestIn;
import com.krokodillLl.traveldiary.model.rest.out.ErrorRestOut;
import com.krokodillLl.traveldiary.model.rest.out.PostRestOut;
import com.krokodillLl.traveldiary.service.api.post.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/v1.0/post")
@RequiredArgsConstructor
@Tag(name = "Операции с постами", description = "Загрузка и выгрузка постов, работа с ними")
@Slf4j
public class PostController {

    private final PostService postService;
    private final DtoModelConverter converter;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Получить все посты")
    @ApiResponse(responseCode = "200", description = "Выполнено успешно")
    @ApiResponse(responseCode = "400", description = "Некорректный формат запроса",
            content = @Content(schema = @Schema(implementation = ErrorRestOut.class)))
    @ApiResponse(responseCode = "500", description = "Ошибка сервера",
            content = @Content(schema = @Schema(implementation = ErrorRestOut.class)))
    List<PostRestOut> getAllPosts() {
        return postService.findAll().stream()
                .map(converter::convert)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(description = "Создать пост")
    @ApiResponse(responseCode = "200", description = "Выполнено успешно")
    @ApiResponse(responseCode = "400", description = "Некорректный формат запроса",
            content = @Content(schema = @Schema(implementation = ErrorRestOut.class)))
    @ApiResponse(responseCode = "500", description = "Ошибка сервера",
            content = @Content(schema = @Schema(implementation = ErrorRestOut.class)))
    PostRestOut createPost(
            @Parameter(description = "Тело поста")
            @Valid @RequestBody PostRestIn postIn
    ) {
        return converter.convert(
                postService.createPost(
                        converter.convert(postIn)
                )
        ).orElse(null);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Обновить пост")
    @ApiResponse(responseCode = "200", description = "Выполнено успешно")
    @ApiResponse(responseCode = "400", description = "Некорректный формат запроса",
            content = @Content(schema = @Schema(implementation = ErrorRestOut.class)))
    @ApiResponse(responseCode = "500", description = "Ошибка сервера",
            content = @Content(schema = @Schema(implementation = ErrorRestOut.class)))
    PostRestOut updatePost(
            @Parameter(description = "Тело поста")
            @Valid @RequestBody PostRestIn postIn) {
        return converter.convert(
                        postService.updatePost(converter.convert(postIn))
                )
                .orElse(null);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(description = "Удалить пост")
    @ApiResponse(responseCode = "200", description = "Выполнено успешно")
    @ApiResponse(responseCode = "400", description = "Некорректный формат запроса",
            content = @Content(schema = @Schema(implementation = ErrorRestOut.class)))
    @ApiResponse(responseCode = "500", description = "Ошибка сервера",
            content = @Content(schema = @Schema(implementation = ErrorRestOut.class)))
    Boolean deletePost(
            @Parameter(description = "Id поста")
            @PathVariable UUID id) {
        return postService.deletePost(id);
    }
}
