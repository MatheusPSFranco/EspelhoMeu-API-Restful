package br.com.fiap.espelhomeu.exception;

public class ErroBancoDadosException extends RuntimeException {

    public ErroBancoDadosException(String message, Throwable cause) {
        super(message, cause);
    }
}