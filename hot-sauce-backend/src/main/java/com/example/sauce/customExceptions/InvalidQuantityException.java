package com.example.sauce.customExceptions;

public class InvalidQuantityException extends RuntimeException {
  public InvalidQuantityException(String message) {
    super(message);
  }
}
