package com.petmatchmaking.Services;

import java.util.Comparator;

import com.petmatchmaking.Models.QuestionModel;

public class QuestionComparable implements Comparator<QuestionModel> {

    @Override
    public int compare(QuestionModel s1, QuestionModel s2) {
        if (s1.getQuestionOrder() == s2.getQuestionOrder())
            return 0;
        else if (s1.getQuestionOrder() > s2.getQuestionOrder())
            return 1;
        else
            return -1;
    }

}
