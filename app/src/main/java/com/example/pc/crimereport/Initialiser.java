package com.example.pc.crimereport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Initialiser extends AppCompatActivity {

    ArrayList<EditText> Info;
    static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        if (sharedPreferences.getInt("Aadhar",0)==0) {
            setContentView(R.layout.activity_initialiser);
            Info = new ArrayList<>();
            getSupportActionBar().setTitle("Add details");
            Info.add((EditText) findViewById(R.id.aadhar));
            Info.add((EditText) findViewById(R.id.name));
            Info.add((EditText) findViewById(R.id.DOB));
            Info.add((EditText) findViewById(R.id.address));
            Info.add((EditText) findViewById(R.id.phone));
            Button Register = (Button) findViewById(R.id.button);

            Register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!Info.get(0).getText().toString().equals("")
                            && !Info.get(1).getText().toString().equals("")
                            && !Info.get(2).getText().toString().equals("")
                            && !Info.get(3).getText().toString().equals("")
                            && !Info.get(4).getText().toString().equals("")) {
                        BGforUserInfo task = new BGforUserInfo(getApplicationContext());
                        task.execute(Info.get(0).getText().toString(),
                                Info.get(1).getText().toString(),
                                Info.get(2).getText().toString(),
                                Info.get(3).getText().toString(),
                                Info.get(4).getText().toString());
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("Aadhar", Integer.parseInt(Info.get(0).getText().toString()));
                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }
            });
        } else {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }


    }
}
