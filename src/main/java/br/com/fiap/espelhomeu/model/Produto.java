package br.com.fiap.espelhomeu.model;

import java.time.LocalDateTime;

public class Produto {

    private Long id;
    private String categoria;
    private String nome;
    private String marca;
    private String categoriaPrincipal;
    private String faixaPreco;
    private Double avaliacao;
    private String cor;
    private LocalDateTime data;

    //Constructor

    public Produto() {}

    public Produto(Long id, String categoria, String nome, String marca, String categoriaPrincipal, String faixaPreco, Double avaliacao, String cor, LocalDateTime data) {
        this.id = id;
        this.categoria = categoria;
        this.nome = nome;
        this.marca = marca;
        this.categoriaPrincipal = categoriaPrincipal;
        this.faixaPreco = faixaPreco;
        this.avaliacao = avaliacao;
        this.cor = cor;
        this.data = data;
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoriaPrincipal() {
        return categoriaPrincipal;
    }

    public void setCategoriaPrincipal(String categoriaPrincipal) {
        this.categoriaPrincipal = categoriaPrincipal;
    }

    public String getFaixaPreco() {
        return faixaPreco;
    }

    public void setFaixaPreco(String faixaPreco) {
        this.faixaPreco = faixaPreco;
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

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}
