package br.com.fiap.espelhomeu.dto;

import java.time.LocalDateTime;

public class DetalhesAvaliacao {

    private Long id;
    private Long usuarioId;
    private Long recomendacaoCorId;
    private Long recomendacaoCorteId;
    private Long recomendacaoPenteadoId;
    private Long recomendacaoMaquiagemId;
    private Double nota;
    private String mensagem;
    private LocalDateTime data;

    //construtores

    public DetalhesAvaliacao() {}

    public DetalhesAvaliacao(Long id, Long usuarioId, Long recomendacaoCorId,
                             Long recomendacaoCorteId, Long recomendacaoPenteadoId,
                             Long recomendacaoMaquiagemId, Double nota, String mensagem,
                             LocalDateTime data) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.recomendacaoCorId = recomendacaoCorId;
        this.recomendacaoCorteId = recomendacaoCorteId;
        this.recomendacaoPenteadoId = recomendacaoPenteadoId;
        this.recomendacaoMaquiagemId = recomendacaoMaquiagemId;
        this.nota = nota;
        this.mensagem = mensagem;
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

    public Long getRecomendacaoCorId() {
        return recomendacaoCorId;
    }

    public void setRecomendacaoCorId(Long recomendacaoCorId) {
        this.recomendacaoCorId = recomendacaoCorId;
    }

    public Long getRecomendacaoCorteId() {
        return recomendacaoCorteId;
    }

    public void setRecomendacaoCorteId(Long recomendacaoCorteId) {
        this.recomendacaoCorteId = recomendacaoCorteId;
    }

    public Long getRecomendacaoPenteadoId() {
        return recomendacaoPenteadoId;
    }

    public void setRecomendacaoPenteadoId(Long recomendacaoPenteadoId) {
        this.recomendacaoPenteadoId = recomendacaoPenteadoId;
    }

    public Long getRecomendacaoMaquiagemId() {
        return recomendacaoMaquiagemId;
    }

    public void setRecomendacaoMaquiagemId(Long recomendacaoMaquiagemId) {
        this.recomendacaoMaquiagemId = recomendacaoMaquiagemId;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}