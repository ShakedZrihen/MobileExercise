package com.shenkar.shakedzrihen.mobileExercise.birthdayList;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.shenkar.shakedzrihen.mobileExercise.R;


public class AddNewBirthdayListItem extends AppCompatActivity {

    EditText _fullName;
    BirthdayPicker _birthday;
    EditText _comment;
    Button _addBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_birthday);
        _fullName = findViewById(R.id.fullName);
        _birthday = new BirthdayPicker(AddNewBirthdayListItem.this, (EditText) findViewById(R.id.birthday));
        _comment = findViewById(R.id.comment);
        _addBtn = findViewById(R.id.addBirthdayBtn);



        final BirthdayListDatabase db = Room.databaseBuilder(
                getApplicationContext(),
                BirthdayListDatabase.class,
                "BirthdayListDB"
        ).allowMainThreadQueries().fallbackToDestructiveMigration().build();

        //  Set listeners
        _addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.birthdayListItemDao().insertAll(
                        new BirthdayListItem(
                                _fullName.getText().toString(),
                                _birthday.getBirthday(),
                                _comment.getText().toString()
                        )
                );
                startActivity(new Intent(AddNewBirthdayListItem.this, BirthdayListActivity.class));
            }
        });
    }
}
