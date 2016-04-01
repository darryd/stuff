package com.darrydanzig.myfirstapp.models;

import android.util.Log;
import com.darrydanzig.myfirstapp.App;
import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.AsyncHttpResponse;
import com.koushikdutta.async.http.WebSocket;

import org.json.JSONObject;

/**
 * Created by Darry on 31/03/16.
 */
public class WebAccess {


    public final String BASE_URL = "https://vanslam.herokuapp.com";
    public final String URL_COMPETITIONS_JSON = BASE_URL + "/welcome/competitions_json";
    public final String URL_COMPETITION_JSON = BASE_URL + "/competition/show?json&id="/*id*/;
    // TODO public final  WHAT_DID_I_MISS_JSON = BASE_URL + "/foobar";

    public final static String TAG = "WEB_ACCESS-TAG";


    // Keeping a handle for future use.
    private Future<WebSocket> socket;


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

    public void getCompetitions(AsyncHttpClient.JSONObjectCallback jsonCallback) {

        AsyncHttpGet url = new AsyncHttpGet(URL_COMPETITIONS_JSON);
        AsyncHttpClient.getDefaultInstance().executeJSONObject(url, jsonCallback);
    }


    public void getCompetition(int id, AsyncHttpClient.JSONObjectCallback jsonObjectCallback) {

        AsyncHttpGet url = new AsyncHttpGet(URL_COMPETITION_JSON + id);
        AsyncHttpClient.getDefaultInstance().executeJSONObject(url, jsonObjectCallback);

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
