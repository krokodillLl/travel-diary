package com.krokodillLl.traveldiary.controller;

import com.krokodillLl.traveldiary.model.out.ErrorRestOut;
import com.netflix.appinfo.ApplicationInfoManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1.0/test")
@RequiredArgsConstructor
@Tag(name = "Тестовый интеграционный контроллер", description = "Тестовый интеграционный контроллер")
@Slf4j
public class TestController {
    private final ApplicationInfoManager applicationInfoManager;
    @GetMapping("/public")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Получить тестовый ответ от удаленного сервера")
    @ApiResponse(responseCode = "200", description = "Выполнено успешно")
    @ApiResponse(responseCode = "400", description = "Некорректный формат запроса",
            content = @Content(schema = @Schema(implementation = ErrorRestOut.class)))
    @ApiResponse(responseCode = "500", description = "Ошибка сервера",
            content = @Content(schema = @Schema(implementation = ErrorRestOut.class)))
    String getTestMessageFromService() {
        return "Public message from server " + applicationInfoManager.getInfo();
    }
    @GetMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Получить тестовый ответ от удаленного сервера")
    @ApiResponse(responseCode = "200", description = "Выполнено успешно")
    @ApiResponse(responseCode = "400", description = "Некорректный формат запроса",
            content = @Content(schema = @Schema(implementation = ErrorRestOut.class)))
    @ApiResponse(responseCode = "500", description = "Ошибка сервера",
            content = @Content(schema = @Schema(implementation = ErrorRestOut.class)))
    String getTestMessageFromServiceByUser() {
        return "User message from server " + applicationInfoManager.getInfo();
    }
    @GetMapping("/moderator-access")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Получить тестовый ответ от удаленного сервера")
    @ApiResponse(responseCode = "200", description = "Выполнено успешно")
    @ApiResponse(responseCode = "400", description = "Некорректный формат запроса",
            content = @Content(schema = @Schema(implementation = ErrorRestOut.class)))
    @ApiResponse(responseCode = "500", description = "Ошибка сервера",
            content = @Content(schema = @Schema(implementation = ErrorRestOut.class)))
    String getTestMessageFromServiceByManager() {
        return "Moderator message from server " + applicationInfoManager.getInfo();
    }
    @GetMapping("/admin-access")
    @ResponseStatus(HttpStatus.OK)
    @Operation(description = "Получить тестовый ответ от удаленного сервера")
    @ApiResponse(responseCode = "200", description = "Выполнено успешно")
    @ApiResponse(responseCode = "400", description = "Некорректный формат запроса",
            content = @Content(schema = @Schema(implementation = ErrorRestOut.class)))
    @ApiResponse(responseCode = "500", description = "Ошибка сервера",
            content = @Content(schema = @Schema(implementation = ErrorRestOut.class)))
    String getTestMessageFromServiceByAdmin() {
        return "Admin message from server " + applicationInfoManager.getInfo();
    }
}
