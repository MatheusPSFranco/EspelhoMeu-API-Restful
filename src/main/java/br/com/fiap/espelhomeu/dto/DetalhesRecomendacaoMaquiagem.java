package br.com.fiap.espelhomeu.dto;

import java.time.LocalDateTime;

public class DetalhesRecomendacaoMaquiagem {

    private Long id;
    private Long consultoriaMaquiagemId;
    private Long maquiagemId;
    private String justificativa;
    private LocalDateTime data;

    //construtores

    public DetalhesRecomendacaoMaquiagem() {}

    public DetalhesRecomendacaoMaquiagem(Long id, Long consultoriaMaquiagemId, Long maquiagemId, String justificativa, LocalDateTime data) {
        this.id = id;
        this.consultoriaMaquiagemId = consultoriaMaquiagemId;
        this.maquiagemId = maquiagemId;
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