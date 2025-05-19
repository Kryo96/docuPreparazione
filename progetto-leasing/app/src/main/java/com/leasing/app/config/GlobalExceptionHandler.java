package com.leasing.app.config;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

/**
 * ritorna al client
 * type
 * title
 * status
 * detail
 * invalidField
 * message
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ProblemDetail handleAuthenticationException(AuthenticationException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.UNAUTHORIZED);
        problemDetail.setTitle("Errore di autenticazione");
        problemDetail.setDetail("Credenziali non valide");
        return problemDetail;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ProblemDetail handleAccessDeniedException(AccessDeniedException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.FORBIDDEN);
        problemDetail.setTitle("Accesso negato");
        problemDetail.setDetail("Non hai i permessi per accedere a questa risorsa.");
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Errore di validazione");
        problemDetail.setDetail("Uno o più campi non sono validi.");

        // Puoi anche aggiungere i dettagli dei campi invalidi:
        var firstError = ex.getBindingResult().getFieldErrors().stream().findFirst();
        firstError.ifPresent(error -> {
            problemDetail.setProperty("invalidField", error.getField());
            problemDetail.setProperty("message", error.getDefaultMessage());
        });

        return problemDetail;
    }

    @ExceptionHandler({EntityNotFoundException.class, NoSuchElementException.class})
    public ProblemDetail handleNotFound(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Risorsa non trovata");
        problemDetail.setDetail(ex.getMessage() != null ? ex.getMessage() : "La risorsa richiesta non è stata trovata.");
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("Errore interno");
        problemDetail.setDetail("Si è verificato un errore inaspettato.");
        return problemDetail;
    }
}
