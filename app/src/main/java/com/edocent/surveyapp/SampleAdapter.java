package com.edocent.surveyapp;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ankursrivastava on 5/4/16.
 */
public class SampleAdapter extends RecyclerView.Adapter {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        CardView mCardView;
        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        CardView cardView = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_sample,parent,false);

        return new ViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
