package br.com.fiap.espelhomeu.model;

import java.time.LocalDateTime;

public class RecomendacaoPenteado {

    private Long id;
    private ConsultoriaCapilar consultoriaCapilar;
    private Penteado penteado;
    private String justificativa;
    private LocalDateTime data;

    //Constructors

    public RecomendacaoPenteado() {}

    public RecomendacaoPenteado(Long id, ConsultoriaCapilar consultoriaCapilar, Penteado penteado, String justificativa, LocalDateTime data) {
        this.id = id;
        this.consultoriaCapilar = consultoriaCapilar;
        this.penteado = penteado;
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

    public Penteado getPenteado() {
        return penteado;
    }

    public void setPenteado(Penteado penteado) {
        this.penteado = penteado;
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
        return "Id: "+ id +", Consultoria Capilar: "+consultoriaCapilar.getId()+", Penteado: "+penteado.getId()+", Justificativa: "+justificativa+", Data: "+data;
    }
}
