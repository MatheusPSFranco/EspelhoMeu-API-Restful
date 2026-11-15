package br.com.fiap.espelhomeu.dto;

import java.time.LocalDateTime;

public class DetalhesFavoritos {

    private Long id;
    private Long usuarioId;
    private Long corteId;
    private Long maquiagemId;
    private Long corCabeloId;
    private Long penteadoId;
    private Long recomendacaoCorteId;
    private Long recomendacaoCorId;
    private Long recomendacaoPenteadoId;
    private Long produtoId;
    private Long recomendacaoMaquiagemId;
    private Long simulacaoId;
    private LocalDateTime data;

    //construtores

    public DetalhesFavoritos() {}

    public DetalhesFavoritos(Long id, Long usuarioId, Long corteId, Long maquiagemId,
                             Long corCabeloId, Long penteadoId, Long recomendacaoCorteId,
                             Long recomendacaoCorId, Long recomendacaoPenteadoId, Long produtoId,
                             Long recomendacaoMaquiagemId, Long simulacaoId, LocalDateTime data) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.corteId = corteId;
        this.maquiagemId = maquiagemId;
        this.corCabeloId = corCabeloId;
        this.penteadoId = penteadoId;
        this.recomendacaoCorteId = recomendacaoCorteId;
        this.recomendacaoCorId = recomendacaoCorId;
        this.recomendacaoPenteadoId = recomendacaoPenteadoId;
        this.produtoId = produtoId;
        this.recomendacaoMaquiagemId = recomendacaoMaquiagemId;
        this.simulacaoId = simulacaoId;
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

    public Long getMaquiagemId() {
        return maquiagemId;
    }

    public void setMaquiagemId(Long maquiagemId) {
        this.maquiagemId = maquiagemId;
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

    public Long getRecomendacaoCorteId() {
        return recomendacaoCorteId;
    }

    public void setRecomendacaoCorteId(Long recomendacaoCorteId) {
        this.recomendacaoCorteId = recomendacaoCorteId;
    }

    public Long getRecomendacaoCorId() {
        return recomendacaoCorId;
    }

    public void setRecomendacaoCorId(Long recomendacaoCorId) {
        this.recomendacaoCorId = recomendacaoCorId;
    }

    public Long getRecomendacaoPenteadoId() {
        return recomendacaoPenteadoId;
    }

    public void setRecomendacaoPenteadoId(Long recomendacaoPenteadoId) {
        this.recomendacaoPenteadoId = recomendacaoPenteadoId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Long getRecomendacaoMaquiagemId() {
        return recomendacaoMaquiagemId;
    }

    public void setRecomendacaoMaquiagemId(Long recomendacaoMaquiagemId) {
        this.recomendacaoMaquiagemId = recomendacaoMaquiagemId;
    }

    public Long getSimulacaoId() {
        return simulacaoId;
    }

    public void setSimulacaoId(Long simulacaoId) {
        this.simulacaoId = simulacaoId;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}