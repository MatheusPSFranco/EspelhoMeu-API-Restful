package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CadastroPerfilCabelo {

    @NotNull(message = "O ID do usuário é obrigatório")
    private Long usuarioId;

    @NotBlank(message = "A textura do cabelo é obrigatória")
    @Size(max = 30, message = "A textura deve ter no máximo 30 caracteres")
    private String texturaCabelo;

    @NotBlank(message = "O formato do rosto é obrigatório")
    @Size(max = 30, message = "O formato do rosto deve ter no máximo 30 caracteres")
    private String formatoRosto;

    @NotBlank(message = "O comprimento atual é obrigatório")
    @Size(max = 20, message = "O comprimento atual deve ter no máximo 20 caracteres")
    private String comprimentoAtual;

    @NotBlank(message = "O comprimento desejado é obrigatório")
    @Size(max = 20, message = "O comprimento desejado deve ter no máximo 20 caracteres")
    private String comprimentoDesejado;

    @NotBlank(message = "A cor atual é obrigatória")
    @Size(max = 30, message = "A cor atual deve ter no máximo 30 caracteres")
    private String corAtual;

    @NotBlank(message = "A preferência de estilo é obrigatória")
    @Size(max = 200, message = "A preferência deve ter no máximo 200 caracteres")
    private String estiloPreferencia;

    @NotBlank(message = "O tempo disponível é obrigatório")
    @Size(max = 30, message = "O tempo disponível deve ter no máximo 30 caracteres")
    private String tempoDisponivel;

    @NotBlank(message = "O interesse em coloração é obrigatório")
    @Size(max = 15, message = "O interesse em coloração deve ter no máximo 15 caracteres")
    private String interesseColoracao;

    @NotBlank(message = "A preferência de mudança é obrigatória")
    @Size(max = 10, message = "A preferência de mudança deve ter no máximo 10 caracteres")
    private String preferenciaMudanca;

    @NotBlank(message = "A faixa de preço é obrigatória")
    @Pattern(
            regexp = "^(Custo-benefício|Intermediário|Caro)$",
            message = "A faixa de preço deve ser Custo-benefício, Intermediário ou Caro"
    )
    private String faixaPreco;

    //Constructor

    public CadastroPerfilCabelo() {}

    //Getters and Setters

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTexturaCabelo() {
        return texturaCabelo;
    }

    public void setTexturaCabelo(String texturaCabelo) {
        this.texturaCabelo = texturaCabelo;
    }

    public String getFormatoRosto() {
        return formatoRosto;
    }

    public void setFormatoRosto(String formatoRosto) {
        this.formatoRosto = formatoRosto;
    }

    public String getComprimentoAtual() {
        return comprimentoAtual;
    }

    public void setComprimentoAtual(String comprimentoAtual) {
        this.comprimentoAtual = comprimentoAtual;
    }

    public String getComprimentoDesejado() {
        return comprimentoDesejado;
    }

    public void setComprimentoDesejado(String comprimentoDesejado) {
        this.comprimentoDesejado = comprimentoDesejado;
    }

    public String getCorAtual() {
        return corAtual;
    }

    public void setCorAtual(String corAtual) {
        this.corAtual = corAtual;
    }

    public String getEstiloPreferencia() {
        return estiloPreferencia;
    }

    public void setEstiloPreferencia(String estiloPreferencia) {
        this.estiloPreferencia = estiloPreferencia;
    }

    public String getTempoDisponivel() {
        return tempoDisponivel;
    }

    public void setTempoDisponivel(String tempoDisponivel) {
        this.tempoDisponivel = tempoDisponivel;
    }

    public String getInteresseColoracao() {
        return interesseColoracao;
    }

    public void setInteresseColoracao(String interesseColoracao) {
        this.interesseColoracao = interesseColoracao;
    }

    public String getPreferenciaMudanca() {
        return preferenciaMudanca;
    }

    public void setPreferenciaMudanca(String preferenciaMudanca) {
        this.preferenciaMudanca = preferenciaMudanca;
    }

    public String getFaixaPreco() {
        return faixaPreco;
    }

    public void setFaixaPreco(String faixaPreco) {
        this.faixaPreco = faixaPreco;
    }
}