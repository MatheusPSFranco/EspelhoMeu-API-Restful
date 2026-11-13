package br.com.fiap.espelhomeu.model;

public class Produto {

    private Long id;
    private String tipo;
    private String faixaPreco;
    private String marca;
    private Double avaliacao;
    private String cor;

    //Constructor

    public Produto() {}

    public Produto(Long id, String tipo, String faixaPreco, String marca, Double avaliacao, String cor) {
        this.id = id;
        this.tipo = tipo;
        this.faixaPreco = faixaPreco;
        this.marca = marca;
        this.avaliacao = avaliacao;
        this.cor = cor;
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFaixaPreco() {
        return faixaPreco;
    }

    public void setFaixaPreco(String faixaPreco) {
        this.faixaPreco = faixaPreco;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
