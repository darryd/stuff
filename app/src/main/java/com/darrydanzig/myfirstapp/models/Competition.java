package com.darrydanzig.myfirstapp.models;

import android.annotation.SuppressLint;
import android.util.Log;

import com.darrydanzig.myfirstapp.App;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.HashMap;

/**
 * Created by Darry on 29/03/16.
 */

public class Competition implements Serializable {

    public int id;
    public String title;
    public int eventNumber;
    @SerializedName("created_at")
    public String created;
    public int numJudges;

    final public String TAG = "COMPETITION_CLASS";

    @SerializedName("do_not_include_min_and_max_scores")
    public boolean includeMinMax;

    public Round[] rounds = new Round[0];

    HashMap<Integer, Integer> PerformanceIdToRoundId = new HashMap<Integer, Integer>();

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

    public void penalty(int performanceId) {
        Log.e(TAG, "Remove performance: " + performanceId);
    }


    @Override
    public String toString() {
        return App.getInstance().getGson().toJson(this, Competition.class);
    }

    public void merge(FullCompetition fullCompetition) {
        Log.e(TAG, "Merged into slam " + id + ", " + title + " Rounds: " + fullCompetition.rounds.length);

        rounds = fullCompetition.rounds;
        title = fullCompetition.slam.title;


        for (Event event : fullCompetition.events) {
            //Log.e(TAG, "Event: " + event.toString() );

            if (event.event.equals("new_performance")) {
                try {
                    rounds[event.roundNumber].addPerformance(event);
                }catch (Exception ex) {}
            }

            if (event.event.equals("judge")) {

                //  Log.e(TAG, "Adding judge score.");
                for (Round round : rounds) {
                    round.addJudgeScore(event);
                }


            }

        }
    }

    public String getTitle() {
        String result = title;

        int i = result.lastIndexOf(",");
        if (i > 0)
            result = result.substring(0, i);

        return result;
    }

    @SuppressLint("DefaultLocale")
    public String getDate() {
        String[] months = DateFormatSymbols.getInstance().getMonths();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(created);
        } catch (ParseException e) {
            Log.e(TAG, e.getMessage());
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return String.format("%s %d, %d", months[calendar.get(Calendar.MONTH)], calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.YEAR));
    }
}
