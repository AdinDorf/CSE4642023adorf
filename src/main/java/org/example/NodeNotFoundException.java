package org.example;

public class NodeNotFoundException extends RuntimeException {
    public NodeNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
