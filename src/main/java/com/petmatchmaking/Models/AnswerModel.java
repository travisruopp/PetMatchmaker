package com.petmatchmaking.Models;



import jakarta.persistence.*;


/**
 * Model class for Answers
 */
@Entity
@Table(name = "t_Answers")
public class AnswerModel {

    @Id
    @GeneratedValue()
    private Long id;
    
    
    @Column()
    private String answer;

    @ManyToOne
    private QuestionModel questionModel;
       

    public AnswerModel() {
    }

    /**
     * Parameterized Constructor
     * 
     * @param id
     * @param answer
     */
    public AnswerModel(String answer) {
        this.answer = answer;
    }

    /**
     * Getters and Setters
     */

    /**
     * Method to get Id
     * 
     * @return answer id
     */
    public Long getId() {
        return id;
    }

    /**
     * Method to Set Id  //TO BE DELETED LATERRRR
     * 
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Method to Get Answer
     * 
     * @return  answer 
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Method to set the question model
     * 
     * @param questionModel question model
     */
    public void  setQuestionModel(QuestionModel questionModel){
        this.questionModel = questionModel;
    }

    @Override
    public String toString() {
        return "AnswerModel [Id=" + id + ", answer=" + answer + "]";
    }

}
