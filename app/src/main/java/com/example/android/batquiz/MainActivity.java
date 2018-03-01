package com.example.android.batquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon2);
    }

    // method to change screen when button is clicked
    public void btnJogarOnClick(View v) {
        Intent intent = new Intent(this, QuizActivity.class);
        startActivity(intent);
    }

}