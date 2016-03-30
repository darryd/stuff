package com.darrydanzig.myfirstapp;

import java.util.List;

/**
 * Created by Darry on 29/03/16.
 */


/*

  create_table "competitions", force: :cascade do |t|
    t.string   "title"
    t.datetime "created_at",                        null: false
    t.datetime "updated_at",                        null: false
    t.integer  "event_number"
    t.boolean  "is_closed"
    t.integer  "num_judges"
    t.boolean  "do_not_include_min_and_max_scores"
    t.integer  "organization_id"
  end

 */


public class Competition {

    private int id;
    private String title;
    private int eventNumber;
    private int numJudges;
    private boolean doNotIncludeMinAndMaxScores;
    private List<Round> rounds;

    @Override
    public String toString() {
        return App.getInstance().getGson().toJson(this, Performance.class);
    }

}
