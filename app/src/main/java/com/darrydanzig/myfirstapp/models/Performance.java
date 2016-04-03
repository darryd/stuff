package com.darrydanzig.myfirstapp.models;

import android.annotation.SuppressLint;
import android.util.Log;

import com.darrydanzig.myfirstapp.App;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by Darry on 29/03/16.
 */


/*

  create_table "performances", force: :cascade do |t|
    t.integer  "poet_id"
    t.integer  "round_id"
    t.datetime "created_at",              null: false
    t.datetime "updated_at",              null: false
    t.integer  "minutes"
    t.integer  "seconds"
    t.float    "penalty"
    t.integer  "previous_performance_id"
  end


 */
public class Performance implements Serializable {

    public int id;
    public String poet;
    int roundId;

    @SerializedName("previous_performance_id")
    int previousId;


    public ArrayList<Integer> scores = new ArrayList<>();

    // Scores are stored as an integer (actual score x 10)
    //public Judges<Integer> judges = new Judges<Integer>();
    int minutes;
    int seconds;
    int penalty;
    Performance prev; // From another round (sometimes scores are cumulative)

    // total and subtotal are ints (actual total x 10)
    public float total = 0;
    int subtotal;
    public int rank;
    List<String> tiedWith;

    public Performance() {
        super();
        // Default c-tor for Gson
    }

    public Performance(int i) {
        // Debug c-tor
        id = i;
        poet = "Poet Name";

        for (int i1 = 0; i1 < 5; i1++) {
            Random random = new Random();
            //judges.setScore(i1, random.nextInt(100));
        }
    }

    public void addScore( int score ) {
        scores.add(score);
        Log.d("Performance", "Adding score " + poet + "-->" + score + " total length: " + scores.size());

        total += (float)score / 10;
    }

    @Override
    public String toString() {
        return App.getInstance().getGson().toJson(this, Performance.class);
    }

    @SuppressLint("DefaultLocale")
    public String getTotal() {
        return  String.format("%.1f", total);
    }

    public int getMin() {
        int min = scores.get(0);

        for (Integer score : scores) {
            if( score < min )
                min = score;
        }

        return min;
    }

    public int getMax() {
        int max = scores.get(0);

        for (Integer score : scores) {
            if( score > max )
                max = score;
        }

        return max;
    }
}
