package com.petmatchmaking.Services;

import java.util.Comparator;

import com.petmatchmaking.Models.ScoreboardModel;

public class ScoreboardCompare implements Comparator<ScoreboardModel> {

    @Override
    public int compare(ScoreboardModel o1, ScoreboardModel o2) {
        if (o1.getScore() == o2.getScore())
            return 0;
        else if (o1.getScore() < o2.getScore())
            return 1;
        else
            return -1;
    }
}
