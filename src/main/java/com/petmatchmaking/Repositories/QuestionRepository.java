package com.petmatchmaking.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.petmatchmaking.Models.*;

/**
 * Interface for the CRUD and expanded CRUD operation for the question table
 */
public interface QuestionRepository extends CrudRepository<QuestionModel, Long> {
   
}
