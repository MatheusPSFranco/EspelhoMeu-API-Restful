package br.com.fiap.espelhomeu.model;

import java.time.LocalDate;

public class Usuario {

    private Long id;
    private String nome;
    private String email;
    private LocalDate data;
    private String telefone;
    private String status;

    //Constructor
    public Usuario() {}

    public Usuario(Long id, String nome, String email, LocalDate data, String telefone, String status) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.data = data;
        this.telefone = telefone;
        this.status = status;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //toString
    @Override
    public String toString(){
        return "Id: "+ id +", Nome: "+ nome+", Email: "+ email+", Data: "+ data+", Telefone: "+ telefone+", Status: "+ status;
    }
}
