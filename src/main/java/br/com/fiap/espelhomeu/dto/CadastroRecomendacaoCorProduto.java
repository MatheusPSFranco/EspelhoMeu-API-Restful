package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CadastroRecomendacaoCorProduto {

    @NotNull
    @Positive
    private Long recomendacaoCorId;

    @NotNull
    @Positive
    private Long produtoId;

    @Size(max = 150)
    private String justificativa;

    //construtores

    public CadastroRecomendacaoCorProduto() {}

    public CadastroRecomendacaoCorProduto(Long recomendacaoCorId, Long produtoId, String justificativa) {
        this.recomendacaoCorId = recomendacaoCorId;
        this.produtoId = produtoId;
        this.justificativa = justificativa;
    }

    //getters and setters

    public Long getRecomendacaoCorId() {
        return recomendacaoCorId;
    }

    public void setRecomendacaoCorId(Long recomendacaoCorId) {
        this.recomendacaoCorId = recomendacaoCorId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }
}