package com.shenkar.shakedzrihen.mobileExercise.birthdayList;

import android.app.DatePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BirthdayPicker {
    private Context _context;
    private EditText _birthday;
    private Calendar _calendar;
    private DatePickerDialog.OnDateSetListener _dateListener;

    public BirthdayPicker(final Context context, EditText birthday) {
        _context = context;
        _birthday = birthday;
        _calendar = Calendar.getInstance();

        _dateListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                _calendar.set(Calendar.YEAR, year);
                _calendar.set(Calendar.MONTH, month);
                _calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat simpleDataFormat = new SimpleDateFormat(myFormat);

                _birthday.setText(simpleDataFormat.format(_calendar.getTime()));
            }
        };

        _birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(_context, _dateListener, _calendar
                        .get(Calendar.YEAR), _calendar.get(Calendar.MONTH),
                        _calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    public String getBirthday() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dateFormat.format(_calendar.getTime());
    }
}
