package br.com.fiap.espelhomeu.model;

public class RecomendacaoCorteProduto {

    private Produto produto;
    private RecomendacaoCorte recomendacaoCorte;
    private String Justificativa;

    //Constructor

    public RecomendacaoCorteProduto() {}

    public RecomendacaoCorteProduto(Produto produto, RecomendacaoCorte recomendacaoCorte, String justificativa) {
        this.produto = produto;
        this.recomendacaoCorte = recomendacaoCorte;
        Justificativa = justificativa;
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
        return Justificativa;
    }

    public void setJustificativa(String justificativa) {
        Justificativa = justificativa;
    }
}
