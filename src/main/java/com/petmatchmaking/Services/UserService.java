package com.petmatchmaking.Services;


import java.util.*;

import org.springframework.stereotype.Service;

import com.petmatchmaking.Models.UserModel;
import com.petmatchmaking.Repositories.UserRepository;

import jakarta.annotation.*;

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    /**
     * Default Constructor
     */
    public UserService() {
    }

    /**
     * Parameterized Constructor
     * 
     * @param userRepository user repository
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

     /**
     * Method to find all Users
     * 
     * @return All user model
     */
    public Iterable<UserModel> findAll(){
        Iterable<UserModel> users = new ArrayList<>();
        try {
            users = userRepository.findAll();
        } catch (Exception ex) {
            throw ex;
        }
        return users;
    }

    /**
     * Method to find users by ID
     * 
     * @param id user id
     * 
     * @return the user model
     */
    public UserModel findById(Long id){
        UserModel user = null;
        try {
            Optional<UserModel> optUser = userRepository.findById(id);
            if (optUser.isPresent()) {
                user = optUser.get();
            }
        } catch (Exception ex) {
            throw ex;
        }
        return user;
    }

    /**
     * Method to find users by user login id
     * 
     * @param userId user login id 
     * 
     * @return the user model
     */
    public UserModel findByUserId(String userId){
        UserModel user = null;
        try {
            Optional<UserModel> optUser = userRepository.findByUserId(userId);
            if (optUser.isPresent()) {
                user = optUser.get();
            }
        } catch (Exception ex) {
            throw ex;
        }
        return user;
    }

    /**
     * Method to find users by user email
     * 
     * @param email user email
     * 
     * @return the user model
     */
    public UserModel findByEmail(String email){
        UserModel user = null;
        try {
            Optional<UserModel> optUser = userRepository.findByEmail(email);
            if (optUser.isPresent()) {
                user = optUser.get();
            }
        } catch (Exception ex) {
            throw ex;
        }
        return user;
    }
    /**
     * Method to delete an user by id
     * 
     * @param id user id
     * 
     * @return if the user was deleted
     */
    public boolean deleteUser(Long id){
        boolean result = false;
        try {
            userRepository.deleteById(id);
            Optional<UserModel> optUser = userRepository.findById(id);
            if (!optUser.isPresent()) {
                result = true;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return result;
    }

    /**
     * Method to update and add an user
     * 
     * @param user the user to add or update
     * 
     * @return the added or updated user model
     */
    public UserModel saveUser(UserModel user){
        try {
                user = userRepository.save(user);
        } catch (Exception ex) {
            throw ex;
        }
        return user;
    }
}
