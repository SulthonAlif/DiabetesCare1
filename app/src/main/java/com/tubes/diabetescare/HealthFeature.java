package com.tubes.diabetescare;

import android.content.res.TypedArray;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class HealthFeature extends AppCompatActivity implements SportAdapterHealth.OnCardListener {

    private ArrayList<SportHealth> mRemainderData;
    private com.tubes.diabetescare.SportAdapterHealth mAdapterRemainder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_feature);

        // Initialize the RecyclerView.
        // Member variables.
        RecyclerView mRecyclerViewRemainder = findViewById(R.id.recyclerViewRemainder);

        // Set the Layout Manager.
        mRecyclerViewRemainder.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the ArrayList that will contain the data.
        mRemainderData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapterRemainder = new com.tubes.diabetescare.SportAdapterHealth(this, mRemainderData, this);
        mRecyclerViewRemainder.setAdapter(mAdapterRemainder);

        initializeDataRemainder();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void initializeDataRemainder() {
        // Get the resources from the XML file.
        String[] sportsTitle = getResources()
                .getStringArray(R.array.sports_health_titles);
        TypedArray sportIconResource = getResources()
                .obtainTypedArray(R.array.sports_health_icon);
        String[] sportsTime = getResources()
                .getStringArray(R.array.sports_health_times);

        // Clear the existing data (to avoid duplication).
        mRemainderData.clear();

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for (int i = 0; i < sportsTitle.length; i++) {
            mRemainderData.add(new SportHealth(sportsTitle[i],
                    sportIconResource.getResourceId(i, 0), sportsTime[i]));
        }

        sportIconResource.recycle();

        // Notify the adapter of the change.
        mAdapterRemainder.notifyDataSetChanged();
    }

    @Override
    public void onCardClick(int position) {

    }

    public void processTimePickerResult(int hourOfDay, int minute) {
        String hour_string = Integer.toString(hourOfDay);
        String minute_string = Integer.toString(minute);
    }
}
