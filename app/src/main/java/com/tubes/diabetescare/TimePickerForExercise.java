package com.tubes.diabetescare;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class TimePickerForExercise extends DialogFragment implements
        TimePickerDialog.OnTimeSetListener {

    private View mView;

    public TimePickerForExercise(View view) {
        mView = view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hourOfDay, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        //Method ini akan di panggil jika user telah memilih date
        HealthFeature activity = (HealthFeature) getActivity();
        assert activity != null;
        activity.processTimePickerResult(hourOfDay, minute);
        Intent intentAlarm = new Intent(AlarmClock.ACTION_SET_ALARM);
        intentAlarm.putExtra(AlarmClock.EXTRA_HOUR, hourOfDay);
        intentAlarm.putExtra(AlarmClock.EXTRA_MINUTES, minute);
        intentAlarm.putExtra(AlarmClock.EXTRA_MESSAGE, "Ayo Berolahraga Demi Masa Depan!");
        if (hourOfDay <=24 && minute <=60){
            startActivity(intentAlarm);
        }
        System.out.println("ini jam : "+hourOfDay);
        System.out.println("ini menit : "+minute);

    }

}
