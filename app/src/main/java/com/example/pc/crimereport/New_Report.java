package com.example.pc.crimereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class New_Report extends AppCompatActivity {

    ArrayList<EditText> InfoFields = new ArrayList<>(8);
    Button Done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new__report);
        InfoFields.add(0, (EditText) findViewById(R.id.new_type));
        InfoFields.add(1, (EditText) findViewById(R.id.new_date));
        InfoFields.add(2, (EditText) findViewById(R.id.new_time));
        InfoFields.add(3, (EditText) findViewById(R.id.new_loc));
        InfoFields.add(4, (EditText) findViewById(R.id.new_suspects));
        InfoFields.add(5, (EditText) findViewById(R.id.new_items));
        InfoFields.add(6, (EditText) findViewById(R.id.new_weapons));
        InfoFields.add(7, (EditText) findViewById(R.id.new_desc));


        Done = (Button) findViewById(R.id.new_done);

        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!InfoFields.get(0).getText().toString().equals("")
                        && !InfoFields.get(1).getText().toString().equals("")
                        && !InfoFields.get(2).getText().toString().equals("")
                        && !InfoFields.get(3).getText().toString().equals("")
                        && !InfoFields.get(7).getText().toString().equals("")) {


                    PoliceFiller policeFiller = new PoliceFiller();
                    String Name = PoliceFiller.Names.get(new Random().nextInt(PoliceFiller.Names.size()));
                    String Dept = PoliceFiller.Dept.get(new Random().nextInt(PoliceFiller.Dept.size()));
                    String Designation = PoliceFiller.Designation.get(new Random().nextInt(PoliceFiller.Designation.size()));
                    String JYear = PoliceFiller.Year.get(new Random().nextInt(PoliceFiller.Year.size()));
                    String Nof_Crimes = Integer.toString(new Random().nextInt(70));
                    String Ratio = Double.toString(Math.random()).substring(0,4);

                    BackGround backGround = new BackGround(getApplicationContext());
                    backGround.execute("Police_Add", Name, Dept, Designation, JYear, Nof_Crimes, Ratio);

                    String head = PoliceFiller.Names.get(new Random().nextInt(PoliceFiller.Names.size()));
                    String estab_yr = "19"+Integer.toString(new Random().nextInt(100));
                    String no_constables = Integer.toString(new Random().nextInt(19));
                    String no_empty_cells= Integer.toString(new Random().nextInt(10));
                    String no_occ_cells = Integer.toString(new Random().nextInt(10));
                    String room = Integer.toString(Integer.parseInt(no_empty_cells)+Integer.parseInt(no_occ_cells));

                    BackGround back= new BackGround(getApplicationContext());
                    back.execute("Station_Add", InfoFields.get(3).getText().toString(), estab_yr,room, head, no_constables,
                            no_empty_cells, no_occ_cells );

                    BackGround backGround2 = new BackGround(getApplicationContext());
                    backGround2.execute("NewDataEntry", InfoFields.get(0).getText().toString(),
                            InfoFields.get(1).getText().toString(),
                            InfoFields.get(2).getText().toString(),
                            InfoFields.get(3).getText().toString(),
                            InfoFields.get(4).getText().toString(),
                            InfoFields.get(5).getText().toString(),
                            InfoFields.get(6).getText().toString(),
                            InfoFields.get(7).getText().toString());
                    finish();

                } else
                    Toast.makeText(getApplicationContext(), "Missing Info", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
