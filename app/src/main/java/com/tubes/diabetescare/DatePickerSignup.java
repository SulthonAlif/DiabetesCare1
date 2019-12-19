package com.tubes.diabetescare;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public class DatePickerSignup extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState) {
        // Use the current date as the default date in the picker.
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int dayOfMonth = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it.
        return new DatePickerDialog(Objects.requireNonNull(getActivity()),
                this, year, month, dayOfMonth);
    }

    @Override // This method will be called when the user sets the date.
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        SignupForm activity = (SignupForm) getActivity();
        assert activity != null;
        activity.processDatePickerResult(year, month, dayOfMonth);
    }
}
