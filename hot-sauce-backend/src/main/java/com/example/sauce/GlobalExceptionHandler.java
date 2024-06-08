package com.example.sauce;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
  // TODO misschien is het handig om een class met custom exceptions te maken, beter leesbaar
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<String> handleEntityNotFoundException(
      EntityNotFoundException entityNotFoundException) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entity does not exist");
  }
}
