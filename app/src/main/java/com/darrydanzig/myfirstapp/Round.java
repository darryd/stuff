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
    private int round_number;
    private String title;
    private int competition_id;
    private int num_poets;
    private boolean is_cumulative;
    private boolean are_poets_from_previous;
    private int time_limit;
    private int grace_time;
    private int num_places;

    private HashMap<Integer, Performance> performances;

    @Override
    public String toString() {
        return "Round{" +
                "id=" + id +
                ", round_number=" + round_number +
                ", title='" + title + '\'' +
                ", competition_id=" + competition_id +
                ", num_poets=" + num_poets +
                ", is_cumulative=" + is_cumulative +
                ", are_poets_from_previous=" + are_poets_from_previous +
                ", time_limit=" + time_limit +
                ", grace_time=" + grace_time +
                ", num_places=" + num_places +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRound_number() {
        return round_number;
    }

    public void setRound_number(int round_number) {
        this.round_number = round_number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCompetition_id() {
        return competition_id;
    }

    public void setCompetition_id(int competition_id) {
        this.competition_id = competition_id;
    }

    public int getNum_poets() {
        return num_poets;
    }

    public void setNum_poets(int num_poets) {
        this.num_poets = num_poets;
    }

    public boolean is_cumulative() {
        return is_cumulative;
    }

    public void setIs_cumulative(boolean is_cumulative) {
        this.is_cumulative = is_cumulative;
    }

    public boolean isAre_poets_from_previous() {
        return are_poets_from_previous;
    }

    public void setAre_poets_from_previous(boolean are_poets_from_previous) {
        this.are_poets_from_previous = are_poets_from_previous;
    }

    public int getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(int time_limit) {
        this.time_limit = time_limit;
    }

    public int getGrace_time() {
        return grace_time;
    }

    public void setGrace_time(int grace_time) {
        this.grace_time = grace_time;
    }

    public int getNum_places() {
        return num_places;
    }

    public void setNum_places(int num_places) {
        this.num_places = num_places;
    }
}
