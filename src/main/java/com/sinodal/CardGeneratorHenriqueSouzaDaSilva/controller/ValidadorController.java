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
import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model.ValidadorCartao;
import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.service.SingletonService;

@RestController
@RequestMapping("/api/validador")
public class ValidadorController {
    
    private static final Logger logger = LoggerFactory.getLogger(ValidadorController.class);
    
    @PostMapping("/validar")
    public ResponseEntity<Map<String, Object>> validarCartao(@RequestBody Map<String, String> request) {
        try {
            logger.debug("Validando cartão");
            System.out.println("Validando número de cartão...");
            
            String numero = request.get("numero");
            
            // Usando Singleton
            SingletonService singleton = SingletonService.getInstance();
            singleton.incrementarContador();
            
            Map<String, Object> response = new HashMap<>();
            response.put("numero", numero);
            response.put("valido", ValidadorCartao.validarNumeroCartao(numero));
            
            Bandeira bandeira = ValidadorCartao.identificarBandeira(numero);
            response.put("bandeira", bandeira != null ? bandeira.getNome() : null);
            
            logger.debug("Validação concluída");
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            logger.debug("Erro na validação: " + e.getMessage());
            logger.debug("Erro: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    @GetMapping("/identificar/{numero}")
    public ResponseEntity<Map<String, Object>> identificarBandeira(@PathVariable String numero) {
        Map<String, Object> response = new HashMap<>();
        Bandeira bandeira = ValidadorCartao.identificarBandeira(numero);
        
        response.put("numero", numero);
        response.put("bandeira", bandeira != null ? bandeira.getNome() : "Não identificada");
        response.put("valido", ValidadorCartao.validarNumeroCartao(numero));
        
        return ResponseEntity.ok(response);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<String>> listarTodasValidacoes() {
        logger.debug("Tentativa de GET listar em ValidadorController");
        List<String> validacoes = new ArrayList<>();
        validacoes.add("Validação 1 para o cartão X");
        validacoes.add("Validação 2 para o cartão Y");
        return ResponseEntity.ok(validacoes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateValidador(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        logger.debug("Tentativa de PUT em ValidadorController, ID: " + id);
        return ResponseEntity.ok("Operação PUT não aplicável para ValidadorController. ID: " + id + ", Request: " + request.toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteValidador(@PathVariable Long id) {
        logger.debug("Tentativa de DELETE em ValidadorController, ID: " + id);
        return ResponseEntity.ok("Operação DELETE não aplicável para ValidadorController. ID: " + id);
    }
}
