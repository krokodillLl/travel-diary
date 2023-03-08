package com.krokodillLl.traveldiary.controller.exception;

import com.krokodillLl.traveldiary.model.out.ErrorRestOut;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final List<Class<? extends Exception>> EXCEPTIONS_400_STATUS_CODE = List.of(
            MethodArgumentNotValidException.class,
            HttpMessageNotReadableException.class,
            IOException.class);

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleRequestException(Exception exception, WebRequest webRequest) {
        log.info("Exception handler invoked for exception", exception);

        return handleExceptionInternal(exception,
                createErrorRestOut(exception),
                new HttpHeaders(),
                defineStatus(exception),
                webRequest);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleExceptionInternal(ex,
                createErrorRestOut(ex),
                new HttpHeaders(),
                defineStatus(ex),
                request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        return handleExceptionInternal(ex,
                createErrorRestOut(ex),
                new HttpHeaders(),
                defineStatus(ex),
                request);
    }

    private ErrorRestOut createErrorRestOut(Exception exception) {
        return ErrorRestOut.builder()
                .timestamp(ZonedDateTime.now().toString())
                .message(getErrorMessageFromException(exception))
                .build();
    }

    private String getErrorMessageFromException(Exception e) {
        if (e instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException ex = (MethodArgumentNotValidException) e;
            return ex.getBindingResult()
                    .getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining(";"));
        }
        return e.getLocalizedMessage();
    }

    private HttpStatus defineStatus(Exception e) {
        if (EXCEPTIONS_400_STATUS_CODE.contains(e.getClass())) {
            return HttpStatus.BAD_REQUEST;
        }
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}
