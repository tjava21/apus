package br.com.cwi.apus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class IntegrationException extends ResponseStatusException {

    public IntegrationException(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "failed to perform " + message);
    }
}
