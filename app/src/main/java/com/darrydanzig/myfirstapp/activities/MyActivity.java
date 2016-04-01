package com.darrydanzig.myfirstapp.activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.darrydanzig.myfirstapp.App;
import com.darrydanzig.myfirstapp.models.DataStore;
import com.darrydanzig.myfirstapp.models.Judges;
import com.darrydanzig.myfirstapp.models.Performance;
import com.darrydanzig.myfirstapp.R;
import com.darrydanzig.myfirstapp.models.VanSlam;
import com.darrydanzig.myfirstapp.adapter.CompetitionAdapter;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.AsyncHttpResponse;
import com.koushikdutta.async.http.WebSocket;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyActivity extends AppCompatActivity {


    public void experiment() {

        Judges<String> judges = new Judges<String>();

        DataStore dataStore = DataStore.getInstance();

        Log.e(TAG, "Comparables");

        /*
        Judges.MyComparable comparableMin = Judges.getMyCompareable(true);
        Judges.MyComparable comparableMax = Judges.getMyCompareable(false);

        Log.e(TAG, "1 < 2 : " + comparableMin.compare(1, 2));
        Log.e(TAG, "2 > 1 : " + comparableMax.compare(2, 1));

        Log.e(TAG, "1 > 2 : " + comparableMax.compare(1, 2));
        Log.e(TAG, "2 < 1 : " + comparableMin.compare(2, 1));
        */

        for (int i= 0; i<5; i++) {

            Random random = new Random();

            int value = random.nextInt(5);

            Log.e(TAG, "" + value);
            judges.setScore("" + i, value);
        }


        Log.e(TAG, "Total (with max and min) = " + judges.getTotal());
        judges.doNotIncludeMaxMinScores = true;
        Log.e(TAG, "Total (without max and min) = " + judges.getTotal());



        //getCompetition(2);

    }

    public final static String TAG = "DARRY-TAG";


    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.list)
    RecyclerView list;

    // Keeping a handle for future use.
    private Future<WebSocket> socket;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        // Binding all the views.
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);


        list.setLayoutManager(new LinearLayoutManager(this));




        // Setup socket.
        initSocket();
        getCompetitions();

        experiment();

        Performance performance = new Performance();
        Log.e(TAG, performance.toString());

    }

    private void initSocket() {
        AsyncHttpClient instance = AsyncHttpClient.getDefaultInstance();
        AsyncHttpGet get = new AsyncHttpGet(getString(R.string.BASE_URL));

        socket = instance.websocket(get, "my-protocol", new AsyncHttpClient.WebSocketConnectCallback() {
            @Override
            public void onCompleted(Exception ex, WebSocket webSocket) {
                onSocketCompleted(ex, webSocket);
            }
        });
    }

    private void getCompetitions() {
        /*
        AsyncHttpClient instance = AsyncHttpClient.getDefaultInstance();
        AsyncHttpGet get = new AsyncHttpGet(getString(R.string.URL_COMPETITIONS_JSON));

        instance.executeJSONObject()

        instance.executeJSONObject(get,(e, response, result) -> {
            onJSONCompleted(e, response, result);});
        */

        // url is the URL to download.
        AsyncHttpGet url = new AsyncHttpGet(getString(R.string.URL_COMPETITIONS_JSON));

        AsyncHttpClient.getDefaultInstance().executeJSONObject(url, new AsyncHttpClient.JSONObjectCallback() {
            // Callback is invoked with any exceptions/errors, and the result, if available.
            @Override
            public void onCompleted(Exception e, AsyncHttpResponse response, JSONObject result) {
                if (e != null) {
                    e.printStackTrace();
                    return;
                }
                Log.e(TAG, "I got a JSONObject: " + result.toString());

                final VanSlam competition1 = App.getInstance().getGson().fromJson(result.toString(), VanSlam.class);
                Log.d(TAG, competition1.toString());

                MyActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        Log.d("UI thread", "I am the UI thread");
                        CompetitionAdapter arrayAdapter = new CompetitionAdapter( MyActivity.this, competition1.slams );
                        list.setAdapter( arrayAdapter );
                    }
                });


            }
        });


    }



    private void onSocketCompleted(Exception ex, WebSocket webSocket) {
        if (ex != null) {
            ex.printStackTrace();
            Log.e(TAG, "Exeception onCompleted. " + ex.getMessage());
            return;
        }
        webSocket.send("a string");
        webSocket.send(new byte[10]);
        webSocket.setStringCallback(new WebSocket.StringCallback() {
            @Override
            public void onStringAvailable(String s) {
                Log.e(TAG, s);
            }
        });
        webSocket.setDataCallback(new DataCallback() {
            public void onDataAvailable(DataEmitter emitter, ByteBufferList byteBufferList) {
                Log.d(TAG, "I got some bytes!");
                // note that this data has been read
                byteBufferList.recycle();
            }
        });

    }
}
