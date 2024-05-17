package com.petmatchmaking.Models;


import jakarta.persistence.*;

/**
 * Class that models the pets
 */
@Entity
@Table(name = "t_Pets")
public class PetModel {
    @Id
    @GeneratedValue()
    private Long id;

    @Column(nullable = false)
    private String petType;

    @Column(length = 3, nullable = false)
    private int defaultScore = 10;

    /**
     * Default Constructor
     */
    public PetModel() {
    }

    /**
     * Parameterized Constructor
     * 
     * @param petType pet type
     * 
     */
    public PetModel(String petType) {
        this.petType = petType;      
    }

    /**
     * Method to get the pet id
     * 
     * @return pet id
     */
    public Long getId() {
        return id;
    }

    /**
     * Method to set the pet id  //TO BE DELETED LATERRRR
     * 
     * @param id pet id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method to get the pet type
     * 
     * @return pet type
     */
    public String getPetType() {
        return petType;
    }

    /**
     * Method to get the score
     * 
     * @return score
     */
    public int getDefaultScore() {
        return defaultScore;
    }
    
    /**
     * Method to set the score
     * 
     * @param score score
     */
    public void setScore(int defaultScore) {
        this.defaultScore = defaultScore;
    }

    /**
     * Override method for toString
     */
    @Override
    public String toString() {
        return "PetModel [id=" + id + ", petType=" + petType + ", score=" + defaultScore + "]";
    }
}
