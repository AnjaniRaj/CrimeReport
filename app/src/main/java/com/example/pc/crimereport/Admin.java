package com.example.pc.crimereport;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

public class Admin extends AppCompatActivity {

    EditText Pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Pin = (EditText) findViewById(R.id.pin);

        Pin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(Pin.getText().toString().equals("1")) {
                    //Toast.makeText(getApplicationContext(), "hgv", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),Admin_Area.class));
                }
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Pin.setText("");
    }
}
