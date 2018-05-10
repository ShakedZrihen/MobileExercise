package com.shenkar.shakedzrihen.mobileExercise.birthdayList;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {BirthdayListItem.class}, version = 3)
public abstract class BirthdayListDatabase extends RoomDatabase {

    public abstract BirthdayListItemDao birthdayListItemDao();
}
