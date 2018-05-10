package com.shenkar.shakedzrihen.mobileExercise.birthdayList;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import com.shenkar.shakedzrihen.mobileExercise.R;
import java.text.ParseException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BirthdayListActivity extends AppCompatActivity {

    RecyclerView birthdayList;
    RecyclerView.Adapter birthdayListAdapter;
    List<BirthdayListItem> bdListItems;
    private static final String TAG = "MyActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.birthdaylist_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        birthdayList = findViewById(R.id.birthday_list);
        birthdayList.setLayoutManager(new LinearLayoutManager(this));


        new Thread(new Runnable(){
            public void run() {
                Log.d(TAG, "here");
                BirthdayListDatabase db = Room.databaseBuilder(
                        getApplicationContext(),
                        BirthdayListDatabase.class,
                        "BirthdayListDB"
                ).fallbackToDestructiveMigration().build();
                bdListItems = db.birthdayListItemDao().getAllItems();
                Collections.sort(bdListItems, new Comparator<BirthdayListItem>() {

                    @Override
                    public int compare(BirthdayListItem o1, BirthdayListItem o2) {
                        try {
                            int generateBDayo1 = o1.calculateNextBDay(o1.getBirthday());
                            int generateBDayo2 = o2.calculateNextBDay(o2.getBirthday());
                            return Integer.compare(generateBDayo1, generateBDayo2);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return 0;
                    }
                } );
                BirthdayListActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        birthdayListAdapter = new BirthdayListAdapter(bdListItems);
                        birthdayList.setAdapter(birthdayListAdapter);
                    }
                });
            }
        }).start();

        FloatingActionButton addNewListItem = findViewById(R.id.fab);

        addNewListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BirthdayListActivity.this, AddNewBirthdayListItem.class));

            }
        });
    }

}
