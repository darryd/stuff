package com.darrydanzig.myfirstapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.darrydanzig.myfirstapp.R;
import com.darrydanzig.myfirstapp.activities.CompetitionActivity;
import com.darrydanzig.myfirstapp.activities.PerformanceActivity;
import com.darrydanzig.myfirstapp.models.Competition;
import com.darrydanzig.myfirstapp.models.Round;
import com.pedrogomez.renderers.Renderer;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RoundRenderer extends Renderer<Round> {

    @Bind(R.id.id)
    TextView id;

    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.name)
    TextView name;


    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View inflatedView = inflater.inflate(R.layout.row_round, parent, false);
        ButterKnife.bind(this, inflatedView);
        return inflatedView;
    }

    @OnClick(R.id.title)
    public void onTitleClick() {
        Context context = getContext();


        if (context != null) {
            Intent intent = new Intent(getContext(), PerformanceActivity.class);
            Round content = getContent();
            //Log.e("Renderer", "Content: " + content.getTitle() + " Rounds: " + content.rounds.length );


            intent.putExtra("round", getContent());
            getContext().startActivity(intent);
        }
    }

    @Override
    public void render(List<Object> payloads) {
        Round round = getContent();

        id.setText(String.valueOf(round.id));
        title.setText(round.title);

//        if( round.title.contains("Sac")) {
            name.setVisibility(View.GONE);
//        } else {
//            name.setVisibility(View.VISIBLE);
//            name.setText("#1 - " + round.getVictor());
//        }
    }
}
