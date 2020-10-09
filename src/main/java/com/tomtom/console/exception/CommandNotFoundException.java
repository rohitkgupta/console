package com.tomtom.console.exception;

public class CommandNotFoundException extends Exception {
    public CommandNotFoundException(String message){
        super(message);
    }
}
