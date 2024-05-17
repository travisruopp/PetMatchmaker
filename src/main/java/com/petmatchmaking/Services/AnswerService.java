package com.petmatchmaking.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.petmatchmaking.Models.AnswerModel;
import com.petmatchmaking.Repositories.AnswerRepository;

import jakarta.annotation.Resource;

/** Class that verifies the repository operations */
@Service
public class AnswerService {

    @Resource
    private AnswerRepository answerRepository;

    public AnswerService() {
    }

    /**
     * parametrized constructor
     * 
     * @param answersRepository
     */
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    /**
     * Method to return all answers
     * 
     * @return all answers 
     */
    public Iterable<AnswerModel> findAll() {
        Iterable<AnswerModel> answers = new ArrayList<>();
        try {
            answers = answerRepository.findAll();
        } catch (Exception ex) {
            throw ex;
        }
        return answers;
    }

    /**
     * Method to find answer by the id
     * 
     * @param id
     * @return a specific answer based of the id given
     */

    public AnswerModel findById(long id) {
        AnswerModel answer = null;
        try {
            Optional<AnswerModel> optAnswer = answerRepository.findById(id);
            if (optAnswer.isPresent()) {
                answer = optAnswer.get();
            }
        } catch (Exception ex) {
            throw ex;
        }
        return answer;
    }

    /**
     * Method to delete an answer
     * 
     * @param id
     * @return a boolean if the answer has been deleted 
     */
    public boolean deleteAnswer(Long id) {
        boolean result = false;
        try {
            answerRepository.deleteById(id);
            Optional<AnswerModel> optAnswer = answerRepository.findById(id);
            if (!optAnswer.isPresent()) {
                result = true;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return result;
    }

    /**
     * Method to save an Answer
     * 
     * @param answer
     * @return saved answer
     */
    public AnswerModel saveAnswer(AnswerModel answer) {
        try {
            answer = answerRepository.save(answer);
        } catch (Exception ex) {
            throw ex;
        }
        return answer;
    }

}
