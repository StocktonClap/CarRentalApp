package com.ms.exceptions;

public class UserNotFoundExpection extends Throwable {
    public UserNotFoundExpection(String message) {
        super(message);
    }
}
