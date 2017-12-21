package com.example.pc.crimereport;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class new_admin_report extends AppCompatActivity {

    ArrayList<Row> rows;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_admin_report);
        RecyclerView detail = (RecyclerView) findViewById(R.id.detail_recyclerView);

        Intent intent = getIntent();

        rows = new ArrayList<>();
        rows.add(new Row("Fir No", intent.getStringExtra("id")));
        rows.add(new Row("Type of crime", intent.getStringExtra("Type")));
        rows.add(new Row("Status", intent.getStringExtra("Status")));
        rows.add(new Row("Date of crime", intent.getStringExtra("doc")));
        rows.add(new Row("Date lodged", intent.getStringExtra("Date")));
        rows.add(new Row("Police station", intent.getStringExtra("station_location")));
        rows.add(new Row("Police", intent.getStringExtra("police_name")));
        rows.add(new Row("Complainer", intent.getStringExtra("name")));


        detail.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        detail.setHasFixedSize(true);
        detail.setAdapter(new AdminAdapter(rows, this,intent.getStringExtra("aadhar"),intent.getStringExtra("police_id"),intent.getStringExtra("station_location")));

    }


}
