package com.shenkar.shakedzrihen.mobileExercise.birthdayList;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.icu.util.Calendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
public class BirthdayListItem {

    @PrimaryKey(autoGenerate = true)
    private int _id;

    @ColumnInfo(name = "full_name")
    private String _fullName;

    @ColumnInfo(name = "birthday")
    private String _birthday;

    @ColumnInfo(name = "comment")
    private String _comment;



    public BirthdayListItem(String fullName, String birthday, String comment){
        setFullName(fullName);
        setBirthday(birthday);
        setComment(comment);
    }

    //    Getters
    public int getId() { return _id; }

    public String getFullName() { return _fullName; }

    public String getBirthday() { return _birthday; }

    public String getComment(){ return _comment; }

    public String getAge() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date convertedDate = dateFormat.parse(_birthday);

        Calendar birhday = Calendar.getInstance();
        birhday.setTime(convertedDate);
        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - birhday.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < birhday.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = Integer.valueOf(age);
        String ageS = ageInt.toString();

        return ageS;


    }


    //    Setters
    public void setComment(String description){ _comment = description; }

    public void setFullName(String fullName) { _fullName = fullName; }

    public void setBirthday(String birthday) { _birthday = birthday; }

    public void setId(int id){_id = id;}

    // Methods

    public int calculateNextBDay(String birthday) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date convertedDate = dateFormat.parse(birthday);

        Calendar birhday = Calendar.getInstance();
        birhday.setTime(convertedDate);
        Calendar today = Calendar.getInstance();

        int nextBday = 0;

        if ( birhday.get(Calendar.MONTH) >= today.get(Calendar.MONTH)){
            nextBday = (birhday.get(Calendar.MONTH) - today.get(Calendar.MONTH)) * 31;
            if (birhday.get(Calendar.DAY_OF_MONTH) >= today.get(Calendar.DAY_OF_MONTH)){
                nextBday += birhday.get(Calendar.DAY_OF_MONTH) - today.get(Calendar.DAY_OF_MONTH);
            } else {
                nextBday += (12 * 31) - (today.get(Calendar.DAY_OF_MONTH) - birhday.get(Calendar.DAY_OF_MONTH));
            }
        } else {
            nextBday = (12 - (today.get(Calendar.MONTH) - birhday.get(Calendar.MONTH))) * 31;
            if (birhday.get(Calendar.DAY_OF_MONTH) >= today.get(Calendar.DAY_OF_MONTH)){
                nextBday += birhday.get(Calendar.DAY_OF_MONTH) - today.get(Calendar.DAY_OF_MONTH);
            } else {
                nextBday += today.get(Calendar.DAY_OF_MONTH) - birhday.get(Calendar.DAY_OF_MONTH);
            }
        }
        return nextBday;
    }
}
