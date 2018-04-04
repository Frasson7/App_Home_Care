package com.example.frasson.app_homecare.usuarios;


import java.io.Serializable;

public class Profissional implements Serializable{

    private Long id;
    private String nome;
    private String Telefone;
    private String Especialidade;

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

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getEspecialidade() {
        return Especialidade;
    }

    public void setEspecialidade(String especialidade) {
        Especialidade = especialidade;
    }

    @Override
    public String toString() {
        return getId() + " - " + getNome();
    }
}
