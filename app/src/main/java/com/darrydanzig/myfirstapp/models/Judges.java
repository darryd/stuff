package com.darrydanzig.myfirstapp.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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


    private judge_key_type getKeyForMinOrMax(boolean isForMin) {





        return getKeyForMinOrMax(scores.keySet(), isForMin);
    }

    private judge_key_type getKeyForMinOrMax(judge_key_type ignore, boolean isForMin) {

        Set<judge_key_type> keys = scores.keySet();
        keys.remove(ignore);

        return getKeyForMinOrMax(keys, isForMin);
    }


    public interface MyComparable {

        boolean compare(Integer a, Integer b);
    }

    public static MyComparable getMyCompareable(boolean isForMin) {

        MyComparable comparable;

        if (isForMin) {

            // Min
            comparable = new MyComparable() {
                @Override
                public boolean compare(Integer a, Integer b) {
                    return a < b;
                }
            };

        } else {

            // Max
            comparable = new MyComparable() {
                @Override
                public boolean compare(Integer a, Integer b) {
                    return a > b;
                }
            };
        }

        return comparable;
    }

    private judge_key_type getKeyForMinOrMax(Set<judge_key_type> keys, boolean isForMin) {

        judge_key_type winnerKey = null;
        int winner = isForMin ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        MyComparable comparable = getMyCompareable(isForMin);


        for (judge_key_type k : keys) {

            int score = scores.get(k);

            if (comparable.compare(score, winner)) {
                winnerKey = k;
                winner = score;
            }
        }

        return winnerKey;
    }

    private ArrayList<judge_key_type> getMaxMinKeys() {

        ArrayList<judge_key_type> min_max_keys = new ArrayList<judge_key_type>();

        if (do_not_include_max_min_scores) {

            // find the min and max

        }

        return min_max_keys;
    }
}
