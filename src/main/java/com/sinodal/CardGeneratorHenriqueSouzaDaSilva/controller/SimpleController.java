package com.sinodal.CardGeneratorHenriqueSouzaDaSilva.controller;

import java.util.HashMap;
import java.util.Map;

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

import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.interfaces.CardInterface;
import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model.Bandeira;
import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model.CartaoCredito;

@RestController
@RequestMapping("/api/simple")
public class SimpleController {
    
    @Autowired
    private CardInterface cardInterface;
    
    @PostMapping("/gerar")
    public ResponseEntity<Map<String, Object>> gerarCartao(@RequestBody Map<String, String> request) {
        try {
            System.out.println("Gerando cartão simples...");
            String bandeiraNome = request.get("bandeira");
            String nome = request.get("nome");
            
            Bandeira bandeira = Bandeira.valueOf(bandeiraNome.toUpperCase());
            CartaoCredito cartao = cardInterface.gerarCartao(bandeira, nome);
            
            Map<String, Object> response = new HashMap<>();
            response.put("numero", cartao.getNumeroFormatado());
            response.put("bandeira", cartao.getBandeira().getNome());
            response.put("titular", cartao.getTitular().getNome());
            response.put("cvv", cartao.getCvv());
            response.put("validade", cartao.getDataValidade());
            response.put("valido", cardInterface.validarCartao(cartao.getNumero())); // Using the new interface method
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("erro", "Erro ao gerar cartão: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/validar/{numero}")
    public ResponseEntity<Map<String, Object>> validarCartaoSimples(@PathVariable String numero) {
        Map<String, Object> response = new HashMap<>();
        response.put("numero", numero);
        response.put("valido", cardInterface.validarCartao(numero));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/info")
    public ResponseEntity<String> getSimpleInfo() {
        return ResponseEntity.ok("Simple Controller Info");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSimple(@PathVariable Long id, @RequestBody Map<String, Object> request) {
        return ResponseEntity.ok("Simple Update for ID: " + id + ", data: " + request.toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSimple(@PathVariable Long id) {
        return ResponseEntity.ok("Simple Delete for ID: " + id);
    }
}
