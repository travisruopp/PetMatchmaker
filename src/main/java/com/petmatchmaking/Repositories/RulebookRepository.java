package com.petmatchmaking.Repositories;

import org.springframework.data.repository.CrudRepository;

import com.petmatchmaking.Models.RulebookModel;

public interface RulebookRepository extends CrudRepository<RulebookModel, Long> {
    Iterable<RulebookModel> findByAnswerId(Long answerId);
}
