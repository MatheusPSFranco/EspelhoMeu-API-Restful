package br.com.fiap.espelhomeu.model;

import java.time.LocalDateTime;

public class RecomendacaoMaquiagem {

    private Long Id;
    private ConsultoriaMaquiagem consultoriaMaquiagem;
    private Maquiagem maquiagem;
    private String justificativa;
    private LocalDateTime data;

    //Constructor

    public RecomendacaoMaquiagem() {}

    public RecomendacaoMaquiagem(Long id, ConsultoriaMaquiagem consultoriaMaquiagem, Maquiagem maquiagem, String justificativa, LocalDateTime data) {
        Id = id;
        this.consultoriaMaquiagem = consultoriaMaquiagem;
        this.maquiagem = maquiagem;
        this.justificativa = justificativa;
        this.data = data;
    }

    //Getters and Setters

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public ConsultoriaMaquiagem getConsultoriaMaquiagem() {
        return consultoriaMaquiagem;
    }

    public void setConsultoriaMaquiagem(ConsultoriaMaquiagem consultoriaMaquiagem) {
        this.consultoriaMaquiagem = consultoriaMaquiagem;
    }

    public Maquiagem getMaquiagem() {
        return maquiagem;
    }

    public void setMaquiagem(Maquiagem maquiagem) {
        this.maquiagem = maquiagem;
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
