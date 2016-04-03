package com.darrydanzig.myfirstapp.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.darrydanzig.myfirstapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class JudgeView extends LinearLayout {

    @Bind(R.id.name)
    TextView name;

    @Bind(R.id.value)
    TextView value;

    public JudgeView(Context context) {
        super(context);
        init();
    }

    public JudgeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.view_judge, this);
        ButterKnife.bind(this);
    }

    public void setScore(float score, int min, int max) {
        float x = score/10;

        value.setText(String.valueOf(x));

//        setScoreColour(score, min, max);
    }

    private void setScoreColour(float score, int min, int max) {
        if( score == min ) {
            value.setTextColor(getResources().getColor(R.color.score_low));
        } else if ( score == max ) {
            value.setTextColor(getResources().getColor(R.color.score_high));
        } else {
            value.setTextColor(getResources().getColor(R.color.white));
        }
    }
}
