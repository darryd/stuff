package com.darrydanzig.myfirstapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.darrydanzig.myfirstapp.R;
import com.darrydanzig.myfirstapp.activities.PerformanceActivity;
import com.darrydanzig.myfirstapp.models.Performance;
import com.darrydanzig.myfirstapp.models.Round;
import com.darrydanzig.myfirstapp.view.JudgeView;
import com.pedrogomez.renderers.Renderer;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PerformanceRenderer extends Renderer<Performance> {

    @Bind(R.id.id)
    TextView id;

    @Bind(R.id.title)
    TextView title;

    @Bind(R.id.scores_container)
    LinearLayout scoresContainer;

    @Bind(R.id.total)
    TextView total;

    @Bind(R.id.icon)
    ImageView icon;

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        View inflatedView = inflater.inflate(R.layout.row_performance, parent, false);
        ButterKnife.bind(this, inflatedView);
        return inflatedView;
    }

    @Override
    public void render(List<Object> payloads) {
        Performance performance = getContent();

        id.setText(String.valueOf(performance.id));
        title.setText(performance.poet);

        total.setText(performance.getTotal());

        int min = performance.getMin();
        int max = performance.getMax();

        ArrayList<Integer> scores = performance.scores;
        for (int i = 0; i < scores.size(); i++) {
            Integer score = scores.get(i);
            ((JudgeView)scoresContainer.getChildAt(i)).setScore(score, min, max);
        }

        if( performance.total > 45 ) {
            icon.setImageDrawable(getContext().getResources().getDrawable(R.drawable.star_gold));
        } else if ( performance.total > 44 ) {
            icon.setImageDrawable(getContext().getResources().getDrawable(R.drawable.star_silver));
        } else if (performance.total > 43) {
            icon.setImageDrawable(getContext().getResources().getDrawable(R.drawable.star_brozne));
        } else {
            icon.setImageDrawable(null);
        }
    }
}
