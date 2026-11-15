package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class AtualizacaoSimulacao {

    @NotNull
    @Positive
    private Long usuarioId;

    @Positive
    private Long corteId;

    @Positive
    private Long corCabeloId;

    @Positive
    private Long penteadoId;

    @NotNull
    @NotEmpty
    private byte[] imagem;

    @NotNull
    private LocalDateTime data;

    //construtores

    public AtualizacaoSimulacao() {}

    public AtualizacaoSimulacao(Long usuarioId, Long corteId, Long corCabeloId, Long penteadoId, byte[] imagem, LocalDateTime data) {
        this.usuarioId = usuarioId;
        this.corteId = corteId;
        this.corCabeloId = corCabeloId;
        this.penteadoId = penteadoId;
        this.imagem = imagem;
        this.data = data;
    }

    //getters and setters

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getCorteId() {
        return corteId;
    }

    public void setCorteId(Long corteId) {
        this.corteId = corteId;
    }

    public Long getCorCabeloId() {
        return corCabeloId;
    }

    public void setCorCabeloId(Long corCabeloId) {
        this.corCabeloId = corCabeloId;
    }

    public Long getPenteadoId() {
        return penteadoId;
    }

    public void setPenteadoId(Long penteadoId) {
        this.penteadoId = penteadoId;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}