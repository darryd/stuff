package com.darrydanzig.myfirstapp.models;

import com.darrydanzig.myfirstapp.App;

import java.util.HashMap;

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

    public int id;
    public int roundNumber;
    public String title;
    int competitionId;
    int numPoets;
    boolean isCumulative;
    boolean arePoetsFromPrevious;
    int timeLimit;
    int graceTime;
    int numPlaces;

    private HashMap<Integer, Performance> performances;

    public Round() {
        super();


    }

    public Round(int i) {
        performances = new HashMap<>();

        for (int t = 0; t < i; t++) {
            performances.put(i, new Performance(i));
        }

        id = i;
        title = "Round " + ( i + 1 );
    }

    @Override
    public String toString() {
        return App.getInstance().getGson().toJson(this, Round.class);
    }

}