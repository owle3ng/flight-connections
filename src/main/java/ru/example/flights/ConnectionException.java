package ru.example.flights;

public class ConnectionException extends RuntimeException {
    public ConnectionException(String message){
        super(message);
    }
}
