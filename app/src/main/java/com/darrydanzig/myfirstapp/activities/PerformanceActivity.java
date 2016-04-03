package com.darrydanzig.myfirstapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.darrydanzig.myfirstapp.adapter.PerformanceRenderer;
import com.darrydanzig.myfirstapp.models.Performance;
import com.darrydanzig.myfirstapp.R;
import com.darrydanzig.myfirstapp.models.Round;
import com.pedrogomez.renderers.RendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PerformanceActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.list)
    RecyclerView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.bind(this);

        if( getIntent().getExtras() != null ) {
            Round round = (Round) getIntent().getExtras().getSerializable("round");
            ArrayList<Performance> performance = new ArrayList<>();

            for (Map.Entry<Integer, Performance> integerPerformanceEntry : round.performances.entrySet()) {
                performance.add(integerPerformanceEntry.getValue());
            }

            list.setLayoutManager( new LinearLayoutManager( this ) );

            RendererBuilder<Round> rendererBuilder = new RendererBuilder<>(new PerformanceRenderer());
            list.setAdapter(new RendererAdapter<>(rendererBuilder, performance));
        }
    }
}
