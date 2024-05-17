package com.petmatchmaking.Repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.petmatchmaking.Models.UserModel;

/**
 * Interface for the CRUD and expanded CRUD operation for the user table
 */
public interface UserRepository extends CrudRepository<UserModel, Long>{
    
    /**
     * Method to find a user model by the user's login id
     * 
     * @param userId user's login id
     * 
     * @return user model
     */
    Optional<UserModel> findByUserId(String userId);


    // do not need this
    // /**
    //  * Method to find a user model by the user's login password
    //  * 
    //  * @param password user's login password
    //  * 
    //  * @return user model
    //  */
    // Optional<UserModel> findByPassword(String password);

    /**
     * Method to find the user model by the user's email
     * 
     * @param email user's email
     * 
     * @return user model
     */
    Optional<UserModel> findByEmail(String email);
}
