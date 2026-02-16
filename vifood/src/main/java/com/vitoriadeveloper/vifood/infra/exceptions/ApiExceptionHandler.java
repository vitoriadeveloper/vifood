package com.vitoriadeveloper.vifood.infra.exceptions;

import com.vitoriadeveloper.vifood.domain.exceptions.*;
import com.vitoriadeveloper.vifood.infra.utils.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(KitchenNotFoundException.class)
    public ResponseEntity<Object> handleKitchenNotFound(KitchenNotFoundException e) {
        var error = new ErrorResponse(
                OffsetDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Cozinha não encontrada",
                e.getMessage()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleEntityInUse(DataIntegrityViolationException e) {
        var error = new ErrorResponse(
                OffsetDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "Entidade está em uso",
                "Não é possível excluir a cozinha porque ela está vinculada a outros registros."
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(RestaurantNotFoundException.class)
    public ResponseEntity<Object> handleRestaurantNotFound(RestaurantNotFoundException e) {
        var error = new ErrorResponse(
                OffsetDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Restaurante não encontrado",
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(StateNotFoundException.class)
    public ResponseEntity<Object> handleStateNotFound(StateNotFoundException e) {
        var error = new ErrorResponse(
                OffsetDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Estado não encontrado",
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<Object> handleCityNotFound(CityNotFoundException e) {
        var error = new ErrorResponse(
                OffsetDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Cidade não encontrada",
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(InvalidStateReferenceException.class)
    public ResponseEntity<Object> handleInvalidStateReference(InvalidStateReferenceException e) {
        var error = new ErrorResponse(
                OffsetDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Referência de estado inválida",
                e.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
