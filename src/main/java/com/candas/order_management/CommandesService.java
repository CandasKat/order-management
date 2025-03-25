package com.candas.order_management;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommandesService {
    private static final Logger logger = LoggerFactory.getLogger(CommandesService.class);
    private final CommandesRepository commandesRepository;

    @Autowired
    public CommandesService(CommandesRepository commandesRepository) {
        logger.info("CommandesService created");
        this.commandesRepository = commandesRepository;
    }

    public Commandes saveCommandes(Commandes commandes) {
        logger.info("Saving commandes " + commandes);
        return this.commandesRepository.save(commandes);
    }

    public List<Commandes> getAllCommandes() {
        logger.info("Getting all commandes");
        return this.commandesRepository.findAll();
    }

    public Optional<Commandes> getCommandesById(Long id) {
        logger.info("Getting commandes by id: " + id);
        return this.commandesRepository.findById(id);
    }

    public Commandes updateCommandes(Long id, Commandes updatedCommand) {
        Optional<Commandes> commandesOptional = this.commandesRepository.findById(id);
        if (commandesOptional.isPresent()) {
            Commandes commandes = commandesOptional.get();
            commandes.setStatus(updatedCommand.getStatus());
            logger.info("Updating commandes with id: " + id + " with status: " + updatedCommand.getStatus());
            return this.commandesRepository.save(commandes);
        }
        else {
            throw new RuntimeException("Commandes not found");
        }
    }

    public void deleteCommandes(Long id) {
        this.commandesRepository.deleteById(id);
        logger.info("Deleting commandes with id: " + id);
    }
}
