package com.darrydanzig.myfirstapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.darrydanzig.myfirstapp.models.Performance;
import com.darrydanzig.myfirstapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Admin on 3/30/2016.
 */
public class PerformanceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private final Performance[] mRounds;

    public PerformanceAdapter(Context context, Performance[] competitions ) {

        this.mContext = context;
        this.mRounds = competitions;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_performance, parent, false);
        return new PerformanceHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PerformanceHolder)holder).bindCompetition(mRounds[position]);
    }

    @Override
    public int getItemCount() {
        return mRounds.length;
    }

    public class PerformanceHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Performance mRound;

        @Bind(R.id.id)
        TextView id;
        @Bind(R.id.title)
        TextView title;

        @Bind(R.id.scores_container)
        LinearLayout scoresContainer;

        public PerformanceHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindCompetition(Performance round) {
            mRound = round;

            id.setText(String.valueOf(round.id));
            title.setText(round.poet);

            for (int i = 0; i < 5; i++) {
                ((TextView)scoresContainer.getChildAt(i)).setText(String.valueOf(round.judges.scores.get(i)));
            }
        }


        @Override
        public void onClick(View v) {

        }
    }
}
