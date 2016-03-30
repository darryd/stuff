package com.darrydanzig.myfirstapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.darrydanzig.myfirstapp.R;
import com.darrydanzig.myfirstapp.models.Round;
import com.darrydanzig.myfirstapp.adapter.RoundAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Admin on 3/30/2016.
 */
public class CompletitionActivity extends Activity {

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
        RoundAdapter arrayAdapter = new RoundAdapter( CompletitionActivity.this, rounds );
        list.setAdapter( arrayAdapter );
    }
}
