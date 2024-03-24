package com.example.exception.exception;

import com.example.exception.i18n.I18nService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.URI;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    @Value("${spring.application.error-uri}")
    private String errorUri;

    private final I18nService i18nService;

//    @ExceptionHandler(ResourceNotFoundException.class)
//    protected ProblemDetail handleResourceNotFoundException(ResourceNotFoundException ex) {
//        return getProblemDetail(
//                HttpStatus.NOT_FOUND,
//                ex.getMessage()
//        );
//    }

    @ExceptionHandler(ResourceNotFoundExceptionWithI18n.class)
    protected ProblemDetail handleResourceNotFoundExceptionWithI18n(ResourceNotFoundExceptionWithI18n ex) {
        return getProblemDetail(
                HttpStatus.NOT_FOUND,
                i18nService.getMessage(
                        ex.getMessage(),
                        ex.getArgs()
                )
        );
    }

    private ProblemDetail getProblemDetail(HttpStatus httpStatus, String detail) {
        ProblemDetail pd = ProblemDetail.forStatusAndDetail(
                httpStatus,
                detail
        );
        pd.setType(URI.create(errorUri));
        return pd;
    }
}