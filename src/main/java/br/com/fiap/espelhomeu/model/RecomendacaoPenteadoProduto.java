package br.com.fiap.espelhomeu.model;

public class RecomendacaoPenteadoProduto {

    private Produto produto;
    private RecomendacaoPenteado recomendacaoPenteado;
    private String justificativa;

    //Constructors

    public RecomendacaoPenteadoProduto() {}

    public RecomendacaoPenteadoProduto(Produto produto, RecomendacaoPenteado recomendacaoPenteado, String justificativa) {
        this.produto = produto;
        this.recomendacaoPenteado = recomendacaoPenteado;
        this.justificativa = justificativa;
    }

    //Getters and Setters

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public RecomendacaoPenteado getRecomendacaoPenteado() {
        return recomendacaoPenteado;
    }

    public void setRecomendacaoPenteado(RecomendacaoPenteado recomendacaoPenteado) {
        this.recomendacaoPenteado = recomendacaoPenteado;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    //toString
    @Override
    public String toString(){
        return "Produto: "+produto.getId()+", Recomendacao Penteado: "+recomendacaoPenteado.getId()+", Justificativa: "+justificativa;
    }
}
