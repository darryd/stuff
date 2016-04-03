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
import com.darrydanzig.myfirstapp.models.Competition;
import com.pedrogomez.renderers.Renderer;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompetitionRenderer extends Renderer<Competition> {

    @Bind(R.id.date)
    TextView date;

    @Bind(R.id.title)
    TextView title;


    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View inflatedView = inflater.inflate(R.layout.row_competition, parent, false);
        ButterKnife.bind(this, inflatedView);
        return inflatedView;
    }

    @OnClick(R.id.title)
    public void onTitleClick() {
        Context context = getContext();

        if (context != null) {
            Intent intent = new Intent(getContext(), CompetitionActivity.class);
            Competition content = getContent();
            Log.e("Renderer", "Content: " + content.getTitle() + " Rounds: " + content.rounds.length );


            intent.putExtra("competition", getContent());
            getContext().startActivity(intent);
        }
    }

    @Override
    public void render(List<Object> payloads) {
        Competition competition = getContent();

        date.setText(String.valueOf(competition.getDate()));
        title.setText(competition.getTitle());
    }
}
