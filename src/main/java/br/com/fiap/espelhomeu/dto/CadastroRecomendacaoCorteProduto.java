package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CadastroRecomendacaoCorteProduto {

    @NotNull
    @Positive
    private Long produtoId;

    @NotNull
    @Positive
    private Long recomendacaoCorteId;

    @Size(max = 150)
    private String justificativa;

    //construtores

    public CadastroRecomendacaoCorteProduto() {}

    public CadastroRecomendacaoCorteProduto(Long produtoId, Long recomendacaoCorteId, String justificativa) {
        this.produtoId = produtoId;
        this.recomendacaoCorteId = recomendacaoCorteId;
        this.justificativa = justificativa;
    }

    //getters and setters

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Long getRecomendacaoCorteId() {
        return recomendacaoCorteId;
    }

    public void setRecomendacaoCorteId(Long recomendacaoCorteId) {
        this.recomendacaoCorteId = recomendacaoCorteId;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }
}