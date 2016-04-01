package com.darrydanzig.myfirstapp.models;

import com.darrydanzig.myfirstapp.App;
import com.google.gson.annotations.SerializedName;

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

    public int id;
    public String title;
    int eventNumber;
    int numJudges;

    @SerializedName("do_not_include_min_and_max_scores")
    boolean includeMinMax;

    Round[] rounds;

    public Competition() {
        super();
        // Default c-tor for gson
    }


    @Override
    public String toString() {
        return App.getInstance().getGson().toJson(this, Competition.class);
    }
}
