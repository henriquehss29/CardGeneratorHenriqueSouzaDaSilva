package com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model;

public class ValidadorCartao {
    
    /**
     * Valida um número de cartão usando o algoritmo de Luhn
     * @param numero Número do cartão a ser validado
     * @return true se o número for válido, false caso contrário
     */
    public static boolean validarNumeroCartao(String numero) {
        if (numero == null || numero.trim().isEmpty()) {
            return false;
        }
        
        // Remove espaços e caracteres não numéricos
        numero = numero.replaceAll("\\D", "");
        
        // Verifica se tem tamanho válido (13-19 dígitos)
        if (numero.length() < 13 || numero.length() > 19) {
            return false;
        }
        
        return aplicarAlgoritmoLuhn(numero);
    }
    
    /**
     * Aplica o algoritmo de Luhn para validação
     * @param numero Número do cartão
     * @return true se válido pelo algoritmo de Luhn
     */
    private static boolean aplicarAlgoritmoLuhn(String numero) {
        int soma = 0;
        boolean alternar = false;
        
        // Percorre o número da direita para a esquerda
        for (int i = numero.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numero.charAt(i));
            
            if (alternar) {
                digito *= 2;
                // Se o resultado for maior que 9, subtrai 9
                if (digito > 9) {
                    digito -= 9;
                }
            }
            
            soma += digito;
            alternar = !alternar;
        }
        
        // O número é válido se a soma for divisível por 10
        return (soma % 10) == 0;
    }
    
    /**
     * Calcula o dígito verificador usando o algoritmo de Luhn
     * @param numeroSemDigitoVerificador Número do cartão sem o último dígito
     * @return Dígito verificador calculado
     */
    public static int calcularDigitoVerificador(String numeroSemDigitoVerificador) {
        int soma = 0;
        boolean alternar = true;
        
        // Percorre da direita para esquerda
        for (int i = numeroSemDigitoVerificador.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(numeroSemDigitoVerificador.charAt(i));
            
            if (alternar) {
                digito *= 2;
                if (digito > 9) {
                    digito -= 9;
                }
            }
            
            soma += digito;
            alternar = !alternar;
        }
        
        // Calcula o dígito que torna a soma divisível por 10
        return (10 - (soma % 10)) % 10;
    }
    
    /**
     * Identifica a bandeira do cartão baseado no número
     * @param numero Número do cartão
     * @return Bandeira identificada ou null se não identificada
     */
    public static Bandeira identificarBandeira(String numero) {
        if (numero == null || numero.trim().isEmpty()) {
            return null;
        }
        
        numero = numero.replaceAll("\\D", "");
        
        // Elo: prefixos específicos (verificar primeiro)
        final String[] prefixosElo = {"5041", "5066", "6362", "6363"};
        for (String prefixo : prefixosElo) {
            if (numero.startsWith(prefixo)) {
                return Bandeira.ELO;
            }
        }
        
        // American Express: começa com 34 ou 37
        if (numero.startsWith("34") || numero.startsWith("37")) {
            return Bandeira.AMEX;
        }
        
        // Visa: começa com 4
        if (numero.startsWith("4")) {
            return Bandeira.VISA;
        }
        
        // Mastercard: começa com 5
        if (numero.startsWith("5")) {
            return Bandeira.MASTERCARD;
        }
        
        return null;
    }
}