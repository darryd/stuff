package com.darrydanzig.myfirstapp;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.future.Future;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.WebSocket;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyActivity extends AppCompatActivity {


    public final static String EXTRA_MESSAGE = "com.darrydanzig.myfirstapp.MESSAGE";

    public final static String TAG = "DARRY-TAG";


    @Bind(R.id.toolbar)
    Toolbar toolbar;

    // Keeping a handle for future use.
    private Future<WebSocket> socket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        // Binding all the views.
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        // Setup socket.
        initSocket();
    }

    @OnClick(R.id.fab)
    public void onFabClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


    /** Called when the user clicks the Send button */
    @OnClick(R.id.send)
    public void sendMessage() {


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
