package com.petmatchmaking.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.petmatchmaking.Models.AnswerModel;

/**
 * Interface for the CRUD and expanded CRUD operation for the answer table
 */
public interface AnswerRepository extends CrudRepository<AnswerModel, Long> {

}
