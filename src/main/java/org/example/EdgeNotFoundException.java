package org.example;

public class EdgeNotFoundException extends RuntimeException {
    public EdgeNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
