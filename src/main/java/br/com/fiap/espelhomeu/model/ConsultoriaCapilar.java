package br.com.fiap.espelhomeu.model;

import java.time.LocalDateTime;

public class ConsultoriaCapilar {

    private Long id;
    private PerfilCabelo perfilCabelo;
    private LocalDateTime data;

    //Constructor

    public ConsultoriaCapilar() {}

    public ConsultoriaCapilar(Long id, PerfilCabelo perfilCabelo, LocalDateTime data) {
        this.id = id;
        this.perfilCabelo = perfilCabelo;
        this.data = data;
    }

    //Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PerfilCabelo getPerfilCabelo() {
        return perfilCabelo;
    }

    public void setPerfilCabelo(PerfilCabelo perfilCabelo) {
        this.perfilCabelo = perfilCabelo;
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
        return "Id: "+ id + ", Perfil Cabelo: "+perfilCabelo.getId()+", Data: "+data;
    }
}
