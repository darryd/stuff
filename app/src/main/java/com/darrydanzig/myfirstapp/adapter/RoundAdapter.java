package com.darrydanzig.myfirstapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.darrydanzig.myfirstapp.activities.PerformanceActivity;
import com.darrydanzig.myfirstapp.R;
import com.darrydanzig.myfirstapp.models.Round;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Admin on 3/30/2016.
 */
public class RoundAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private final Round[] mRounds;

    public RoundAdapter(Context context, Round[] competitions ) {

        this.mContext = context;
        this.mRounds = competitions;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_competition, parent, false);
        return new RoundHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((RoundHolder)holder).bindCompetition(mRounds[position]);
    }

    @Override
    public int getItemCount() {
        return mRounds.length;
    }

    public class RoundHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Round mRound;

        @Bind(R.id.id)
        TextView id;
        @Bind(R.id.title)
        TextView title;

        public RoundHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindCompetition(Round round) {
            mRound = round;

            id.setText(String.valueOf(round.id));
            title.setText(round.title);
        }


        @Override
        public void onClick(View v) {
            mContext.startActivity(new Intent(mContext, PerformanceActivity.class));
        }
    }
}
