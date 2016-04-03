package com.darrydanzig.myfirstapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.darrydanzig.myfirstapp.App;
import com.darrydanzig.myfirstapp.adapter.CompetitionRenderer;
import com.darrydanzig.myfirstapp.models.Competition;
import com.darrydanzig.myfirstapp.network.DataStore;
import com.darrydanzig.myfirstapp.models.Judges;
import com.darrydanzig.myfirstapp.R;
import com.darrydanzig.myfirstapp.models.Round;
import com.darrydanzig.myfirstapp.models.VanSlam;
import com.darrydanzig.myfirstapp.network.WebAccess;
import com.google.gson.Gson;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.AsyncHttpResponse;
import com.koushikdutta.async.http.WebSocket;
import com.pedrogomez.renderers.RendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MyActivity extends AppCompatActivity {


    public class MyJSONCallBack extends AsyncHttpClient.JSONObjectCallback {
        // Callback is invoked with any exceptions/errors, and the result, if available.
        @Override
        public void onCompleted(Exception e, AsyncHttpResponse response, JSONObject result) {
            if (e != null) {
                e.printStackTrace();
                return;
            }
           // Log.e("experiment", "I got a JSONObject: " + result.toString());

            if( DataStore.vanSlam == null ) {
                DataStore.vanSlam =  App.getInstance().getGson().fromJson(result.toString(), VanSlam.class);
                setupCompetitionsAdapter(DataStore.vanSlam);
            } else {
                DataStore.vanSlam.updateSlam(result.toString());
            }

            //final VanSlam competition1 = App.getInstance().getGson().fromJson(result.toString(), VanSlam.class);
            //Log.d(TAG, competition1.toString());

        }
    }

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


        MyJSONCallBack mine = new MyJSONCallBack();
        WebAccess webAccess = new WebAccess();
        webAccess.getCompetitions(mine);

        dataStore.updateCompetition(46);
    }

    public final static String TAG = "DARRY-TAG";


    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.list)
    RecyclerView list;

    // Keeping a handle for future use.
    private Future<WebSocket> socket;

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
        //getCompetitions();

        experiment();
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


                Gson gson = App.getInstance().getGson();

                //Log.e(TAG, "")

                Log.e(TAG, "I got a JSONObject: " + result.toString());

                DataStore.vanSlam = gson.fromJson(result.toString(), VanSlam.class);
                Log.d(TAG, DataStore.vanSlam.toString());

                setupCompetitionsAdapter(DataStore.vanSlam);
            }
        });


    }

    private void setupCompetitionsAdapter(final VanSlam vanSlam) {
        MyActivity.this.runOnUiThread(new Runnable() {
            public void run() {
                ArrayList<Competition> competitions = new ArrayList<Competition>();
                Collections.addAll(competitions, vanSlam.slams);

                RendererBuilder<Round> rendererBuilder = new RendererBuilder<>(new CompetitionRenderer());
                list.setAdapter(new RendererAdapter<>(rendererBuilder, competitions));
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
                Log.e(TAG, "onSocketCompleted:StringCallback - " + s);
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
