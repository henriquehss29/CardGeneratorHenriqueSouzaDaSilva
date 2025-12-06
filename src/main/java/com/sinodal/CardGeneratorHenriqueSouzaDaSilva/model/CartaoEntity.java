package com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cartoes")
public class CartaoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String numero;
    
    @Column(nullable = false)
    private String bandeira;
    
    @Column(nullable = false)
    private String titular;
    
    @Column(nullable = false)
    private String cvv;
    
    @Column(nullable = false)
    private String dataValidade;
    
    public CartaoEntity() {}
    
    public CartaoEntity(Long id, String numero, String bandeira, String titular, String cvv, String dataValidade) {
        this.id = id;
        this.numero = numero;
        this.bandeira = bandeira;
        this.titular = titular;
        this.cvv = cvv;
        this.dataValidade = dataValidade;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    
    public String getBandeira() { return bandeira; }
    public void setBandeira(String bandeira) { this.bandeira = bandeira; }
    
    public String getTitular() { return titular; }
    public void setTitular(String titular) { this.titular = titular; }
    
    public String getCvv() { return cvv; }
    public void setCvv(String cvv) { this.cvv = cvv; }
    
    public String getDataValidade() { return dataValidade; }
    public void setDataValidade(String dataValidade) { this.dataValidade = dataValidade; }
}
