package com.example.api_farmacia.controller;

import com.example.api_farmacia.entity.Farmacia;
import com.example.api_farmacia.repository.FarmaciaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/farmacias")
public class FarmaciaController {
    private final FarmaciaRepository repository;

    public FarmaciaController(FarmaciaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Farmacia> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Farmacia> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Farmacia criar(@RequestBody Farmacia farmacia) {
        return repository.save(farmacia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Farmacia> atualizar(@PathVariable Long id, @RequestBody Farmacia farmacia) {
        if (!repository.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        farmacia.setId(id);
        return ResponseEntity.ok(repository.save(farmacia));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!repository.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}