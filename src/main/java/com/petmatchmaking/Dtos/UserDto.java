package com.petmatchmaking.Dtos;

import java.util.*;

import com.petmatchmaking.Models.ScoreboardModel;
import com.petmatchmaking.Models.UserModel;

/**
 * Class that models the user model data transfer object
 */
public class UserDto {
    private Long id; //DO WE NEED THIS? if no, remove from class
    private Collection<ScoreboardModel> scoreBoard;
    private String userId;
    private String password;
    private String name;
    private String email;
    private int orderQuestion = 0; //DO WE NEED THIS? 

    /**
     * Default Constructor
     */
    public UserDto() {
    }

    /**
     * Parametarized constructor with all parameters
     * 
     * @param id user model id
     * @param scoreBoard score board associated with this uder
     * @param userId user id
     * @param password user password
     * @param name user name
     * @param email user email
     * @param orderQuestion
     */
    public UserDto(Long id, Collection<ScoreboardModel> scoreBoard, String userId, String password, String name,
            String email, int orderQuestion) {
        this.id = id;
        this.scoreBoard = scoreBoard;
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.email = email;
        this.orderQuestion = orderQuestion;
    }

    /**
     * Parametarized constructor with one parameter
     * 
     * @param model user model
     */
    public UserDto(UserModel model) {
        this.id = model.getId();
        //this.scoreBoard = model.getScoreBoard();
        this.userId = model.getUserId();
        this.password = model.getPassword();
        this.name = model.getName();
        this.email = model.getEmail();
       // this.orderQuestion = model.getOrderQuestion();
    }

    /**
     * Method to convert userDto to userModel
     * 
     * @return user
     */
    public UserModel convertToModel() {
        if (id == null) {
            id = 0l;
        }
        UserModel user = new UserModel(this.getUserId(), this.getPassword(),
                this.getName(), this.getEmail());
        return user;
    }

    /**
     * Method to get userModel id
     * 
     * @return  userModel id
     */
    public Long getId() {
        return id;
    }

    /**
     * Method to set userModel id
     * 
     * @param id userModel id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method to get user id
     * 
     * @return user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Method to set user id
     * 
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Method to get password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Method to set password
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Method to get user name
     * 
     * @return  user name
     */
    public String getName() {
        return name;
    }

    /**
     * Method to set user name
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to get user email
     * 
     * @return user email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Method to set email
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Method to get order of questions
     * 
     * @return order of questions
     */
    public int getOrderQuestion() {
        return orderQuestion;
    }

    /**
     * Method to set order of questions
     * 
     * @param orderQuestion
     */
    public void setOrderQuestion(int orderQuestion) {
        this.orderQuestion = orderQuestion;
    }

    /**
     * Method to get score board 
     * 
     * @return score board 
     */
    public Collection<ScoreboardModel> getScoreBoard() {
        return scoreBoard;
    }

    /**
     * Method to set score board
     * 
     * @param scoreBoard
     */
    public void setScoreBoard(Collection<ScoreboardModel> scoreBoard) {
        this.scoreBoard = scoreBoard;
    }

}
