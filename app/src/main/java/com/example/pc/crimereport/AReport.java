package com.example.pc.crimereport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AReport extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areport);

        Intent intent = getIntent();
        ((TextView) findViewById(R.id.detail_type_ans)).setText(intent.getStringExtra("Type"));
        ((TextView) findViewById(R.id.detail_status_ans)).setText(intent.getStringExtra("Status"));
        ((TextView) findViewById(R.id.detail_date_ans)).setText(intent.getStringExtra("Date"));
        ((TextView) findViewById(R.id.detail_fir_no)).setText(intent.getStringExtra("id"));
        ((TextView) findViewById(R.id.detail_station_ans)).setText(intent.getStringExtra("location"));
        ((TextView) findViewById(R.id.detail_doc_ans)).setText(intent.getStringExtra("doc"));
    }
}
