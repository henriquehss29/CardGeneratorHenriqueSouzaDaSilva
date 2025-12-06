package com.sinodal.CardGeneratorHenriqueSouzaDaSilva.interfaces;

import org.springframework.stereotype.Service;

import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model.Bandeira;
import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model.CartaoCredito;

public interface CardInterface {
    CartaoCredito gerarCartao(Bandeira bandeira, String nome);
    boolean validarCartao(String numero);
}

@Service
class CardInterfaceImpl implements CardInterface {
    private final com.sinodal.CardGeneratorHenriqueSouzaDaSilva.service.CartaoService cartaoService;

    public CardInterfaceImpl(com.sinodal.CardGeneratorHenriqueSouzaDaSilva.service.CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @Override
    public CartaoCredito gerarCartao(Bandeira bandeira, String nome) {
        return cartaoService.gerarCartao(bandeira, nome);
    }

    @Override
    public boolean validarCartao(String numero) {
        return cartaoService.validarCartao(numero);
    }
}
