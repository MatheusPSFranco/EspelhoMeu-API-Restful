package br.com.fiap.espelhomeu.dto;

public class DetalhesRecomendacaoPenteadoProduto {

    private Long produtoId;
    private Long recomendacaoPenteadoId;
    private String justificativa;

    //construtores

    public DetalhesRecomendacaoPenteadoProduto() {}

    public DetalhesRecomendacaoPenteadoProduto(Long produtoId, Long recomendacaoPenteadoId, String justificativa) {
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