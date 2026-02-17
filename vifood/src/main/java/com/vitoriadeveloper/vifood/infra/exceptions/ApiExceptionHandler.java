package com.vitoriadeveloper.vifood.infra.exceptions;

import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.vitoriadeveloper.vifood.domain.exceptions.*;
import com.vitoriadeveloper.vifood.infra.utils.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private Throwable rootCause;

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
                e.getMessage());

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

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, Object body, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var error = new ErrorResponse(
                OffsetDateTime.now(),
                status.value(),
                status.toString(),
                ex.getMessage()

        );
        return ResponseEntity.status(status).body(error);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        String detail = "O corpo da requisição contém um formato inválido. Verifique os dados enviados.";

        Throwable rootCause = ex.getCause();

        if (rootCause instanceof InvalidFormatException invalidEx) {

            String path = invalidEx.getPath().stream()
                    .map(ref -> ref.getFieldName())
                    .reduce((a, b) -> a + "." + b)
                    .orElse("");

            detail = String.format(
                    "A propriedade '%s' recebeu o valor '%s', que é incompatível com o tipo '%s'.",
                    path,
                    invalidEx.getValue(),
                    invalidEx.getTargetType().getSimpleName()
            );
        }
        else if (rootCause instanceof PropertyBindingException){

            String path = ((IgnoredPropertyException) rootCause).getPath().stream()
                    .map(ref -> ref.getFieldName())
                    .reduce((a, b) -> a + "." + b)
                    .orElse("");

            detail = String.format(
                    "A propriedade '%s' não é reconhecida. Verifique se o nome está correto ou se ela foi uma propriedade ignorada.",
                    path
            );
        }

        var error = new ErrorResponse(
                OffsetDateTime.now(),
                status.value(),
                "Formato de entrada inválido",
                detail
        );

        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String detail = String.format(
                "O parâmetro de URL '%s' recebeu o valor '%s', que é incompatível com o tipo '%s'.",
                ex.getName(),
                ex.getValue(),
                ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "desconecido"
        );

        var error = new ErrorResponse(
                OffsetDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Parâmetro de URL inválido",
                detail
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }


}
