package com.darrydanzig.myfirstapp.models;

import com.darrydanzig.myfirstapp.App;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
public class Round implements Serializable, Comparable<Round> {

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

    public HashMap<Integer, Performance> performances = new HashMap<>();
    private int victor;

    @Override
    public String toString() {
        return App.getInstance().getGson().toJson(this, Round.class);
    }

    @Override
    public int compareTo(Round another) {
        if (id < another.id)
            return 1;

        if (id == another.id)
            return 0;

        return -1;
    }

    public void addPerformance(Event event) {
        Performance value = new Performance();
        value.poet = event.poetName;
        value.id = event.performanceId;

        performances.put(event.performanceId, value);
    }

    public void addJudgeScore(Event event) {
        try {
            Performance performance = performances.get(event.performanceId);
            performance.addScore((int) (event.value * 10));
        } catch (Exception ex) {
        }
    }

    public String getVictor() {
        String victor = "";

        Performance currentVictor = null;

        for (Map.Entry<Integer, Performance> integerPerformanceEntry : performances.entrySet()) {
            Performance value = integerPerformanceEntry.getValue();
            if( currentVictor == null || value.total > currentVictor.total ) {
                currentVictor = value;
                victor = currentVictor.poet;
            }
        }

        return victor;
    }
}