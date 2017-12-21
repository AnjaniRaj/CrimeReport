package com.example.pc.crimereport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;


public class PoliceInfo extends AppCompatActivity {
    static ArrayList<DetailRow> dprows;
    static RecyclerView detail;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__area);
        detail = (RecyclerView) findViewById(R.id.recycler_view_admin);
        intent=getIntent();
        ImageView imageView= (ImageView) findViewById(R.id.img_admin);
        imageView.setVisibility(View.INVISIBLE);
        BackGround back= new BackGround(getApplicationContext());
        back.execute("policeinfo",intent.getStringExtra("Police_id"));



        detail.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        detail.setHasFixedSize(true);
        detail.setAdapter(new DetailAdapter(dprows));
    }
}