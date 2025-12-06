package com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model;

public enum Bandeira {
    VISA("Visa", "4"),
    MASTERCARD("Mastercard", "5"),
    AMEX("American Express", "34,37"),
    ELO("Elo", "5041,5066,6362,6363");
    
    private final String nome;
    private final String prefixos;
    
    Bandeira(String nome, String prefixos) {
        this.nome = nome;
        this.prefixos = prefixos;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getPrefixos() {
        return prefixos;
    }
    
    public int getTamanhoNumero() {
        if (this == AMEX) return 15;
        return 16;
    }
}