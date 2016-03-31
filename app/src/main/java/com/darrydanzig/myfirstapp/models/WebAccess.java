package com.darrydanzig.myfirstapp.models;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.darrydanzig.myfirstapp.App;
import com.darrydanzig.myfirstapp.R;
import com.darrydanzig.myfirstapp.adapter.CompetitionAdapter;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.AsyncHttpResponse;
import com.koushikdutta.async.http.WebSocket;

import org.json.JSONObject;

import java.util.Random;

/*
import butterknife.Bind;
import butterknife.ButterKnife;
*/
/**
 * Created by Darry on 31/03/16.
 */
public class WebAccess {


    private static WebAccess instance = null;

    public final String BASE_URL = "https://vanslam.herokuapp.com";
    public final String URL_COMPETITIONS_JSON = BASE_URL + "/welcome/competitions_json";
    public final String URL_COMPETITION_JSON = BASE_URL + "/competition/show?json&id="/*id*/;
    // TODO public final  WHAT_DID_I_MISS_JSON = BASE_URL + "/foobar";


    protected WebAccess() {

    }

    public static WebAccess getInstance() {

        if (instance == null)
            instance = new WebAccess();

        return instance;
    }

    public void experiment() {

        Judges<String> judges = new Judges<String>();






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

    public final static String TAG = "WEB_ACCESS-TAG";


    // Keeping a handle for future use.
    private Future<WebSocket> socket;

    protected void create() {


        // Setup socket.
        initSocket();
        getCompetitions();

        experiment();

        Performance performance = new Performance();
        Log.e(TAG, performance.toString());

    }

    private void initSocket() {
        AsyncHttpClient instance = AsyncHttpClient.getDefaultInstance();
        AsyncHttpGet get = new AsyncHttpGet(BASE_URL);

        socket = instance.websocket(get, "my-protocol", new AsyncHttpClient.WebSocketConnectCallback() {
            @Override
            public void onCompleted(Exception ex, WebSocket webSocket) {
                onSocketCompleted(ex, webSocket);
            }
        });
    }

    private void getCompetitions() {

        // url is the URL to download.
        AsyncHttpGet url = new AsyncHttpGet(URL_COMPETITIONS_JSON);

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

            }
        });
    }


    private void getCompetition(int id) {

        AsyncHttpGet url = new AsyncHttpGet(URL_COMPETITION_JSON + id);

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

    // TODO
    private void whatDidIMiss() {

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
