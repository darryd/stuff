package com.darrydanzig.myfirstapp.models;

import android.util.Log;

import com.darrydanzig.myfirstapp.App;

/**
 * Created by Admin on 3/30/2016.
 */
public class VanSlam {

    public Competition[] slams;

    public void updateSlam(String result) {
        slams[2] = App.getInstance().getGson().fromJson(result, Competition.class);
    }

    public void merge(FullCompetition fullCompetition) {
        Log.e("VanSlam", "Events Size: "+ fullCompetition.events.length);
        for (Event event : fullCompetition.events) {

        }

        int id = fullCompetition.slam.id;
        for (Competition slam : slams) {
            if( slam.id == id ) {
                slam.merge(fullCompetition);
            }
        }
    }
}
