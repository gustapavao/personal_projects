package br.com.pavao.personal_projects.exception;

import java.io.Serial;

public class ServiceException extends Exception {

    public ServiceException(String message) {
        super(message);
    }


    @Serial
    private static final long serialVersionUID = 1L;
}