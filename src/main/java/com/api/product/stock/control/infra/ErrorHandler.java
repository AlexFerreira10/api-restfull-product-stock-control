package com.api.product.stock.control.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> tratarErro500(DataIntegrityViolationException ex) {
        String mensagem = "Erro de integridade de dados. Verifique se os dados inseridos já existem.";
        return ResponseEntity.status(HttpStatus.CONFLICT).body(mensagem + "\n" +ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> tratarErro400(MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> tratarErro404(EntityNotFoundException ex) {
        return ResponseEntity.notFound().build();
    }

    private record DadosErroValidacao(String campo, String mensagem) {
        public DadosErroValidacao(FieldError erro) {
            this(erro.getField(),erro.getDefaultMessage());
        }
    }
}