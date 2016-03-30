package com.darrydanzig.myfirstapp;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Modifier;

/**
 * Created by Admin on 3/29/2016.
 */
public class App extends Application {

    private static App singleton;
    private Gson gson;

    @Override
    public void onCreate() {
        super.onCreate();

        singleton = this;

        initGson();
    }

    private void initGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithModifiers(Modifier.TRANSIENT);
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);

        gson = gsonBuilder.create();
    }

    public Gson getGson() {
        return gson;
    }

    public static App getInstance() {
        return singleton;
    }
}
