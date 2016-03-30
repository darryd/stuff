package com.darrydanzig.myfirstapp.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Darry on 29/03/16.
 */
public class Judges<judge_key_type> {


    private HashMap<judge_key_type, Integer> scores = new HashMap<judge_key_type, Integer>();
    public boolean doNotIncludeMaxMinScores;

    public void setScore(judge_key_type judgeKey, int score) {
        scores.put(judgeKey, score);
    }

    public int getTotal() {

        int total = 0;
        ArrayList<judge_key_type> ignoreKeys = new ArrayList<judge_key_type>();

        if (doNotIncludeMaxMinScores)
            ignoreKeys = getMaxMinKeys();


        Set<judge_key_type> keys = scores.keySet();
        for (judge_key_type ignoreKey : ignoreKeys){
            keys.remove(ignoreKey);
        }

        for (judge_key_type k : keys) total += scores.get(k);

        return total;
    }


    private judge_key_type getKeyForMinOrMax(boolean isForMin) {
        return getKeyForMinOrMax(isForMin, scores.keySet());
    }

    private judge_key_type getKeyForMinOrMax(boolean isForMin, judge_key_type ignore) {

        Set<judge_key_type> keys = scores.keySet();
        keys.remove(ignore);

        return getKeyForMinOrMax(isForMin, keys);
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

    public judge_key_type getKeyForMinOrMax(boolean isForMin, Set<judge_key_type> keys) {

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

        ArrayList<judge_key_type> keys = new ArrayList<judge_key_type>();
        judge_key_type minKey, maxKey;

        minKey = getKeyForMinOrMax(true);
        maxKey = getKeyForMinOrMax(false, minKey);

        keys.add(minKey);
        keys.add(maxKey);

        return keys;
    }
}
