package com.darrydanzig.myfirstapp.models;


import android.util.Log;

import com.darrydanzig.myfirstapp.App;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpResponse;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by aur on 01/04/16.
 */
public class DataStore {

    public final String TAG = "DATA-STORE-TAG";

    protected static DataStore instance = null;
    private WebAccess webAccess = new WebAccess();

    /*


    competitions
        - We download this to find out all the competitions
        - is data from BACE_URL/welcome/competitions_json
    fullCompetitions
        - We download this to view the competition (so it naturally contains more infomation)
        - is data from BASE_URL/competitions/show?json&id={id}

     */
    public HashMap<Integer, Competition> competitions = new HashMap<Integer, Competition>();
    public HashMap<Integer, Competition> fullCompetitions = new HashMap<Integer, Competition>();

    /*


    Populate competitions HashMap.


     */
    public class CompetitionsJSONCallBack extends AsyncHttpClient.JSONObjectCallback {
        // Callback is invoked with any exceptions/errors, and the result, if available.
        @Override
        public void onCompleted(Exception e, AsyncHttpResponse response, JSONObject result) {
            if (e != null) {
                e.printStackTrace();
                return;
            }
            Log.e(TAG, "I got a JSONObject: " + result.toString());


            final VanSlam vanSlam = App.getInstance().getGson().fromJson(result.toString(), VanSlam.class);
            Log.e(TAG, "Number of Competitions = " + vanSlam.slams.length);


            int numberOfCompetitions = vanSlam.slams.length;
            for (int i=0; i<numberOfCompetitions; i++) {

                int id = vanSlam.slams[i].id;
                competitions.put(id, vanSlam.slams[i]);
            }
        }
    }

    /*

    Get additional information


    */
    public class CompetitionJSONCallBack extends AsyncHttpClient.JSONObjectCallback {
        // Callback is invoked with any exceptions/errors, and the result, if available.
        @Override
        public void onCompleted(Exception e, AsyncHttpResponse response, JSONObject result) {
            if (e != null) {
                e.printStackTrace();
                return;
            }
            Log.e(TAG, "I got a JSONObject: " + result.toString());


            Competition fullCompetition = App.getInstance().getGson().fromJson(result.toString(), Competition.class);

            fullCompetitions.put(fullCompetition.id, fullCompetition);


            Log.e(TAG, "fullCompetition");
            Log.e(TAG, fullCompetition.toString());

            Log.e(TAG, "Here's a round");
            Log.e(TAG, fullCompetition.rounds[0].toString());
        }
    }



    protected DataStore() {
        updateCompetitions();
    }

    public static DataStore getInstance() {

        if (instance == null)
            instance = new DataStore();

        return instance;
    }

    public void updateCompetitions() {
        webAccess.getCompetitions(new CompetitionsJSONCallBack());
    }

    public void updateCompetition(int id) {
        webAccess.getCompetition(id,new CompetitionJSONCallBack());
    }

    public Competition getCompetition(int id) {
        return null;
    }


}
