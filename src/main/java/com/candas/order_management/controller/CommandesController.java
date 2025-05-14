package com.candas.order_management.controller;

import com.candas.order_management.models.Commandes;
import com.candas.order_management.service.CommandesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CommandesController {
    private final CommandesService commandesService;

    @Autowired
    public CommandesController(CommandesService commandesService) {
        this.commandesService = commandesService;
    }

    @PostMapping("/commandes")
    public ResponseEntity<Commandes> saveCommandes(@RequestBody Commandes commandes) {
        Commandes newCommandes = commandesService.saveCommandes(commandes);
        return ResponseEntity.ok(newCommandes);
    }

    @GetMapping("/commandes")
    public List<Commandes> getAllCommandes() {
        return commandesService.getAllCommandes();
    }

    @GetMapping("/commandes/{id}")
    public ResponseEntity<Commandes> getCommandeById(@PathVariable long id) {
        Optional<Commandes> commandes = commandesService.getCommandesById(id);
        return commandes.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/commandes/{id}")
    public ResponseEntity<Commandes> updateCommandes(@PathVariable long id, @RequestBody Commandes commandes) {
        Commandes commande = commandesService.updateCommandes(id, commandes);
        return ResponseEntity.ok(commande);
    }

    @DeleteMapping("/commandes/{id}")
    public ResponseEntity<String> deleteCommandes(@PathVariable long id) {
        commandesService.deleteCommandes(id);
        return ResponseEntity.ok("Commandes deleted successfully");
    }
}
