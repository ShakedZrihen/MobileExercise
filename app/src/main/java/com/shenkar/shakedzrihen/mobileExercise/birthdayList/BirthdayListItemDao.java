package com.shenkar.shakedzrihen.mobileExercise.birthdayList;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface BirthdayListItemDao {
    @Query("SELECT * FROM BirthdayListItem")
    List<BirthdayListItem> getAllItems();

    @Insert
    void insertAll(BirthdayListItem birthdayListItem);

    @Query("DELETE FROM BirthdayListItem")
    public void deleteAll();
}
