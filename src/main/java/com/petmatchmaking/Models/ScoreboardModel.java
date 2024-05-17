package com.petmatchmaking.Models;



import jakarta.persistence.*;
@Entity
@Table(name = "t_Scoreboards")
public class ScoreboardModel {
    @Id
    @GeneratedValue
    private Long id;

    @Override
    public String toString() {
        return "ScoreboardModel [id=" + id + ", user=" + user + ", petId=" + petId + ", petType=" + petType + ", score="
                + score + "]";
    }

    @ManyToOne
    private UserModel user;

    private long petId;
    private String petType;

    

    private int score;

    public ScoreboardModel() {
    }

    /**
     * gives us 1 user w/ array of pet types
     */
    public ScoreboardModel(UserModel user, PetModel pet ) {
        this.user = user;   
    
         this.petId = pet.getId();
         this.petType = pet.getPetType();
         this.score = pet.getDefaultScore();
    }

    public Long getId() {
        return id;
    }

    public UserModel getUser() {
        return user;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }  
    public long getPetId() {
        return petId;
    }

    public String getPetType() {
        return petType;
    }

    
}

