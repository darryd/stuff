package com.darrydanzig.myfirstapp.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.darrydanzig.myfirstapp.R;
import com.darrydanzig.myfirstapp.adapter.PerformanceRenderer;
import com.darrydanzig.myfirstapp.adapter.RoundRenderer;
import com.darrydanzig.myfirstapp.models.Competition;
import com.darrydanzig.myfirstapp.models.Performance;
import com.darrydanzig.myfirstapp.models.Round;
import com.pedrogomez.renderers.RendererAdapter;
import com.pedrogomez.renderers.RendererBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CompetitionActivity extends AppCompatActivity {

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
            Competition competition = (Competition) getIntent().getExtras().getSerializable("competition");

            toolbar.setTitle(competition.getTitle());

            ArrayList<Round> rounds = new ArrayList<>();
            Collections.addAll(rounds, competition.rounds );
            Collections.sort(rounds);


            list.setLayoutManager( new LinearLayoutManager( this ) );

            RendererBuilder rendererBuilder = new RendererBuilder()
                    .bind(Round.class, new RoundRenderer())
                    .bind(Performance.class, new PerformanceRenderer());

            RendererAdapter adapter = new RendererAdapter(rendererBuilder);
            list.setAdapter(adapter);

            for (Round round : rounds) {
                adapter.add(round);
                for (Map.Entry<Integer, Performance> integerPerformanceEntry : round.performances.entrySet()) {
                    adapter.add(integerPerformanceEntry.getValue());
                }
            }
        }
    }
}
