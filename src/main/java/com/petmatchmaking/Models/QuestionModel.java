package com.petmatchmaking.Models;

import java.util.*;

import jakarta.persistence.*;

/**
 * Class that models the questions
 */
@Entity
@Table(name = "t_Questions")
public class QuestionModel {
    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 900, nullable = false)
    private String question;

    @Column(nullable = false)
    private Double questionOrder = 1.0;

    @Column(nullable = false)
    private boolean manyAnswers = false;

    @OneToMany(mappedBy = "questionModel", cascade = CascadeType.ALL)
    private Collection<AnswerModel> answers;

    /**
     * Default Constructor
     */
    public QuestionModel() {
    }

    /**
     * Parameterized Constructor
     * 
     * @param question question string
     */
    public QuestionModel(String question, boolean manyAnswers) { 
        this.question = question;
        this.answers = new ArrayList<>();
        this.manyAnswers = manyAnswers;
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
     * Method to get question
     * 
     * @return question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Method to get the collection of answers
     * 
     * @return collection of answers
     */
    public Collection<AnswerModel> getAnswers() {
        return answers;
    }

    /**
     * Method to set id   //TO BE DELETED LATERRRR
     * 
     * @param id id for this class
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method to get if the question has multiple answers
     * 
     * @return if the question has multiple answers
     */
    public boolean isManyAnswers() {
        return manyAnswers;
    }

    public Double getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(Double questionOrder) {
        this.questionOrder = questionOrder;
    }

    /**
     * Override method for toString
     */
    @Override
    public String toString() {
        return "QuestionModel [id=" + id + ", question=" + question + "]";
    }
}
