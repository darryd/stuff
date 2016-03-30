package com.darrydanzig.myfirstapp;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Darry on 29/03/16.
 */


/*

  create_table "rounds", force: :cascade do |t|
    t.integer  "num_poets"
    t.boolean  "is_cumulative"
    t.datetime "created_at",              null: false
    t.datetime "updated_at",              null: false
    t.integer  "competition_id"
    t.string   "title"
    t.integer  "round_number"
    t.boolean  "are_poets_from_previous"
    t.integer  "time_limit"
    t.integer  "num_places"
    t.boolean  "is_extra"
  end

 */
public class Round {

    private int id;
    private int roundNumber;
    private String title;
    private int competitionId;
    private int numPoets;
    private boolean isCumulative;
    private boolean arePoetsFromPrevious;
    private int timeLimit;
    private int graceTime;
    private int numPlaces;

    private HashMap<Integer, Performance> performances;

    @Override
    public String toString() {
        return App.getInstance().getGson().toJson(this, Round.class);
    }

}