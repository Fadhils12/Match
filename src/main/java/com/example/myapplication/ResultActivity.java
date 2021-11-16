package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView textResult = findViewById(R.id.tv_winner);



        if(getIntent().getExtras()!=null){

            textResult.setText(getIntent().getStringExtra("team"));



//            textResult.setText("Pemenangnya adalah tim" + getIntent().getStringExtra("team a"));
        } else {
            textResult.setText("!! Draw !!");
        }

    }
}