package com.candas.order_management;

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

    @Autowired
    public CommandesProduitsService(CommandesProduitsRepository commandesProduitsRepository) {
        logger.info("CommandesService created");
        this.commandesProduitsRepository = commandesProduitsRepository;
    }

    public CommandesProduits saveCommandeProduits(CommandesProduits commandesProduits) {
        logger.info("Saving commandes produits " + commandesProduits);
        return this.commandesProduitsRepository.save(commandesProduits);
    }

    public List<CommandesProduits> getAllCommandeProduits() {
        logger.info("Getting all commandes produits");
        return this.commandesProduitsRepository.findAll();
    }

    public Optional<CommandesProduits> getCommandeProduitsById(Long id) {
        logger.info("Getting commandes produits by id " + id);
        return this.commandesProduitsRepository.findById(id);
    }

    public CommandesProduits updateCommandeProduits(Long id, CommandesProduits commandesProduits) {
        Optional<CommandesProduits> commandesProduitsOptional = this.commandesProduitsRepository.findById(id);
        if (commandesProduitsOptional.isPresent()) {
            CommandesProduits commandeProduits = commandesProduitsOptional.get();
            commandeProduits.setCommande(commandesProduits.getCommande());
            commandeProduits.setProduit_id(commandesProduits.getProduit_id());
            commandeProduits.setQuantite(commandesProduits.getQuantite());
            logger.info("Updating commandes produits " + commandeProduits);
            return this.commandesProduitsRepository.save(commandeProduits);
        }
        else {
            throw new RuntimeException("Commandes Produits not found");
        }
    }
}
