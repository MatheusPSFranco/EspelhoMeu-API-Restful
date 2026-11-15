package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class CadastroRecomendacaoMaquiagem {

    @NotNull
    @Positive
    private Long consultoriaMaquiagemId;

    @NotNull
    @Positive
    private Long maquiagemId;

    @Size(max = 150)
    private String justificativa;

    private LocalDateTime data;

    //construtores

    public CadastroRecomendacaoMaquiagem() {}

    public CadastroRecomendacaoMaquiagem(Long consultoriaMaquiagemId, Long maquiagemId, String justificativa, LocalDateTime data) {
        this.consultoriaMaquiagemId = consultoriaMaquiagemId;
        this.maquiagemId = maquiagemId;
        this.justificativa = justificativa;
        this.data = data;
    }

    //getters and setters

    public Long getConsultoriaMaquiagemId() {
        return consultoriaMaquiagemId;
    }

    public void setConsultoriaMaquiagemId(Long consultoriaMaquiagemId) {
        this.consultoriaMaquiagemId = consultoriaMaquiagemId;
    }

    public Long getMaquiagemId() {
        return maquiagemId;
    }

    public void setMaquiagemId(Long maquiagemId) {
        this.maquiagemId = maquiagemId;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}