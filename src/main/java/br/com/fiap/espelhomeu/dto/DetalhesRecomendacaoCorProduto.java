package br.com.fiap.espelhomeu.dto;

public class DetalhesRecomendacaoCorProduto {

    private Long recomendacaoCorId;
    private Long produtoId;
    private String justificativa;

    //construtores

    public DetalhesRecomendacaoCorProduto() {}

    public DetalhesRecomendacaoCorProduto(Long recomendacaoCorId, Long produtoId, String justificativa) {
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