package com.petmatchmaking.Repositories;

import java.util.Collection;


import org.springframework.data.repository.CrudRepository;


import com.petmatchmaking.Models.ScoreboardModel;

public interface ScoreboardRepository extends CrudRepository <ScoreboardModel, Long>{
        Collection<ScoreboardModel> findByUserId(Long id);  
       
}
