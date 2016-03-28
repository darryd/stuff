package com.darrydanzig.myfirstapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.koushikdutta.async.ByteBufferList;
import com.koushikdutta.async.DataEmitter;
import com.koushikdutta.async.callback.DataCallback;
import com.koushikdutta.async.http.AsyncHttpClient;
import com.koushikdutta.async.http.AsyncHttpGet;
import com.koushikdutta.async.http.AsyncHttpRequest;
import com.koushikdutta.async.http.AsyncHttpResponse;
import com.koushikdutta.async.http.WebSocket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyActivity extends AppCompatActivity {


    public final static String EXTRA_MESSAGE = "com.darrydanzig.myfirstapp.MESSAGE";

    public final static String TAG = "DARRY-TAG";


    @Bind(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        // Binding all the views.
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        final Intent intent = new Intent(this, DisplayMessageActivity.class);

        //testThread(intent);
    }

    private void testThread(final Intent intent) {
        new Thread(new Runnable() {

            @Override
            public void run() {


                EditText editText = (EditText) findViewById(R.id.edit_message);
                String message = ""; // = editText.getText().toString();

                try {
                    URL url = new URL("https://vanslamlive.ca");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                    try {
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());


                        StringBuilder stringBuilder = new StringBuilder();

                        try {

                            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                            String nextLine = "";

                            while ((nextLine = reader.readLine()) != null) {
                                stringBuilder.append(nextLine);
                                //System.out.println(nextLine);
                            }

                            message = stringBuilder.toString();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }




                    }
                    catch (Exception e) {
                        message = e.toString();
                    }
                    finally {
                        urlConnection.disconnect();
                        message += "horse";
                    }

                } catch (IOException e) {
                }
                catch (Exception e) {

                    message = e.toString();
                }

                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);


            }
        }).start();
    }


    @OnClick(R.id.fab)
    public void onFabClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


    /** Called when the user clicks the Send button */
    @OnClick(R.id.send)
    public void sendMessage() {

        AsyncHttpGet asyncHttpGet = new AsyncHttpGet("https://vanslam.herokuapp.com");

        // url is the URL to download.
        AsyncHttpClient.getDefaultInstance().executeString(asyncHttpGet, new AsyncHttpClient.StringCallback() {
            // Callback is invoked with any exceptions/errors, and the result, if available.
            @Override
            public void onCompleted(Exception e, AsyncHttpResponse response, String result) {
                if (e != null) {
                    e.printStackTrace();
                    return;
                }
                Log.d(TAG, "I got a string: " + result);
            }
        });

        AsyncHttpClient.getDefaultInstance().websocket(asyncHttpGet, "my-protocol", new AsyncHttpClient.WebSocketConnectCallback() {
            @Override
            public void onCompleted(Exception ex, WebSocket webSocket) {
                if (ex != null) {
                    ex.printStackTrace();
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
