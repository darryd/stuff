package com.darrydanzig.myfirstapp.models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Darry on 29/03/16.
 */
public class Judges<judge_key_type> {

    private HashMap<judge_key_type, Integer> scores = new HashMap<judge_key_type, Integer>();
    private boolean do_not_include_max_min_scores;

    public void setScore(judge_key_type judgeKey, int score) {
        scores.put(judgeKey, score);
    }

    public int getTotal() {

        int total = 0;

        return total;
    }

    private ArrayList<judge_key_type> getMaxMinKeys() {

        ArrayList<judge_key_type> min_max_keys = new ArrayList<judge_key_type>();

        if (do_not_include_max_min_scores) {

            // find the min and max

        }

        return min_max_keys;
    }
}
