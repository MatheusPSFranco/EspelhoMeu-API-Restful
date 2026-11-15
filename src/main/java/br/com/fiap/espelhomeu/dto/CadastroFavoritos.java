package br.com.fiap.espelhomeu.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class CadastroFavoritos {

    @NotNull
    @Positive
    private Long usuarioId;

    @Positive
    private Long corteId;

    @Positive
    private Long maquiagemId;

    @Positive
    private Long corCabeloId;

    @Positive
    private Long penteadoId;

    @Positive
    private Long recomendacaoCorteId;

    @Positive
    private Long recomendacaoCorId;

    @Positive
    private Long recomendacaoPenteadoId;

    @Positive
    private Long produtoId;

    @Positive
    private Long recomendacaoMaquiagemId;

    @Positive
    private Long simulacaoId;

    private LocalDateTime data;

    @JsonIgnore
    @AssertTrue(message = "Informe exatamente um item como favorito.")
    public boolean isApenasUmItemInformado() {
        int quantidade = 0;

        if (corteId != null) quantidade++;
        if (maquiagemId != null) quantidade++;
        if (corCabeloId != null) quantidade++;
        if (penteadoId != null) quantidade++;
        if (recomendacaoCorteId != null) quantidade++;
        if (recomendacaoCorId != null) quantidade++;
        if (recomendacaoPenteadoId != null) quantidade++;
        if (produtoId != null) quantidade++;
        if (recomendacaoMaquiagemId != null) quantidade++;
        if (simulacaoId != null) quantidade++;

        return quantidade == 1;
    }

    //construtores

    public CadastroFavoritos() {}

    public CadastroFavoritos(Long usuarioId, Long corteId, Long maquiagemId, Long corCabeloId,
                             Long penteadoId, Long recomendacaoCorteId, Long recomendacaoCorId,
                             Long recomendacaoPenteadoId, Long produtoId, Long recomendacaoMaquiagemId,
                             Long simulacaoId, LocalDateTime data) {
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