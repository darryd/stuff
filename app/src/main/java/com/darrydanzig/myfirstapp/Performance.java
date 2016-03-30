package com.darrydanzig.myfirstapp;

import java.util.HashMap;
import java.util.List;

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

    int id;
    String poet;
    int roundId;

    // Scores are stored as an integer (actual score x 10)
    HashMap<Integer, Integer> scores = new HashMap<Integer, Integer>();
    int minutes;
    int seconds;
    int penalty;
    Performance prev;

    // total and subtotal are ints (actual total x 10)
    int total;
    int subtotal;
    int rank;
    List<String> tiedWith;

    @Override
    public String toString() {
        return App.getInstance().getGson().toJson(this, Performance.class);
    }


}
