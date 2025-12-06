package com.sinodal.CardGeneratorHenriqueSouzaDaSilva.service;

public class SingletonService {
    private static SingletonService instance;
    private int contadorCartoes = 0;
    
    private SingletonService() {}
    
    public static synchronized SingletonService getInstance() {
        if (instance == null) {
            instance = new SingletonService();
        }
        return instance;
    }
    
    public int incrementarContador() {
        return ++contadorCartoes;
    }
    
    public int getContadorCartoes() {
        return contadorCartoes;
    }
}