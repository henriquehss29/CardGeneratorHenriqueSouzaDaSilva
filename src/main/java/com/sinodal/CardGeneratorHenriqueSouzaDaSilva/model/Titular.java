package com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model;

public class Titular {
    private Long id;
    private String nome;
    
    public Titular(String nome) {
        this.nome = nome;
    }
    
    public Titular(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
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
    
    @Override
    public String toString() {
        return "Titular: " + nome;
    }
}
