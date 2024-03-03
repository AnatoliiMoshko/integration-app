package com.example.service.integrationapp.controller;

import com.example.service.integrationapp.clients.RestTemplateClient;
import com.example.service.integrationapp.model.EntityModel;
import com.example.service.integrationapp.model.UpsertEntityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/client/entity")
@RequiredArgsConstructor
public class EntityClientController {

    private final RestTemplateClient client;

    @GetMapping
    public ResponseEntity<List<EntityModel>> entityList() {
        return ResponseEntity.ok(client.getEntityList());
    }

    @GetMapping("/{name}")
    public ResponseEntity<EntityModel> entityByName(@PathVariable String name) {
        return ResponseEntity.ok(client.getEntityByName(name));
    }

    @PostMapping
    public ResponseEntity<EntityModel> createEntity(@RequestBody UpsertEntityRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(client.createEntity(request));
    }

    @PostMapping("/{id}")
    public ResponseEntity<EntityModel> updateEntity(@PathVariable UUID id, @RequestBody UpsertEntityRequest request) {
        return ResponseEntity.ok(client.updateEntity(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EntityModel> deleteById(@PathVariable UUID id) {
        client.deleteEntityById(id);

        return ResponseEntity.noContent().build();
    }
}
