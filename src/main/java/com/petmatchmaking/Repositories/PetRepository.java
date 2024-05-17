package com.petmatchmaking.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.petmatchmaking.Models.PetModel;

/**
 * Interface for the CRUD and expanded CRUD operation for the pet table
 */
public interface PetRepository extends CrudRepository<PetModel, Long>{
    
}
