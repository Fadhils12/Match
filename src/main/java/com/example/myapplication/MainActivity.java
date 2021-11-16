package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements AdapterView.OnItemSelectedListener {

    String[] countryNames = {"Football", "Basket Ball", "Baseabll", "Volly", "Badminton", "New Tennis", "Rugby Ball"};
    int flags[] = {R.drawable.football, R.drawable.basketball_ball, R.drawable.baseball, R.drawable.volleyball, R.drawable.badminton, R.drawable.tennis, R.drawable.rugby_ball};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spin = (Spinner) findViewById(R.id.tv_judul);
        spin.setOnItemSelectedListener(this);

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), flags, countryNames);
        spin.setAdapter(customAdapter);

        final EditText homeTeam = findViewById(R.id.home_team);
        final EditText awayTeam = findViewById(R.id.away_team);


        Button btnStart = findViewById(R.id.btn_start);

        Bundle bundle = new Bundle();

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (homeTeam.getText().toString().length()==0){
                    homeTeam.setError("Username diperlukan!");
                } else if (awayTeam.getText().toString().length()==0){
                    awayTeam.setError("vsdvsdcasc");
                } else {
                    Intent intent = new Intent(MainActivity.this, MatchActivity.class);
                    intent.putExtra("home", homeTeam.getText().toString());
                    intent.putExtra("away", awayTeam.getText().toString());
                    startActivity(intent);
                }

                /*intent.putExtra("", homeTeam.getText().toString());
                intent.putExtra("", awayTeam.getText().toString());
                startActivity(intent);
*/
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), countryNames[position], Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}