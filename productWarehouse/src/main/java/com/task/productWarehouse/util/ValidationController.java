package com.task.productWarehouse.util;

import com.task.productWarehouse.product.service.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
/**
 * Класс, представляющий собой глобальный обработчик ошибок
 */
@RestControllerAdvice
public class ValidationController {
    /**
     * @param ex Ошибка, возникшая при валидации запроса
     * @return Словарь, содержащий название некорректно заполненного поля и причину некорректности
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    /**
     * @param ex Ошибка, возникновение которой приложение ожидает.
     * @return Описание ошибки
     */
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundEntityExceptions(Throwable ex){
        return "API expected error - " + ex.getMessage();
    }

    /**
     * @param ex Ошибка, для которой нет отдельного обработчика
     * @return Описание ошибки
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleUnexpectedExceptions(Throwable ex){
        return "API critical error - " + ex.getMessage();
    }
}
