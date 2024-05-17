package com.petmatchmaking.Dtos;

import com.petmatchmaking.Models.AnswerModel;

public class AnswerDto {

    /**
     * Class for the Answer DTO
     */
    private Long id;
    private String answer;
    private boolean selected = false;


    /**
     * blank constructor
     */
    public AnswerDto() {
    }

    public AnswerDto(Long id, String answer, boolean selected) {
        this.id = id;
        this.answer = answer;
        this.selected = selected;
    }

    /**
     * DTO parameterized Constructor
     * 
     * @param model
     */
    public AnswerDto(AnswerModel model) {
        this.id = model.getId();
        this.answer = model.getAnswer();
    }

    /**
     * Get Dto id
     * 
     * @return answer id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set Id
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * get answer
     * 
     * @return string answer 
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * set an answer
     * 
     * @param answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Method to get if the answer was selected
     * 
     * @return if the answer was selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Method to set if the answer was selected
     * 
     * @param selected if the answer was selected
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return answer;
    }
    
}
