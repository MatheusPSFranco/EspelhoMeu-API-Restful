package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.Size;

public class AtualizacaoRecomendacaoCorteProduto {

    @Size(max = 150)
    private String justificativa;

    //construtores

    public AtualizacaoRecomendacaoCorteProduto() {}

    public AtualizacaoRecomendacaoCorteProduto(String justificativa) {
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