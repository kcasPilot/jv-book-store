package com.bookstore.jvbookstore.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        return new ResponseEntity<>(
                getErrorReport(ex.getBindingResult().getAllErrors().stream()
                        .map(this::getErrorMessage)
                        .toList(), HttpStatus.BAD_REQUEST), headers, status);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException e,
                                                                WebRequest request) {
        return handleExceptionInternal(e, getErrorReport(List.of(e.getMessage()),
                HttpStatus.NOT_FOUND), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private Map<String, Object> getErrorReport(List<String> errors, HttpStatus httpStatus) {
        Map<String, Object> report = new LinkedHashMap<>();
        report.put("time", LocalDateTime.now());
        report.put("status", httpStatus);
        report.put("errors", errors);
        return report;
    }

    private String getErrorMessage(ObjectError error) {
        if (error instanceof FieldError) {
            return ((FieldError) error).getField() + " " + error.getDefaultMessage();
        }
        return error.getDefaultMessage();
    }
}
