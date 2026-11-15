package br.com.fiap.espelhomeu.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class CadastroRecomendacaoPenteado {

    @NotNull
    @Positive
    private Long consultoriaCapilarId;

    @NotNull
    @Positive
    private Long penteadoId;

    @Size(max = 150)
    private String justificativa;

    private LocalDateTime data;

    //construtores

    public CadastroRecomendacaoPenteado() {}

    public CadastroRecomendacaoPenteado(Long consultoriaCapilarId, Long penteadoId, String justificativa, LocalDateTime data) {
        this.consultoriaCapilarId = consultoriaCapilarId;
        this.penteadoId = penteadoId;
        this.justificativa = justificativa;
        this.data = data;
    }

    //getters and setters

    public Long getConsultoriaCapilarId() {
        return consultoriaCapilarId;
    }

    public void setConsultoriaCapilarId(Long consultoriaCapilarId) {
        this.consultoriaCapilarId = consultoriaCapilarId;
    }

    public Long getPenteadoId() {
        return penteadoId;
    }

    public void setPenteadoId(Long penteadoId) {
        this.penteadoId = penteadoId;
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