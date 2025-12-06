package com.sinodal.CardGeneratorHenriqueSouzaDaSilva.interfaces;

import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model.Bandeira;
import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model.CartaoCredito;
import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model.Titular;

public interface CardGeneratorInterface {
    CartaoCredito gerarCartaoCompleto(Bandeira bandeira, Titular titular);
}
