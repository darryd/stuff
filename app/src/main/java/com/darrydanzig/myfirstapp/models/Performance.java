package com.darrydanzig.myfirstapp.models;

import com.darrydanzig.myfirstapp.App;
import com.google.gson.annotations.SerializedName;

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
public class Performance {

    public int id;
    public String poet;
    int roundId;

    @SerializedName("previous_performance_id")
    int previousId;

    // Scores are stored as an integer (actual score x 10)
    public Judges<Integer> judges = new Judges<Integer>();
    int minutes;
    int seconds;
    int penalty;
    Performance prev; // From another round (sometimes scores are cumulative)

    // total and subtotal are ints (actual total x 10)
    int total;
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
            judges.setScore(i1, random.nextInt(100));
        }
    }

    @Override
    public String toString() {
        return App.getInstance().getGson().toJson(this, Performance.class);
    }
}
