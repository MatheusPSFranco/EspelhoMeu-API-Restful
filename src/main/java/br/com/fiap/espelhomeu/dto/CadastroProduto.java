package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CadastroProduto {

    @Size(max = 20)
    private String categoria;

    @Size(max = 20)
    private String nome;

    @Size(max = 30)
    private String marca;

    @Size(max = 30)
    private String categoriaPrincipal;

    @DecimalMin("0.00")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal precoReferencia;

    @Pattern(regexp = "^(Custo-beneficio|Intermediário|Caro)$")
    private String faixaPreco;

    @Digits(integer = 1, fraction = 2)
    private Double avaliacao;

    @Size(max = 30)
    private String cor;

    private LocalDateTime data;

    //construtores

    public CadastroProduto() {}

    public CadastroProduto(String categoria, String nome, String marca, String categoriaPrincipal, BigDecimal precoReferencia, String faixaPreco, Double avaliacao, String cor, LocalDateTime data) {
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