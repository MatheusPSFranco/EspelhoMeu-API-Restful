package br.com.fiap.espelhomeu.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class CadastroAvaliacao {

    @NotNull
    @Positive
    private Long usuarioId;

    @Positive
    private Long recomendacaoCorId;

    @Positive
    private Long recomendacaoCorteId;

    @Positive
    private Long recomendacaoPenteadoId;

    @Positive
    private Long recomendacaoMaquiagemId;

    @NotNull
    @Digits(integer = 1, fraction = 2)
    private Double nota;

    @Size(max = 100)
    private String mensagem;

    @NotNull
    private LocalDateTime data;

    @JsonIgnore
    @AssertTrue(message = "Informe exatamente uma recomendação.")
    public boolean isApenasUmaRecomendacaoInformada() {
        int quantidade = 0;

        if (recomendacaoCorId != null) quantidade++;
        if (recomendacaoCorteId != null) quantidade++;
        if (recomendacaoPenteadoId != null) quantidade++;
        if (recomendacaoMaquiagemId != null) quantidade++;

        return quantidade == 1;
    }

    //construtores

    public CadastroAvaliacao() {}

    public CadastroAvaliacao(Long usuarioId, Long recomendacaoCorId, Long recomendacaoCorteId,
                             Long recomendacaoPenteadoId, Long recomendacaoMaquiagemId,
                             Double nota, String mensagem, LocalDateTime data) {
        this.usuarioId = usuarioId;
        this.recomendacaoCorId = recomendacaoCorId;
        this.recomendacaoCorteId = recomendacaoCorteId;
        this.recomendacaoPenteadoId = recomendacaoPenteadoId;
        this.recomendacaoMaquiagemId = recomendacaoMaquiagemId;
        this.nota = nota;
        this.mensagem = mensagem;
        this.data = data;
    }

    //getters and setters

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getRecomendacaoCorId() {
        return recomendacaoCorId;
    }

    public void setRecomendacaoCorId(Long recomendacaoCorId) {
        this.recomendacaoCorId = recomendacaoCorId;
    }

    public Long getRecomendacaoCorteId() {
        return recomendacaoCorteId;
    }

    public void setRecomendacaoCorteId(Long recomendacaoCorteId) {
        this.recomendacaoCorteId = recomendacaoCorteId;
    }

    public Long getRecomendacaoPenteadoId() {
        return recomendacaoPenteadoId;
    }

    public void setRecomendacaoPenteadoId(Long recomendacaoPenteadoId) {
        this.recomendacaoPenteadoId = recomendacaoPenteadoId;
    }

    public Long getRecomendacaoMaquiagemId() {
        return recomendacaoMaquiagemId;
    }

    public void setRecomendacaoMaquiagemId(Long recomendacaoMaquiagemId) {
        this.recomendacaoMaquiagemId = recomendacaoMaquiagemId;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}