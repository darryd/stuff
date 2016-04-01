package com.darrydanzig.myfirstapp.models;


import android.provider.ContactsContract;
import android.util.Log;

import com.darrydanzig.myfirstapp.App;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpResponse;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by darry on 01/04/16.
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

                Log.e(TAG, "=======================================================================");


                Log.e(TAG, vanSlam.slams[i].title);




                Log.e(TAG, "-----------------------------------------------------------------------");
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


            FullCompetition fullCompetition = App.getInstance().getGson().fromJson(result.toString(), FullCompetition.class);

            Log.e(TAG, "****************************************************************************");

            Log.e(TAG, fullCompetition.slam.title);

            int numberOfRounds = fullCompetition.rounds.length;
            for (int i=0; i<numberOfRounds; i++) {
                Log.e(TAG, fullCompetition.rounds[i].title);
            }


            Log.e(TAG, "****************************************************************************");






            //fullCompetitions.put(fullCompetition.id, fullCompetition);

            //DataStore dataStore = DataStore.getInstance();

            /*
            Log.e(TAG, "---------------------------------------------------------------------");
            Log.e(TAG, fullCompetition.title);
            Log.e(TAG, "---------------------------------------------------------------------");
            //displayCompetion(fullCompetition);
            */

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
        webAccess.getCompetition(id, new CompetitionJSONCallBack());
    }

    public Competition getCompetition(int id) {
        return null;
    }

    public void displayCompetion(Competition fullCompetition) {

        Log.e(TAG, "----------------------------------------------------------------------------------");
        Log.e(TAG, fullCompetition.title);
        Log.e(TAG, "----------------------------------------------------------------------------------");
    }

}
