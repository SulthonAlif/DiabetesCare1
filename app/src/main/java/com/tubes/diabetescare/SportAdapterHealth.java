package com.tubes.diabetescare;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SportAdapterHealth extends RecyclerView.Adapter<SportAdapterHealth.ViewHolder> {

    // Member variables.
    private ArrayList<SportHealth> mRemainderData;
    private Context mContext;
    private ImageView mRemainderIcon;
    private OnCardListener mOnCardListener;

    SportAdapterHealth(Context context, ArrayList<SportHealth> RemainderData, OnCardListener onCardListener) {
        this.mRemainderData = RemainderData;
        this.mContext = context;
        this.mOnCardListener = onCardListener;
    }

    @NonNull
    @Override
    public SportAdapterHealth.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.exercise_list, parent, false), mOnCardListener);
    }

    @Override
    public void onBindViewHolder(final SportAdapterHealth.ViewHolder holder,
                                 final int position) {
        // Get current sport.
        SportHealth currentRemainderCard = mRemainderData.get(position);

        // Populate the TextView with data.
        holder.bindTo(currentRemainderCard);

        holder.mTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new TimePickerForExercise(v);
                newFragment.show(((HealthFeature)mContext).getSupportFragmentManager(), "timePicker");
            }
        });

    }

    @Override
    public int getItemCount() {
        return mRemainderData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Member Variables for the TextViews
        private TextView mTitleText, mTimeText;
        OnCardListener onCardListener;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The RootView of the list_item.xml layout file.
         */
        ViewHolder(View itemView, OnCardListener onCardListener) {
            super(itemView);

            // Initialize the views.
            mTitleText = (TextView) itemView.findViewById(R.id.sportsText);
            mRemainderIcon = (ImageView) itemView.findViewById(R.id.sportsIcon);
            mTimeText = (TextView) itemView.findViewById(R.id.sportsTime);

            this.onCardListener = onCardListener;

            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);
        }

        void bindTo(SportHealth currentRemainderCard) {
            // Populate the TextView with data.
            mTitleText.setText(currentRemainderCard.getExerciseTitle());
            Glide.with(mContext).load(currentRemainderCard.getImageResource()).into(mRemainderIcon);
            mTimeText.setText(currentRemainderCard.getExerciseTime());
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
