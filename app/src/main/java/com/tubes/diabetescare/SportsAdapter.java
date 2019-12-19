package com.tubes.diabetescare;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SportsAdapter extends RecyclerView.Adapter<SportsAdapter.ViewHolder> {

    // Member variables.
    private ArrayList<Sport> mSportsData;
    private Context mContext;
    private ImageView mSportsImage;
    private OnCardListener mOnCardListener;

    SportsAdapter(Context context, ArrayList<Sport> sportsData, OnCardListener onCardListener) {
        this.mSportsData = sportsData;
        this.mContext = context;
        this.mOnCardListener = onCardListener;
    }

    @Override
    public SportsAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.card_list, parent, false), mOnCardListener);
    }

    @Override
    public void onBindViewHolder(SportsAdapter.ViewHolder holder,
                                 int position) {
        // Get current sport.
        Sport currentSport = mSportsData.get(position);

        // Populate the TextView with data.
        holder.bindTo(currentSport);
    }

    @Override
    public int getItemCount() {
        return mSportsData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mSubTitleText;
        OnCardListener onCardListener;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The RootView of the list_item.xml layout file.
         */
        ViewHolder(View itemView, OnCardListener onCardListener) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.title);
            mSubTitleText = itemView.findViewById(R.id.subTitle);
            mSportsImage = itemView.findViewById(R.id.sportsImage);

            this.onCardListener = onCardListener;

            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);
        }

        void bindTo(Sport currentSport) {
            // Populate the TextView with data.
            mTitleText.setText(currentSport.getTitle());
            mSubTitleText.setText(currentSport.getSubTitle());
            Glide.with(mContext).load(currentSport.getImageResource()).into(mSportsImage);
        }

        @Override
        public void onClick(View view) {
            onCardListener.onCardClick(getAdapterPosition());
        }
    }

    public interface OnCardListener {
        void onCardClick(int position);
    }
}
