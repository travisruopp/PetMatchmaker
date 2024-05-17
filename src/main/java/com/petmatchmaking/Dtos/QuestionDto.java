package com.petmatchmaking.Dtos;

import java.util.ArrayList;
import java.util.Collection;

import com.petmatchmaking.Models.AnswerModel;
import com.petmatchmaking.Models.QuestionModel;

/**
 * Class that models the question model data transfer object
 */
public class QuestionDto {
     
    private Long id;
    private String question;
    private boolean manyAnswers = false;
    private ArrayList<AnswerDto> answerDto;
    private Boolean[] selected;
    /**
     * Default Constructor
     */
    public QuestionDto() {
    }

    /**
     * Parameterized Constructor
     * 
     * @param model Question Model
     */
    public QuestionDto(QuestionModel model) {
        this.id = model.getId();
        this.question = model.getQuestion();
        this.manyAnswers = model.isManyAnswers();
        this.answerDto =convertToDto(model.getAnswers());
        this.selected = new Boolean[model.getAnswers().size()];
    }

    /**
     * Method to convert the question data transfer object to a question model
     * 
     * @return a question model
     */
    public QuestionModel convertToModel(){
        QuestionModel question = new QuestionModel(
            this.getQuestion(), this.isManyAnswers());
            return question;
    }

    /**
     * Method to get the question id
     * 
     * @return question id
     */
    public Long getId() {
        return id;
    }

    /**
     * Method to set the Question id
     * 
     * @param id Question id
     */
    public void setId(Long id) {
        this.id = id;
    }

     /**
     * Method to get the question
     * 
     * @return question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Method to set the question
     * 
     * @return question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Method to get if the question has multiple answers
     * 
     * @return if the question has multiple answers
     */
    public boolean isManyAnswers() {
        return manyAnswers;
    }

    /**
     * Method to Set if the question has multiple answers
     * 
     * @param manyAnswers if the question has multiple answers
     */
    public void setManyAnswers(boolean manyAnswers) {
        this.manyAnswers = manyAnswers;
    }



    public Boolean[] getAnswers() {
        selected = new Boolean[this.answerDto.size()];
        int counter=0;
        for(AnswerDto dto : this.answerDto){
            selected[counter++] = false;
        }
        return selected;
    }
    /**
     * Method to set the collection of answers
     * 
     * @param answerDto collection of answers
     */
    public void setAnswerDto(ArrayList<AnswerDto> answerDto) {
        this.answerDto = answerDto;
    }    
    
    public AnswerDto getSelected(int index){
     return answerDto.get(index);
    } 
    /**
     * Method to convert a collection of answer models to answer data transfer objects
     * 
     * @param answerModels collection of answer models
     * 
     * @return collection of answer data transfer objects
     */
    private ArrayList<AnswerDto> convertToDto(Collection<AnswerModel> answerModels) {
        ArrayList<AnswerDto> answerDtos = new ArrayList<>();
        for (AnswerModel model : answerModels) {
            answerDtos.add(new AnswerDto(model));
        }
        return answerDtos;
    }

    public Collection<AnswerDto> getAnswerDto() {
        return answerDto;
    }
}
