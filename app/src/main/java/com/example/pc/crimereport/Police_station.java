package com.example.pc.crimereport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class Police_station extends AppCompatActivity {

    static ArrayList<DetailRow> dsrows;
    static RecyclerView detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_admin_report);     //wrong layout used honey
        detail = (RecyclerView) findViewById(R.id.detail_recyclerView);
        detail.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        detail.setHasFixedSize(true);
        detail.setAdapter(new DetailAdapter(dsrows));

        Intent intent = getIntent();
        BackGround backGround = new BackGround(getApplicationContext());
        backGround.execute("Station_fetch", intent.getStringExtra("station_location"));

    }
}

