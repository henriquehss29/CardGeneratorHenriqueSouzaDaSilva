package com.sinodal.CardGeneratorHenriqueSouzaDaSilva.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model.Bandeira;
import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model.CartaoCredito;
import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model.CartaoEntity;
import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.service.CartaoService;
import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.service.SingletonService;

@RestController
@RequestMapping("/api/cartao")
public class CartaoController {
    
    private static final Logger logger = LoggerFactory.getLogger(CartaoController.class);
    
    @Autowired
    private CartaoService cartaoService;
    
    @PostMapping("/gerar-cartao")
    public ResponseEntity<Map<String, Object>> gerarCartao(
            @RequestBody Map<String, Object> request) {
        
        try {
            logger.debug("Iniciando geração de cartão");
            System.out.println("Gerando novo cartão de crédito...");

            String bandeiraNome = (String) request.get("bandeira");
            String nome = (String) request.get("nome");

            Bandeira bandeira = Bandeira.valueOf(bandeiraNome.toUpperCase());

            // Usando o service que salva no banco
            CartaoCredito cartao = cartaoService.gerarCartao(bandeira, nome);

            // Usando padrão Singleton
            SingletonService singleton = SingletonService.getInstance();
            int numeroCartao = singleton.incrementarContador();

            Map<String, Object> response = new HashMap<>();
            response.put("numero", cartao.getNumeroFormatado());
            response.put("bandeira", cartao.getBandeira().getNome());
            response.put("titular", cartao.getTitular().getNome());
            response.put("cvv", cartao.getCvv());
            response.put("validade", cartao.getDataValidade());
            response.put("sequencial", numeroCartao);

            logger.debug("Cartão gerado com sucesso: " + numeroCartao);
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            logger.debug("Erro ao gerar cartão: " + e.getMessage());
            logger.debug("Erro na geração: " + e.getMessage());
            Map<String, Object> error = new HashMap<>();
            error.put("erro", "Erro ao gerar cartão: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @GetMapping("/bandeiras")
    public ResponseEntity<Bandeira[]> listarBandeiras() {
        try {
            logger.debug("Listando bandeiras disponíveis");
            logger.debug("Consultando bandeiras");
            return ResponseEntity.ok(Bandeira.values());
        } catch (Exception e) {
            logger.debug("Erro ao listar bandeiras: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/listar")
    public ResponseEntity<List<CartaoEntity>> listarTodos() {
        try {
            logger.debug("Listando todos os cartões");
            logger.debug("Buscando todos os cartões");
            List<CartaoEntity> cartoes = cartaoService.listarTodos();
            return ResponseEntity.ok(cartoes);
        } catch (Exception e) {
            logger.debug("Erro ao listar cartões: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartaoEntity> buscarPorId(@PathVariable Long id) {
        try {
            logger.debug("Buscando cartão por ID: " + id);
            CartaoEntity cartao = cartaoService.buscarPorId(id);
            return ResponseEntity.ok(cartao);
        } catch (Exception e) {
            logger.debug("Erro ao buscar cartão por ID: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartaoEntity> atualizarCartao(@PathVariable Long id, @RequestBody CartaoEntity cartaoAtualizado) {
        try {
            logger.debug("Atualizando cartão com ID: " + id);
            CartaoEntity cartao = cartaoService.atualizarCartao(id, cartaoAtualizado);
            return ResponseEntity.ok(cartao);
        } catch (Exception e) {
            logger.debug("Erro ao atualizar cartão: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCartao(@PathVariable Long id) {
        try {
            logger.debug("Deletando cartão com ID: " + id);
            cartaoService.deletarCartao(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            logger.debug("Erro ao deletar cartão: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
