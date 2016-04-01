package com.darrydanzig.myfirstapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.darrydanzig.myfirstapp.models.Competition;
import com.darrydanzig.myfirstapp.activities.CompetitionActivity;
import com.darrydanzig.myfirstapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Admin on 3/30/2016.
 */
public class CompetitionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context mContext;
    private final Competition[] mCompetitions;

    public CompetitionAdapter(Context context, Competition[] competitions ) {

        this.mContext = context;
        this.mCompetitions = competitions;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_competition, parent, false);
        return new CompetitionHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((CompetitionHolder)holder).bindCompetition(mCompetitions[position]);
    }

    @Override
    public int getItemCount() {
        return mCompetitions.length;
    }

    public class CompetitionHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Competition mCompetition;

        @Bind(R.id.id)
        TextView id;
        @Bind(R.id.title)
        TextView title;

        public CompetitionHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bindCompetition(Competition competition) {
            mCompetition = competition;

            id.setText(String.valueOf(competition.id));
            title.setText(competition.title);
        }


        @Override
        public void onClick(View v) {
            mContext.startActivity(new Intent(mContext, CompetitionActivity.class));
        }
    }
}
