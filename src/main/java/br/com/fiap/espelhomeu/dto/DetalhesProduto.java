package br.com.fiap.espelhomeu.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DetalhesProduto {

    private Long id;
    private String categoria;
    private String nome;
    private String marca;
    private String categoriaPrincipal;
    private BigDecimal precoReferencia;
    private String faixaPreco;
    private Double avaliacao;
    private String cor;
    private LocalDateTime data;

    //construtores

    public DetalhesProduto() {}

    public DetalhesProduto(Long id, String categoria, String nome, String marca, String categoriaPrincipal, BigDecimal precoReferencia, String faixaPreco, Double avaliacao, String cor, LocalDateTime data) {
        this.id = id;
        this.categoria = categoria;
        this.nome = nome;
        this.marca = marca;
        this.categoriaPrincipal = categoriaPrincipal;
        this.precoReferencia = precoReferencia;
        this.faixaPreco = faixaPreco;
        this.avaliacao = avaliacao;
        this.cor = cor;
        this.data = data;
    }

    //getters and setters

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

    public BigDecimal getPrecoReferencia() {
        return precoReferencia;
    }

    public void setPrecoReferencia(BigDecimal precoReferencia) {
        this.precoReferencia = precoReferencia;
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