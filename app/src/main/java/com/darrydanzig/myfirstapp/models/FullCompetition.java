package com.darrydanzig.myfirstapp.models;

import com.darrydanzig.myfirstapp.App;

/**
 * Created by darry on 01/04/16.
 */
public class FullCompetition {

    public Competition slam;
    public Round[] rounds;
    public Event[] events;


    @Override
    public String toString() {
        return App.getInstance().getGson().toJson(this, FullCompetition.class);
    }
}
