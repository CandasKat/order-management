package com.candas.order_management;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class CommandesProduitsController {
    private final CommandesProduitsService commandesProduitsService;

    @Autowired
    public CommandesProduitsController(CommandesProduitsService commandesProduitsService) {
        this.commandesProduitsService = commandesProduitsService;
    }

    @PostMapping("/commandes_produits")
    public ResponseEntity<CommandesProduits> saveCommandes(@RequestBody CommandesProduits commandes) {
        CommandesProduits newCommandes = commandesProduitsService.saveCommandeProduits(commandes);
        return ResponseEntity.ok(newCommandes);
    }

    @GetMapping("/commandes_produits")
    public List<CommandesProduits> getAllCommandes() {
        return commandesProduitsService.getAllCommandeProduits();
    }

    @GetMapping("/commandes_produits/{id}")
    public ResponseEntity<CommandesProduits> getCommandeById(@PathVariable long id) {
        Optional<CommandesProduits> commandesProduits = commandesProduitsService.getCommandeProduitsById(id);
        return commandesProduits.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/commandes_produits/{id}")
    public ResponseEntity<CommandesProduits> updateCommande(@PathVariable long id, @RequestBody CommandesProduits commandes) {
        CommandesProduits commandesProduits = commandesProduitsService.updateCommandeProduits(id, commandes);
        return ResponseEntity.ok(commandesProduits);
    }
}
