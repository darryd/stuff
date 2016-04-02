package com.darrydanzig.myfirstapp.models;

import android.util.Log;

import com.darrydanzig.myfirstapp.App;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Darry on 29/03/16.
 */

public class Competition {

    public int id;
    public String title;
    int eventNumber;
    int numJudges;

    final public String TAG = "COMPETITION_CLASS";

    @SerializedName("do_not_include_min_and_max_scores")
    boolean includeMinMax;

    Round[] rounds;

    public Competition() {
        super();
        // Default c-tor for gson
    }


    public void newPerformance(int performanceId, int prevPerformanceId, String poetName, int roundNumber) {

        //TODO empty for now

        Log.e(TAG, poetName);

    }

    public void judge(int performanceId, String judgeName, float value) {
        Log.e(TAG, judgeName + ": " + value);
    }

    public void set_time(int performanceId, int minutes, int seconds) {
        Log.e(TAG, "Time: " + minutes + ":" + seconds);
    }

    public void penalty(int performanceId, float value) {
        Log.e(TAG, "Penalty: " + value);
    }

    @Override
    public String toString() {
        return App.getInstance().getGson().toJson(this, Competition.class);
    }
}
