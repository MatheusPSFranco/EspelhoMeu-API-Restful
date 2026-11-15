package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.Size;

public class AtualizacaoRecomendacaoCorProduto {

    @Size(max = 150)
    private String justificativa;

    //construtores

    public AtualizacaoRecomendacaoCorProduto() {}

    public AtualizacaoRecomendacaoCorProduto(String justificativa) {
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