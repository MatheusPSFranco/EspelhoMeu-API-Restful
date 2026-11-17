package br.com.fiap.espelhomeu.handler;

import br.com.fiap.espelhomeu.exception.EntidadeNaoEncontradaException;
import br.com.fiap.espelhomeu.exception.ErroBancoDadosException;
import br.com.fiap.espelhomeu.exception.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ErrorResponseDto> naoEncontrada(EntidadeNaoEncontradaException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDto(
                        "Entidade não encontrada",
                        e.getMessage()
                ));
    }

    @ExceptionHandler(ErroBancoDadosException.class)
    public ResponseEntity<ErrorResponseDto> erroBancoDados(ErroBancoDadosException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDto(
                        "Erro interno no servidor",
                        "Não foi possível realizar a operação no banco de dados"
                ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> validacao(MethodArgumentNotValidException e) {

        StringBuilder errors = new StringBuilder();

        e.getBindingResult().getFieldErrors().forEach(erro ->
                errors.append(erro.getDefaultMessage()).append("; ")
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(
                        "Dados inválidos",
                        errors.toString()
                ));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponseDto> erroRequisicao(ResponseStatusException e) {
        return ResponseEntity.status(e.getStatusCode())
                .body(new ErrorResponseDto(
                        "Erro na requisição",
                        e.getReason()
                ));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponseDto> tipoDeParametroInvalido(
            MethodArgumentTypeMismatchException e) {

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDto(
                        "Parâmetro inválido",
                        "O valor informado para '" + e.getName() + "' é inválido"
                ));
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ErrorResponseDto> recursoNaoEncontrado(
            NoResourceFoundException e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDto(
                        "Rota não encontrada",
                        "O endpoint informado não existe"
                ));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDto> metodoNaoPermitido(
            HttpRequestMethodNotSupportedException e) {

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(new ErrorResponseDto(
                        "Método não permitido",
                        "O método HTTP informado não é permitido neste endpoint"
                ));
    }
}