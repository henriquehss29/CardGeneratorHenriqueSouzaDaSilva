package com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GeradorCartao implements com.sinodal.CardGeneratorHenriqueSouzaDaSilva.interfaces.CardGeneratorInterface {
    private final Random random;
    
    public GeradorCartao() {
        this.random = new Random();
    }
    
    // Gera um número de cartão válido usando algoritmo de Luhn
    public String gerarNumeroCartao(Bandeira bandeira) {
        int tamanho = bandeira.getTamanhoNumero();
        String prefixo = obterPrefixoAleatorio(bandeira);
        
        StringBuilder numero = new StringBuilder(prefixo);
        
        // Preenche com dígitos aleatórios (menos 1 para o dígito verificador)
        while (numero.length() < tamanho - 1) {
            numero.append(random.nextInt(10));
        }
        
        // Calcula o dígito verificador usando algoritmo de Luhn
        int digitoVerificador = calcularDigitoVerificador(numero.toString());
        numero.append(digitoVerificador);
        
        return numero.toString();
    }
    
    // Obtém um prefixo aleatório da bandeira
    private String obterPrefixoAleatorio(Bandeira bandeira) {
        String[] prefixos = bandeira.getPrefixos().split(",");
        return prefixos[random.nextInt(prefixos.length)];
    }
    
    // Calcula o dígito verificador usando o ValidadorCartao
    private int calcularDigitoVerificador(String numero) {
        return ValidadorCartao.calcularDigitoVerificador(numero);
    }
    
    // Valida um número de cartão usando o ValidadorCartao
    public boolean validarNumeroCartao(String numero) {
        return ValidadorCartao.validarNumeroCartao(numero);
    }
    
    // Gera um CVV aleatório
    public String gerarCVV(Bandeira bandeira) {
        int tamanho = (bandeira == Bandeira.AMEX) ? 4 : 3;
        StringBuilder cvv = new StringBuilder();
        
        for (int i = 0; i < tamanho; i++) {
            cvv.append(random.nextInt(10));
        }
        
        return cvv.toString();
    }
    
    // Gera uma data de validade futura
    public String gerarDataValidade() {
        LocalDate hoje = LocalDate.now();
        int anosAFrente = 3 + random.nextInt(3); // 3 a 5 anos
        LocalDate validade = hoje.plusYears(anosAFrente);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yyyy");
        return validade.format(formatter);
    }
    
    // Método principal para gerar um cartão completo
    public CartaoCredito gerarCartaoCompleto(Bandeira bandeira, Titular titular) {
        String numero = gerarNumeroCartao(bandeira);
        String cvv = gerarCVV(bandeira);
        String dataValidade = gerarDataValidade();
        
        return new CartaoCredito(numero, bandeira, titular, cvv, dataValidade);
    }
}
