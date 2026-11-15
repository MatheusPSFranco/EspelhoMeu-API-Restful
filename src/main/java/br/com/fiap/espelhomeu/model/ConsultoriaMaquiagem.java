package br.com.fiap.espelhomeu.model;

import java.time.LocalDateTime;

public class ConsultoriaMaquiagem {

    private Long id;
    private PerfilMaquiagem perfilMaquiagem;
    private LocalDateTime data;

    //Constructor

    public ConsultoriaMaquiagem() {}

    public ConsultoriaMaquiagem(Long id, PerfilMaquiagem perfilMaquiagem, LocalDateTime data) {
        this.id = id;
        this.perfilMaquiagem = perfilMaquiagem;
        this.data = data;
    }

//Getters and Setters

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PerfilMaquiagem getPerfilMaquiagem() {
        return perfilMaquiagem;
    }

    public void setPerfilMaquiagem(PerfilMaquiagem perfilMaquiagem) {
        this.perfilMaquiagem = perfilMaquiagem;
    }

    //toString
    @Override
    public String toString(){
        return "Id: "+ id + ", Perfil Maquiagem: "+perfilMaquiagem.getId()+", Data: "+data;
    }
}
