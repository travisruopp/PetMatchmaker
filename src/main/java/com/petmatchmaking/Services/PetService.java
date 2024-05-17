package com.petmatchmaking.Services;

import java.util.*;

import org.springframework.stereotype.Service;

import com.petmatchmaking.Models.PetModel;
import com.petmatchmaking.Repositories.PetRepository;

import jakarta.annotation.Resource;

/**
 * Class that models the verification of the repository operations
 */
@Service
public class PetService {
    
    @Resource
    private PetRepository petRepository;

    /**
     * Default Constructor
     */
    public PetService() {
    }

    /**
     * Parameterized Constructor
     * 
     * @param petRepository pet repository
     */
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

     /**
     * Method to find all Pets
     * 
     * @return All pets
     */
    public Iterable<PetModel> findAll(){
        Iterable<PetModel> pets = new ArrayList<>();
        try {
            pets = petRepository.findAll();
        } catch (Exception ex) {
            throw ex;
        }
        return pets;
    }

    /**
     * Method to find pets by ID
     * 
     * @param id pet id
     * 
     * @return the pet
     */
    public PetModel findById(Long id){
        PetModel pet = null;
        try {
            Optional<PetModel> optPet = petRepository.findById(id);
            if (optPet.isPresent()) {
                pet = optPet.get();
            }
        } catch (Exception ex) {
            throw ex;
        }
        return pet;
    }

    /**
     * Method to delete an pet by ID
     * 
     * @param id pet ID
     * 
     * @return if the pet was deleted
     */
    public boolean deletePet(Long id){
        boolean result = false;
        try {
            petRepository.deleteById(id);
            Optional<PetModel> optPet = petRepository.findById(id);
            if (!optPet.isPresent()) {
                result = true;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return result;
    }

    /**
     * Method to update and add an pet
     * 
     * @param pet the pet to add or update
     * 
     * @return the added or updated pet
     */
    public PetModel savePet(PetModel pet){
        try {
                pet = petRepository.save(pet);
        } catch (Exception ex) {
            throw ex;
        }
        return pet;
    }
}
