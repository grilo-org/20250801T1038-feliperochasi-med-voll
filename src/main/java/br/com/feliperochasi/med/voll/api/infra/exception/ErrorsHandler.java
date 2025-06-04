package br.com.feliperochasi.med.voll.api.infra.exception;

import br.com.feliperochasi.med.voll.api.domain.ValidationIdException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorsHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity errorHandler404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity errorHandler400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ErrorsDataValidation::new).toList());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity errorHandlerBadCredentials() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas");
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity errorHandlerAuthentication() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity errorHandlerAcessoNegado() {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso negado");
    }

    @ExceptionHandler(ValidationIdException.class)
    public ResponseEntity errorHandlerValidators(ValidationIdException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity errorHandler500(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " +ex.getLocalizedMessage());
    }

    private record ErrorsDataValidation(String campo, String mensagem){
        public ErrorsDataValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
