package br.com.fiap.espelhomeu.dto;

import java.time.LocalDateTime;

public class DetalhesSimulacao {

    private Long id;
    private Long usuarioId;
    private Long corteId;
    private Long corCabeloId;
    private Long penteadoId;
    private byte[] imagem;
    private LocalDateTime data;

    //construtores

    public DetalhesSimulacao() {}

    public DetalhesSimulacao(Long id, Long usuarioId, Long corteId, Long corCabeloId, Long penteadoId, byte[] imagem, LocalDateTime data) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.corteId = corteId;
        this.corCabeloId = corCabeloId;
        this.penteadoId = penteadoId;
        this.imagem = imagem;
        this.data = data;
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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