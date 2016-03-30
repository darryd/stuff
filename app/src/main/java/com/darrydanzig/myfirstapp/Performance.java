package com.darrydanzig.myfirstapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoet() {
        return poet;
    }

    public void setPoet(String poet) {
        this.poet = poet;
    }

    public int getRound_id() {
        return round_id;
    }

    public void setRound_id(int round_id) {
        this.round_id = round_id;
    }


    public HashMap<Integer, Integer> getScores() {
        return scores;
    }

    public void setScores(HashMap<Integer, Integer> scores) {
        this.scores = scores;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public Performance getPrev() {
        return prev;
    }

    public void setPrev(Performance prev) {
        this.prev = prev;
    }

    public int getTotal() {
        return total;
    }

    public int getSubtotal() {
        return subtotal;
    }

    private void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    private void setTotal(int total) {
        this.total = total;
    }

    private void calculate() {

    }

    @Override
    public String toString() {
        return "Performance{" +
                "id=" + id +
                ", poet='" + poet + '\'' +
                ", round_id=" + round_id +
                ", scores=" + scores +
                ", minutes=" + minutes +
                ", seconds=" + seconds +
                ", penalty=" + penalty +
                ", prev=" + prev +
                ", total=" + total +
                ", subtotal=" + subtotal +
                '}';
    }

    private int id;
    private String poet;
    private int round_id;

    // Scores are stored as an integer (actual score x 10)
    private HashMap<Integer, Integer> scores = new HashMap<Integer,Integer>();
    private int minutes;
    private int seconds;
    private int penalty;
    private Performance prev;

    // total and subtotal are ints (actual total x 10)
    private int total;
    private int subtotal;
    private int rank;
    private List<String> tied_with;
}
