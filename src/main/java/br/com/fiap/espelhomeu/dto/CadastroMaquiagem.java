package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class CadastroMaquiagem {

    @NotBlank
    @Pattern(regexp = "^(Base|Corretivo|Pó Compacto|Pó Solto)$")
    private String tipo;

    @NotBlank
    @Size(max = 30)
    private String cor;

    @NotNull
    @DecimalMin("0.00")
    @Digits(integer = 8, fraction = 2)
    private BigDecimal precoReferencia;

    @NotBlank
    @Size(max = 40)
    private String marca;

    @NotBlank
    @Size(max = 50)
    private String linhaProduto;

    @NotBlank
    @Size(max = 20)
    private String codigoTonalidade;

    @NotBlank
    @Size(max = 50)
    private String tipoCobertura;

    @NotBlank
    @Size(max = 50)
    private String acabamento;

    @NotNull
    @Digits(integer = 1, fraction = 2)
    private Double avaliacao;

    //construtores

    public CadastroMaquiagem() {}

    public CadastroMaquiagem(String tipo, String cor, BigDecimal precoReferencia, String marca, String linhaProduto, String codigoTonalidade, String tipoCobertura, String acabamento, Double avaliacao) {
        this.tipo = tipo;
        this.cor = cor;
        this.precoReferencia = precoReferencia;
        this.marca = marca;
        this.linhaProduto = linhaProduto;
        this.codigoTonalidade = codigoTonalidade;
        this.tipoCobertura = tipoCobertura;
        this.acabamento = acabamento;
        this.avaliacao = avaliacao;
    }

    //getters and setters

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