package com.sinodal.CardGeneratorHenriqueSouzaDaSilva.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.lang.NonNull;

import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model.Bandeira;
import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model.CartaoCredito;
import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model.CartaoEntity;
import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model.GeradorCartao;
import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model.Titular;
import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model.ValidadorCartao;
import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.repository.CartaoRepository;

@Service
public class CartaoService {
    private final GeradorCartao gerador = new GeradorCartao();
    
    @Autowired
    private CartaoRepository cartaoRepository;
    
    public CartaoCredito gerarCartao(Bandeira bandeira, String nome) {
        CartaoCredito cartao = gerador.gerarCartaoCompleto(bandeira, new Titular(nome));
        
        // Salvar no banco
        CartaoEntity entity = new CartaoEntity();
        entity.setNumero(cartao.getNumero());
        entity.setBandeira(cartao.getBandeira().name());
        entity.setTitular(cartao.getTitular().getNome());
        entity.setCvv(cartao.getCvv());
        entity.setDataValidade(cartao.getDataValidade());
        
        cartaoRepository.save(entity);
        
        return cartao;
    }
    
    public boolean validarCartao(String numero) {
        return ValidadorCartao.validarNumeroCartao(numero);
    }
    
    public List<CartaoEntity> listarTodos() {
        return cartaoRepository.findAll();
    }
    
    public List<CartaoEntity> buscarPorBandeira(String bandeira) {
        return cartaoRepository.findByBandeira(bandeira);
    }
    
    public List<CartaoEntity> buscarPorTitular(String titular) {
        return cartaoRepository.findByTitular(titular);
    }

    public CartaoEntity buscarPorId(@NonNull Long id) {
        return cartaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado com ID: " + id));
    }

    public CartaoEntity atualizarCartao(@NonNull Long id, CartaoEntity cartaoAtualizado) {
        return cartaoRepository.findById(id)
                .map(cartao -> {
                    cartao.setNumero(cartaoAtualizado.getNumero());
                    cartao.setBandeira(cartaoAtualizado.getBandeira());
                    cartao.setTitular(cartaoAtualizado.getTitular());
                    cartao.setCvv(cartaoAtualizado.getCvv());
                    cartao.setDataValidade(cartaoAtualizado.getDataValidade());
                    return cartaoRepository.save(cartao);
                })
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado com ID: " + id));
    }

    public void deletarCartao(@NonNull Long id) {
        cartaoRepository.deleteById(id);
    }
}
