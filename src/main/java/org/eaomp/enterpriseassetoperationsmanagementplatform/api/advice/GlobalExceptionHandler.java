package org.eaomp.enterpriseassetoperationsmanagementplatform.api.advice;

import lombok.extern.slf4j.Slf4j;
import org.eaomp.enterpriseassetoperationsmanagementplatform.api.model.asset.AssetResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Handles manual validation errors thrown in service
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<AssetResponseModel> handleIllegalArgument(
            IllegalArgumentException ex) {

        log.info("An error occurred: {}. \nStacktrace: {}", ex.getMessage(), ex.getStackTrace());
        AssetResponseModel response = AssetResponseModel.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<AssetResponseModel> handleRuntimeException(RuntimeException ex) {
        log.info("An error occurred: {}. \nStacktrace: {}", ex.getMessage(), ex.getStackTrace());
        AssetResponseModel response = AssetResponseModel.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<AssetResponseModel> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        log.info("An error occurred: {}. \nStacktrace: {}", ex.getMessage(), ex.getStackTrace());
        AssetResponseModel response = AssetResponseModel.error(HttpStatus.BAD_REQUEST.value(), errorMessage);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Catch-all fallback
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AssetResponseModel> handleGenericException(Exception ex) {

        log.info("An error occurred: {}", ex.getMessage());
        AssetResponseModel response = AssetResponseModel.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred");

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}