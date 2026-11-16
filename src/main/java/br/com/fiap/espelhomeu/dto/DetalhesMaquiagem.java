package br.com.fiap.espelhomeu.dto;

import java.math.BigDecimal;

public class DetalhesMaquiagem {

    private Long id;
    private String tipo;
    private String cor;
    private BigDecimal precoReferencia;
    private String faixaPreco;
    private String marca;
    private String linhaProduto;
    private String codigoTonalidade;
    private String tipoCobertura;
    private String acabamento;
    private Double avaliacao;

    //construtores

    public DetalhesMaquiagem() {}

    public DetalhesMaquiagem(Long id, String tipo, String cor, BigDecimal precoReferencia, String faixaPreco, String marca, String linhaProduto, String codigoTonalidade, String tipoCobertura, String acabamento, Double avaliacao) {
        this.id = id;
        this.tipo = tipo;
        this.cor = cor;
        this.precoReferencia = precoReferencia;
        this.faixaPreco = faixaPreco;
        this.marca = marca;
        this.linhaProduto = linhaProduto;
        this.codigoTonalidade = codigoTonalidade;
        this.tipoCobertura = tipoCobertura;
        this.acabamento = acabamento;
        this.avaliacao = avaliacao;
    }

    //getters and setters

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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLinhaProduto() {
        return linhaProduto;
    }

    public void setLinhaProduto(String linhaProduto) {
        this.linhaProduto = linhaProduto;
    }

    public String getCodigoTonalidade() {
        return codigoTonalidade;
    }

    public void setCodigoTonalidade(String codigoTonalidade) {
        this.codigoTonalidade = codigoTonalidade;
    }

    public String getTipoCobertura() {
        return tipoCobertura;
    }

    public void setTipoCobertura(String tipoCobertura) {
        this.tipoCobertura = tipoCobertura;
    }

    public String getAcabamento() {
        return acabamento;
    }

    public void setAcabamento(String acabamento) {
        this.acabamento = acabamento;
    }

    public Double getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }
}