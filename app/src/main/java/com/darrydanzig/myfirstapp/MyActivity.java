package com.darrydanzig.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.koushikdutta.async.http.AsyncHttpClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyActivity extends AppCompatActivity {


    public final static String EXTRA_MESSAGE = "com.darrydanzig.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final Intent intent = new Intent(this, DisplayMessageActivity.class);

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
                                System.out.println(nextLine);
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

                } catch (java.io.IOException e) {
                }
                catch (Exception e) {

                    message = e.toString();
                }

                intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);


            }
        }).start();
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {


        /*
        // url is the URL to download.
        AsyncHttpClient.getDefaultInstance().getString(url, new AsyncHttpClient.StringCallback() {
            // Callback is invoked with any exceptions/errors, and the result, if available.
            @Override
            public void onCompleted(Exception e, AsyncHttpResponse response, String result) {
                if (e != null) {
                    e.printStackTrace();
                    return;
                }
                System.out.println("I got a string: " + result);
            }
        });

    */
        AsyncHttpClient.getDefaultInstance();

    /*

    */


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
