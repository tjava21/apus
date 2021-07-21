package br.com.cwi.apus.exception;

import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;

public class BusinessException extends ResponseStatusException {

    public BusinessException(String message) {
        super(CONFLICT, message);
    }
}
