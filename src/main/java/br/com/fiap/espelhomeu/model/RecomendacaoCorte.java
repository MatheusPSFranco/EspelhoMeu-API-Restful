package br.com.fiap.espelhomeu.model;

import java.time.LocalDateTime;

public class RecomendacaoCorte {

    private Long id;
    private ConsultoriaCapilar consultoriaCapilar;
    private Corte corte;
    private String justificativa;
    private LocalDateTime data;

    //Constructor

    public RecomendacaoCorte() {}

    public RecomendacaoCorte(Long id, ConsultoriaCapilar consultoriaCapilar, Corte corte, String justificativa, LocalDateTime data) {
        this.id = id;
        this.consultoriaCapilar = consultoriaCapilar;
        this.corte = corte;
        this.justificativa = justificativa;
        this.data = data;
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConsultoriaCapilar getConsultoriaCapilar() {
        return consultoriaCapilar;
    }

    public void setConsultoriaCapilar(ConsultoriaCapilar consultoriaCapilar) {
        this.consultoriaCapilar = consultoriaCapilar;
    }

    public Corte getCorte() {
        return corte;
    }

    public void setCorte(Corte corte) {
        this.corte = corte;
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

    //toString
    @Override
    public String toString(){
        return "Id: "+ id +", Consultoria Capilar: "+consultoriaCapilar.getId()+", Corte: "+corte.getId()+", Justificativa: "+justificativa+", Data: "+data;
    }
}
