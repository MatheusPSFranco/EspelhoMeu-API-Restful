package br.com.fiap.espelhomeu.model;

import java.time.LocalDateTime;

public class RecomendacaoCor {

    private Long id;
    private ConsultoriaCapilar consultoriaCapilar;
    private CorCabelo corCabelo;
    private String justificativa;
    private LocalDateTime data;

    //Constructor

    public RecomendacaoCor() {}

    public RecomendacaoCor(Long id, ConsultoriaCapilar consultoriaCapilar, CorCabelo corCabelo, String justificativa, LocalDateTime data) {
        this.id = id;
        this.consultoriaCapilar = consultoriaCapilar;
        this.corCabelo = corCabelo;
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

    public CorCabelo getCorCabelo() {
        return corCabelo;
    }

    public void setCorCabelo(CorCabelo corCabelo) {
        this.corCabelo = corCabelo;
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
        return "Id: "+ id +", Consultoria Capilar: "+consultoriaCapilar.getId()+", Cor de Cabelo: "+corCabelo.getId()+", Justificativa: "+justificativa+", Data: "+data;
    }
}
