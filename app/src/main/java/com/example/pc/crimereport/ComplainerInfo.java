package com.example.pc.crimereport;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class ComplainerInfo extends AppCompatActivity {
    static ArrayList<DetailRow> dcrows;
     static RecyclerView detail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_admin_report);
        detail = (RecyclerView) findViewById(R.id.detail_recyclerView);
        detail.setHasFixedSize(true);
        detail.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        detail.setAdapter(new DetailAdapter(dcrows));

        Intent intent = getIntent();



       // int aadhar = Initialiser.sharedPreferences.getInt("Aadhar", 0);
       // Toast.makeText(this,Integer.toString(aadhar),Toast.LENGTH_SHORT).show();
        BackGround backGround = new BackGround(getApplicationContext());
        backGround.execute("complainer", intent.getStringExtra("Aadhar"));







    }


}



