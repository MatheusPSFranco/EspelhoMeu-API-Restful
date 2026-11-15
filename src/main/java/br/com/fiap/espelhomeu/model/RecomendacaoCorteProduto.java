package br.com.fiap.espelhomeu.model;

public class RecomendacaoCorteProduto {

    private Produto produto;
    private RecomendacaoCorte recomendacaoCorte;
    private String justificativa;

    //Constructor

    public RecomendacaoCorteProduto() {}

    public RecomendacaoCorteProduto(Produto produto, RecomendacaoCorte recomendacaoCorte, String justificativa) {
        this.produto = produto;
        this.recomendacaoCorte = recomendacaoCorte;
        this.justificativa = justificativa;
    }

    //Getters and Setters

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public RecomendacaoCorte getRecomendacaoCorte() {
        return recomendacaoCorte;
    }

    public void setRecomendacaoCorte(RecomendacaoCorte recomendacaoCorte) {
        this.recomendacaoCorte = recomendacaoCorte;
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
        return "Produto: "+produto.getId()+", Recomendacao Corte: "+recomendacaoCorte.getId()+", Justificativa: "+justificativa;
    }
}
