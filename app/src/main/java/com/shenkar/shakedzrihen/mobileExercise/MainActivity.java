package com.shenkar.shakedzrihen.mobileExercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.shenkar.shakedzrihen.mobileExercise.birthdayList.BirthdayListActivity;
import com.shenkar.shakedzrihen.mobileExercise.calculator.CalculatorMainActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

    }

    public void onClickCalculatorBtn(View v){
        startActivity(new Intent(MainActivity.this, CalculatorMainActivity.class));
    }

    public void onClickBirthdayListBtn(View v){
        startActivity(new Intent(MainActivity.this, BirthdayListActivity.class));

    }

}
