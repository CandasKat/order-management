package com.candas.order_management.service;

import com.candas.order_management.dto.CommandesProduitsDTO;
import com.candas.order_management.models.Commandes;
import com.candas.order_management.models.CommandesProduits;
import com.candas.order_management.repository.CommandesProduitsRepository;
import com.candas.order_management.repository.CommandesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandesProduitsService {
    private static final Logger logger = LoggerFactory.getLogger(CommandesProduitsService.class);

    private final CommandesProduitsRepository commandesProduitsRepository;
    private final CommandesRepository commandesRepository;

    @Autowired
    public CommandesProduitsService(CommandesProduitsRepository commandesProduitsRepository,
                                    CommandesRepository commandesRepository) {
        logger.info("CommandesService created");
        this.commandesProduitsRepository = commandesProduitsRepository;
        this.commandesRepository = commandesRepository;
    }

    public CommandesProduits saveCommandeProduits(CommandesProduitsDTO dto) {
        Commandes commande = commandesRepository.findById(dto.commande_id)
                .orElseThrow(() -> new RuntimeException("Commande not found with id: " + dto.commande_id));

        CommandesProduits entity = new CommandesProduits(commande, dto.produit_id, dto.quantite);
        commande.addOrderProduct(entity);
        logger.info("Saving commandes produits " + entity);
        return commandesProduitsRepository.save(entity);
    }

    public List<CommandesProduits> getAllCommandeProduits() {
        logger.info("Getting all commandes produits");
        return this.commandesProduitsRepository.findAll();
    }

    public Optional<CommandesProduits> getCommandeProduitsById(Long id) {
        logger.info("Getting commandes produits by id " + id);
        return this.commandesProduitsRepository.findById(id);
    }

    public CommandesProduits updateCommandeProduits(Long id, CommandesProduitsDTO dto) {
        CommandesProduits existing = commandesProduitsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CommandesProduits not found with id: " + id));
        Commandes commande = commandesRepository.findById(dto.commande_id)
                .orElseThrow(() -> new RuntimeException("Commande not found with id: " + dto.commande_id));
        existing.setProduit_id(dto.produit_id);
        existing.setQuantite(dto.quantite);
        commande.addOrderProduct(existing);
        existing.setCommande(commande);


        logger.info("Updating commandes produits with id: " + id);
        return commandesProduitsRepository.save(existing);
    }

    public void deleteCommandeProduits(Long id) {
        CommandesProduits existing = commandesProduitsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CommandesProduits not found with id: " + id));
        Commandes commande = existing.getCommande();
        commande.removeOrderProduct(existing);
        commandesProduitsRepository.deleteById(id);
        logger.info("Deleting commandes produits with id: " + id);
        commandesRepository.save(commande);
    }
}
