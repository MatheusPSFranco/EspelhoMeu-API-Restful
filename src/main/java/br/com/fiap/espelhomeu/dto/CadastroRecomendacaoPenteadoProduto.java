package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class CadastroRecomendacaoPenteadoProduto {

    @NotNull
    @Positive
    private Long produtoId;

    @NotNull
    @Positive
    private Long recomendacaoPenteadoId;

    @Size(max = 150)
    private String justificativa;

    //construtores

    public CadastroRecomendacaoPenteadoProduto() {}

    public CadastroRecomendacaoPenteadoProduto(Long produtoId, Long recomendacaoPenteadoId, String justificativa) {
        this.produtoId = produtoId;
        this.recomendacaoPenteadoId = recomendacaoPenteadoId;
        this.justificativa = justificativa;
    }

    //getters and setters

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Long getRecomendacaoPenteadoId() {
        return recomendacaoPenteadoId;
    }

    public void setRecomendacaoPenteadoId(Long recomendacaoPenteadoId) {
        this.recomendacaoPenteadoId = recomendacaoPenteadoId;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }
}