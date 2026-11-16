package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CadastroProduto {

    @NotBlank
    @Pattern(regexp = "^(Shampoo|Condicionador|Máscara Capilar|Finalizador|Modelador|Tintura)$")
    private String categoria;

    @NotBlank
    @Size(max = 20)
    private String nome;

    @NotBlank
    @Size(max = 30)
    private String marca;

    @NotBlank
    @Size(max = 30)
    private String categoriaPrincipal;

    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal precoReferencia;

    @NotNull
    @Digits(integer = 1, fraction = 2)
    private Double avaliacao;

    @Size(max = 30)
    private String cor;

    private LocalDateTime data;

    //construtores

    public CadastroProduto() {}

    public CadastroProduto(String categoria, String nome, String marca, String categoriaPrincipal, BigDecimal precoReferencia, Double avaliacao, String cor, LocalDateTime data) {
        this.categoria = categoria;
        this.nome = nome;
        this.marca = marca;
        this.categoriaPrincipal = categoriaPrincipal;
        this.precoReferencia = precoReferencia;
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