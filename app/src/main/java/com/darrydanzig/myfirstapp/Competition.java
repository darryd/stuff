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
    private int event_number;
    private int num_judges;
    private boolean do_not_include_min_and_max_scores;
    private List<Round> rounds;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getEvent_number() {
        return event_number;
    }

    public void setEvent_number(int event_number) {
        this.event_number = event_number;
    }

    public int getNum_judges() {
        return num_judges;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", event_number=" + event_number +
                ", num_judges=" + num_judges +
                ", do_not_include_min_and_max_scores=" + do_not_include_min_and_max_scores +
                ", rounds=" + rounds +
                '}';
    }

    public void setNum_judges(int num_judges) {
        this.num_judges = num_judges;
    }

    public boolean isDo_not_include_min_and_max_scores() {
        return do_not_include_min_and_max_scores;
    }

    public void setDo_not_include_min_and_max_scores(boolean do_not_include_min_and_max_scores) {
        this.do_not_include_min_and_max_scores = do_not_include_min_and_max_scores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
