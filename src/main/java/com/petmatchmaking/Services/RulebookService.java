package com.petmatchmaking.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.petmatchmaking.Models.RulebookModel;
import com.petmatchmaking.Repositories.RulebookRepository;

import jakarta.annotation.Resource;

@Service
public class RulebookService {

    @Resource
    private RulebookRepository rulebookRepository;

    public RulebookService() {
    }

    public RulebookService(RulebookRepository rulebookRepository) {
        this.rulebookRepository = rulebookRepository;
    }

    /**
     * Returns all Rulebook Models
     * 
     * @return
     */
    public Iterable<RulebookModel> findAll() {
        Iterable<RulebookModel> rulebooks = new ArrayList<>();
        try {
            rulebooks = rulebookRepository.findAll();
        } catch (Exception ex) {
            throw ex;
        }
        return rulebooks;
    }

    public Iterable<RulebookModel> findByAnswerId(Long answerId) {
        Iterable<RulebookModel> rulebooks = new ArrayList<>();
        try {
            rulebooks = rulebookRepository.findByAnswerId(answerId);
        } catch (Exception ex) {
            throw ex;
        }
        return rulebooks;
    }
    /**
     * returns a specific Rulebook by id
     * 
     * @param id
     * @return
     */
    public RulebookModel findById(Long id) {
        RulebookModel rulebook = null;
        try {
            Optional<RulebookModel> optRulebook = rulebookRepository.findById(id);
            if (optRulebook.isPresent()) {
                rulebook = optRulebook.get();
            }
        } catch (Exception ex) {
            throw ex;
        }
        return rulebook;
    }

    /**
     * deletes a rulebook by id
     * 
     * @param id
     * @return
     */
    public boolean deleteRulebook(Long id) {
        boolean result = false;
        try {
            rulebookRepository.deleteById(id);
            Optional<RulebookModel> optRulebook = rulebookRepository.findById(id);
            if (!optRulebook.isPresent()) {
                result = true;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return result;
    }

    /**
     * save edits or save when adding a rulebook
     * 
     * @param rulebook
     * @return
     */
    public RulebookModel saveRulebook(RulebookModel rulebook) {
        try {
            rulebook = rulebookRepository.save(rulebook);
        } catch (Exception ex) {
            throw ex;
        }
        return rulebook;
    }

}