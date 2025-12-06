package com.sinodal.CardGeneratorHenriqueSouzaDaSilva.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model.GeradorCartao;
import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.service.SingletonService;

@RestController
@RequestMapping("/api/gerador")
public class GeradorController {
    
    private static final Logger logger = LoggerFactory.getLogger(GeradorController.class);
    
    @PostMapping("/numero")
    public ResponseEntity<Map<String, Object>> gerarNumero(@RequestBody Map<String, String> request) {
        try {
            logger.debug("Gerando número de cartão");
            System.out.println("Iniciando geração de número...");
            
            String bandeiraNome = request.get("bandeira");
            Bandeira bandeira = Bandeira.valueOf(bandeiraNome.toUpperCase());
            
            // Usando Singleton
            SingletonService singleton = SingletonService.getInstance();
            singleton.incrementarContador();
            
            GeradorCartao gerador = new GeradorCartao();
            String numero = gerador.gerarNumeroCartao(bandeira);
            
            Map<String, Object> response = new HashMap<>();
            response.put("numero", numero);
            response.put("numeroFormatado", numero.replaceAll("(.{4})", "$1 ").trim());
            response.put("bandeira", bandeira.getNome());
            
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            logger.debug("Bandeira inválida: " + e.getMessage());
            logger.debug("Erro na geração: " + e.getMessage());
            Map<String, Object> error = new HashMap<>();
            error.put("erro", "Bandeira inválida: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    @PostMapping("/multiplos")
    public ResponseEntity<List<Map<String, Object>>> gerarMultiplos(@RequestBody Map<String, Object> request) {
        try {
            String bandeiraNome = (String) request.get("bandeira");
            int quantidade = Integer.parseInt(request.get("quantidade").toString());
            
            if (quantidade > 10) quantidade = 10; // Limite máximo
            
            Bandeira bandeira = Bandeira.valueOf(bandeiraNome.toUpperCase());
            GeradorCartao gerador = new GeradorCartao();
            
            List<Map<String, Object>> cartoes = new ArrayList<>();
            
            for (int i = 0; i < quantidade; i++) {
                String numero = gerador.gerarNumeroCartao(bandeira);
                String cvv = gerador.gerarCVV(bandeira);
                String validade = gerador.gerarDataValidade();
                
                Map<String, Object> cartao = new HashMap<>();
                cartao.put("numero", numero.replaceAll("(.{4})", "$1 ").trim());
                cartao.put("cvv", cvv);
                cartao.put("validade", validade);
                cartao.put("bandeira", bandeira.getNome());
                
                cartoes.add(cartao);
            }
            
            return ResponseEntity.ok(cartoes);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getGeradorInfo(@PathVariable Long id) {
        logger.debug("Tentativa de GET em GeradorController, ID: " + id);
        return ResponseEntity.ok("Operação GET não aplicável para GeradorController. ID: " + id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateGerador(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        logger.debug("Tentativa de PUT em GeradorController, ID: " + id);
        return ResponseEntity.ok("Operação PUT não aplicável para GeradorController. ID: " + id + ", Request: " + request.toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGerador(@PathVariable Long id) {
        logger.debug("Tentativa de DELETE em GeradorController, ID: " + id);
        return ResponseEntity.ok("Operação DELETE não aplicável para GeradorController. ID: " + id);
    }
}
