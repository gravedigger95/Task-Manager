package com.example.masa.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Pocetna extends AppCompatActivity implements View.OnClickListener {

    Button btnNoviZad;
    Button btnStatistika;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocetna);

        btnNoviZad=(Button)findViewById(R.id.btnNoviZad);
        btnNoviZad.setOnClickListener(this);
        btnStatistika=(Button)findViewById(R.id.btnStatistika);
        btnStatistika.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnNoviZad:
                Intent intentNoviZad= new Intent(Pocetna.this,NoviZadatak.class);
                startActivity(intentNoviZad);
                break;

            case R.id.btnStatistika:
                Intent intentStatistika=new Intent(Pocetna.this,Statistika.class);
                startActivity(intentStatistika);
                break;

            default:
                break;
        }

    }
}
