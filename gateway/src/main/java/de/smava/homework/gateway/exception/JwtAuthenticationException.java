package de.smava.homework.gateway.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationException extends AuthenticationException {
    /**
     * 
     */
    private static final long serialVersionUID = -8440643260905681516L;

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
