package br.com.fiap.espelhomeu.dto;

public class DetalhesRecomendacaoCorteProduto {

    private Long produtoId;
    private Long recomendacaoCorteId;
    private String justificativa;

    //construtores

    public DetalhesRecomendacaoCorteProduto() {}

    public DetalhesRecomendacaoCorteProduto(Long produtoId, Long recomendacaoCorteId, String justificativa) {
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