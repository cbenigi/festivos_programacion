package co.edu.festivos.ui.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Map<String, String>> fechaInvalida(DateTimeParseException ex) {
        return ResponseEntity.badRequest().body(Map.of("error", "La fecha no es válida. Usa el formato yyyy-MM-dd, por ejemplo 2023-06-12."));
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> noEncontrado(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "No se encontró el registro solicitado."));
    }
}
