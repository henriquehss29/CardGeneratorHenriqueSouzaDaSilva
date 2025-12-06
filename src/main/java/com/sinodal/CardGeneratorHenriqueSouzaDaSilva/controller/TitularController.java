package com.sinodal.CardGeneratorHenriqueSouzaDaSilva.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinodal.CardGeneratorHenriqueSouzaDaSilva.model.Titular;

@RestController
@RequestMapping("/api/titular")
public class TitularController {
    
    private List<Titular> titulares = new ArrayList<>(); // In-memory storage
    private Long nextId = 1L;

    @PostMapping("/criar")
    public ResponseEntity<Map<String, Object>> criarTitular(@RequestBody Map<String, String> request) {
        try {
            System.out.println("Criando novo titular...");
            Titular titular = new Titular(nextId++, request.get("nome"));
            titulares.add(titular);

            Map<String, Object> response = new HashMap<>();
            response.put("id", titular.getId());
            response.put("nome", titular.getNome());
            
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("erro", "Erro ao criar titular: " + e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Titular>> listarTitulares() {
        return ResponseEntity.ok(titulares);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Titular> buscarTitularPorId(@PathVariable Long id) {
        return titulares.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Titular> atualizarTitular(@PathVariable Long id, @RequestBody Map<String, String> request) {
        return titulares.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .map(titular -> {
                    titular.setNome(request.get("nome"));
                    return ResponseEntity.ok(titular);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTitular(@PathVariable Long id) {
        boolean removed = titulares.removeIf(t -> t.getId().equals(id));
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
