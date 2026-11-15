package br.com.fiap.espelhomeu.dto;

import java.time.LocalDateTime;

public class DetalhesRecomendacaoPenteado {

    private Long id;
    private Long consultoriaCapilarId;
    private Long penteadoId;
    private String justificativa;
    private LocalDateTime data;

    //construtores

    public DetalhesRecomendacaoPenteado() {}

    public DetalhesRecomendacaoPenteado(Long id, Long consultoriaCapilarId, Long penteadoId, String justificativa, LocalDateTime data) {
        this.id = id;
        this.consultoriaCapilarId = consultoriaCapilarId;
        this.penteadoId = penteadoId;
        this.justificativa = justificativa;
        this.data = data;
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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