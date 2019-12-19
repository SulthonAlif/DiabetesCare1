package com.tubes.diabetescare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class ArticleFeature extends AppCompatActivity implements SportsAdapter.OnCardListener {

    private ArrayList<com.tubes.diabetescare.Sport> mSportsData;
    private com.tubes.diabetescare.SportsAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_feature);

        // Initialize the RecyclerView.
        // Member variables.
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the ArrayList that will contain the data.
        mSportsData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new com.tubes.diabetescare.SportsAdapter(this, mSportsData, this);
        mRecyclerView.setAdapter(mAdapter);

        initializeData();
    }

    private void initializeData() {
        // Get the resources from the XML file.
        String[] sportsList = getResources()
                .getStringArray(R.array.sports_titles);
        String[] sportsSubs = getResources()
                .getStringArray(R.array.sports_subs);
        TypedArray sportImageResource = getResources()
                .obtainTypedArray(R.array.sports_images);
        String[] url = getResources().getStringArray(R.array.sports_link);

        // Clear the existing data (to avoid duplication).
        mSportsData.clear();

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for (int i = 0; i < sportsList.length; i++) {
            mSportsData.add(new com.tubes.diabetescare.Sport(sportsList[i], sportsSubs[i],
                    sportImageResource.getResourceId(i, 0), url[i]));
        }

        sportImageResource.recycle();

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCardClick(int position) {
        //Parse the URI and create the intent.
        LinearLayout cardsList = (LinearLayout) findViewById(R.id.cardList);
        Sport currentCard = mSportsData.get(position);
        Intent openLink = new Intent(Intent.ACTION_VIEW);
        openLink.setData(Uri.parse(currentCard.getNewsURL()));

        //Find an activity to hand the intent and start that activity.
        if (openLink.resolveActivity(getPackageManager()) != null) {
            startActivity(openLink);
        } else {
            Log.d("ImplicitIntents", "Can't handle this!");
        }
    }
}
