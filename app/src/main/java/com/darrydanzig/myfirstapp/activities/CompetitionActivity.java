package com.darrydanzig.myfirstapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.darrydanzig.myfirstapp.R;
import com.darrydanzig.myfirstapp.models.Round;
import com.darrydanzig.myfirstapp.adapter.RoundAdapter;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.AsyncHttpResponse;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Admin on 3/30/2016.
 */
public class CompetitionActivity extends Activity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.list)
    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.bind(this);

        Round[] rounds = new Round[10];
        for (int i = 0; i < rounds.length; i++) {
            rounds[i] = new Round(i);
        }


        list.setLayoutManager( new LinearLayoutManager( this ) );
        RoundAdapter arrayAdapter = new RoundAdapter( CompetitionActivity.this, rounds );
        list.setAdapter( arrayAdapter );


        // HOW DO I GET THE COMPETION CLASS PASSED INTO HERE?
        //Log.e(TAG, "id = " + this.id);
    }

    public final static String TAG = "DARRY-TAG";

    private void getCompetition(int id) {

        AsyncHttpGet url = new AsyncHttpGet(getString(R.string.BASE_URL)+ "/competition/show?json&id=" + id);

        AsyncHttpClient.getDefaultInstance().executeJSONObject(url, new AsyncHttpClient.JSONObjectCallback() {
            @Override
            public void onCompleted(Exception e, AsyncHttpResponse source, JSONObject result) {
                if (e != null) {
                    e.printStackTrace();
                    Log.e(TAG, "Exeception onCompleted. " + e.getMessage());
                    return;
                }


                Log.e(TAG, "Competition json:");
                Log.e(TAG, result.toString());
            }
        });


    }
}
