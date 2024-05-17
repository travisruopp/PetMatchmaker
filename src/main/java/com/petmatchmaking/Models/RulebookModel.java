package com.petmatchmaking.Models;

import jakarta.persistence.*;

@Entity
@Table(name = "t_Rulebooks")
public class RulebookModel {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long answerId;

    @Column(nullable = false)
    private Long petId;

    @Column
    private int postiveScore = 1;

    @Column
    private int negativeScore = -1;
    
    /**
     * constructor
     */
    public RulebookModel() {
    }

    /**
     * parameterized instructor
     */
    public RulebookModel(Long answerId, int postiveScore, int negativeScore, PetModel pet) {
        this.answerId = answerId;
        this.postiveScore = postiveScore;
        this.negativeScore = negativeScore;
        this.petId = pet.getId();
    }

    /**
     * return id
     */

    public Long getId() {
        return id;
    }

    /**
     * return answerId
     */
    public Long getAnswerId() {
        return answerId;
    }

    /**
     * return positive score
     */
    public int getPostiveScore() {
        return postiveScore;
    }

    /**
     * return negative score
     */
    public int getNegativeScore() {
        return negativeScore;
    }

    public Long getPetId() {
        return petId;
    }
    
    /**
     * To string for pet model
     */
    @Override
    public String toString() {
        return "RulebookModel [id=" + id + ", answerId=" + answerId + ", postiveScore=" + postiveScore
                + ", negativeScore=" + negativeScore + ", petid=" + petId + "]";
    }

    

}