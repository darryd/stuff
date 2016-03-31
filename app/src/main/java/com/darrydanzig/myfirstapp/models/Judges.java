package com.darrydanzig.myfirstapp.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Darry on 29/03/16.
 *
 *
 *
 * Scores are stored as an integer (actual score x 10)
 */
public class Judges<judge_key_type> {


    public HashMap<judge_key_type, Integer> scores = new HashMap<judge_key_type, Integer>();
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


    public interface MyComparable {

        boolean compare(Integer a, Integer b);
    }


    MyComparable minCompareable = new MyComparable() {
        @Override
        public boolean compare(Integer a, Integer b) {
            return a < b;
        }
    };

    MyComparable maxCompareable = new MyComparable() {
        @Override
        public boolean compare(Integer a, Integer b) {
            return a > b;
        }
    };


/* ------ Min--------------------------------------------------*/

    private judge_key_type getKeyForMin() {
        return getKeyForMin(scores.keySet());
    }

    private judge_key_type getKeyForMin(judge_key_type ignore) {

        Set<judge_key_type> keys = scores.keySet();
        keys.remove(ignore);

        return getKeyForMin(keys);
    }

    private judge_key_type getKeyForMin(Set<judge_key_type> keys) {

        judge_key_type winnerKey = null;
        int winner = Integer.MAX_VALUE;
        MyComparable comparable = minCompareable;


        for (judge_key_type k : keys) {

            int score = scores.get(k);

            if (comparable.compare(score, winner)) {
                winnerKey = k;
                winner = score;
            }
        }

        return winnerKey;
    }

/* ------ Max--------------------------------------------------*/

    private judge_key_type getKeyForMax() {
        return getKeyForMax(scores.keySet());
    }

    private judge_key_type getKeyForMax(judge_key_type ignore) {

        Set<judge_key_type> keys = scores.keySet();
        keys.remove(ignore);

        return getKeyForMax(keys);
    }

    private judge_key_type getKeyForMax(Set<judge_key_type> keys) {

        judge_key_type winnerKey = null;
        int winner = Integer.MIN_VALUE;
        MyComparable comparable = maxCompareable;


        for (judge_key_type k : keys) {

            int score = scores.get(k);

            if (comparable.compare(score, winner)) {
                winnerKey = k;
                winner = score;
            }
        }

        return winnerKey;
    }


/*-------------------------------------------------------------*/


    private ArrayList<judge_key_type> getMaxMinKeys() {

        ArrayList<judge_key_type> keys = new ArrayList<judge_key_type>();
        judge_key_type minKey, maxKey;

        minKey = getKeyForMin();
        maxKey = getKeyForMax(minKey);

        keys.add(minKey);
        keys.add(maxKey);

        return keys;
    }
}
