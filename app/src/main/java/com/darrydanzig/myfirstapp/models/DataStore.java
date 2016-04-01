package com.darrydanzig.myfirstapp.models;


import java.util.HashMap;

/**
 * Created by aur on 01/04/16.
 */
public class DataStore {

    protected static DataStore instance = null;
    private WebAccess webAccess = new WebAccess();

    public HashMap<Integer, Competition> competitions = new HashMap<Integer, Competition>();


    protected DataStore() {
        updateCompetitions();
    }

    public static DataStore getInstance() {

        if (instance == null)
            instance = new DataStore();

        return instance;
    }

    public void updateCompetitions() {

    }

    public Competition getCompetition(int id) {
        return null;
    }


}
