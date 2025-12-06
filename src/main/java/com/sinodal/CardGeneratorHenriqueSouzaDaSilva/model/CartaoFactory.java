package com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model;

public class CartaoFactory {
    
    public static CartaoCredito criarCartao(Bandeira bandeira, Titular titular) {
        GeradorCartao gerador = new GeradorCartao();
        
        return gerador.gerarCartaoCompleto(bandeira, titular);
    }
    
    public static CartaoEntity criarCartaoEntity(CartaoCredito cartao) {
        CartaoEntity entity = new CartaoEntity();
        entity.setNumero(cartao.getNumero());
        entity.setBandeira(cartao.getBandeira().getNome());
        entity.setTitular(cartao.getTitular().getNome());
        entity.setCvv(cartao.getCvv());
        entity.setDataValidade(cartao.getDataValidade());
        return entity;
    }
}
