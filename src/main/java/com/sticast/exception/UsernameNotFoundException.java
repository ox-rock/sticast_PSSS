package com.sticast.exception;

public class UsernameNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public UsernameNotFoundException(Object username) {
        super(username != null ? username.toString() : null);
    }
}
