package br.com.fiap.espelhomeu.model;

public class RecomendacaoCorProduto {

    private RecomendacaoCor recomendacaoCor;
    private Produto produto;
    private String justificativa;

    //Constructor

    public RecomendacaoCorProduto() {}

    public RecomendacaoCorProduto(RecomendacaoCor recomendacaoCor, Produto produto, String justificativa) {
        this.recomendacaoCor = recomendacaoCor;
        this.produto = produto;
        this.justificativa = justificativa;
    }

    //Getters and Setters

    public RecomendacaoCor getRecomendacaoCor() {
        return recomendacaoCor;
    }

    public void setRecomendacaoCor(RecomendacaoCor recomendacaoCor) {
        this.recomendacaoCor = recomendacaoCor;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
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
        return "Recomendacao Cor: "+recomendacaoCor.getId()+", Produto: "+produto.getId()+", Justificativa: "+justificativa;
    }
}
