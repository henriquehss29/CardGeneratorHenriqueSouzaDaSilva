package com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model;

public class CartaoCredito {
    private final String numero;
    private final Bandeira bandeira;
    private final Titular titular;
    private final String cvv;
    private final String dataValidade;
    
    public CartaoCredito(String numero, Bandeira bandeira, Titular titular, 
                        String cvv, String dataValidade) {
        this.numero = numero;
        this.bandeira = bandeira;
        this.titular = titular;
        this.cvv = cvv;
        this.dataValidade = dataValidade;
    }
    
    public String getNumero() { 
        return numero; 
    }
    
    public Bandeira getBandeira() { 
        return bandeira; 
    }
    
    public Titular getTitular() { 
        return titular; 
    }
    
    public String getCvv() { 
        return cvv; 
    }
    
    public String getDataValidade() { 
        return dataValidade; 
    }
    
    public String getNumeroFormatado() {
        return numero.replaceAll("(.{4})", "$1 ").trim();
    }
}