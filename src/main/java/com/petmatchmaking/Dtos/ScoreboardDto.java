package com.petmatchmaking.Dtos;

import com.petmatchmaking.Models.ScoreboardModel;

public class ScoreboardDto {
    private Long id;
    private long petId;
    private String petType;
    private int score;

    public ScoreboardDto(ScoreboardModel model) {
        this.id = model.getId();
        this.petId = model.getPetId();
        this.petType = model.getPetType();
        this.score = model.getScore();
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getPetId() {
        return petId;
    }

    public void setPetId(long petId) {
        this.petId = petId;
    }

    public String getPetType() {
        return petType;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

}
