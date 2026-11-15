package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.Size;

public class AtualizacaoRecomendacaoPenteadoProduto {

    @Size(max = 150)
    private String justificativa;

    //construtores

    public AtualizacaoRecomendacaoPenteadoProduto() {}

    public AtualizacaoRecomendacaoPenteadoProduto(String justificativa) {
        this.justificativa = justificativa;
    }

    //getters and setters

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }
}