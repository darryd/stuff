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

    public HashMap<Integer, Competition> competitions = new HashMap<Integer, Competition>();

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

    public Competition getCompetition(int id) {
        return null;
    }


}
