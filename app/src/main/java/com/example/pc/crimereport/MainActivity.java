package com.example.pc.crimereport;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SwipeRefreshLayout refreshLayout;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    ArrayList<MainRow> mainRows;
    FrameLayout frameLayout;

    String Aadhar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = (FrameLayout) findViewById(R.id.frame_no_rows);
        frameLayout.setVisibility(View.INVISIBLE);
        Aadhar = Integer.toString(Initialiser.sharedPreferences.getInt("Aadhar", 0));
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), New_Report.class));
            }
        });


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerAdapter = new RecyclerAdapter(mainRows);
        recyclerView.setAdapter(recyclerAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0)
                    fab.hide();
                else
                    fab.show();

            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                AsyncFetch asyncFetch = new AsyncFetch();
                asyncFetch.execute("NewDataEntry", Aadhar);
            }
        });
        AsyncFetch asyncFetch = new AsyncFetch();
        asyncFetch.execute("NewDataEntry", Aadhar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

    }


    class AsyncFetch extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                frameLayout.setVisibility(View.VISIBLE);
                (findViewById(R.id.img)).setVisibility(View.VISIBLE);
                ((TextView) findViewById(R.id.no_net_message)).setText("No internet");
            }


        }

        @Override
        protected String doInBackground(String... strings) {

            if (strings[0].equals("NewDataEntry")) {
                try {
                    URL url = new URL("http://nimraptnc.netai.net/fetch_my.php");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);

                    OutputStream outputStream = connection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("Aadhar", "UTF-8") + "=" + URLEncoder.encode(strings[1], "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result.append(line);
                    }
                    bufferedReader.close();
                    inputStream.close();
                    connection.disconnect();

                    return result.toString().trim();

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            } else
                return null;
        }

        @Override
        protected void onPostExecute(String s) {
            mainRows = new ArrayList<>();
            try {
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray("server_response");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject = array.getJSONObject(i);
                    MainRow mainRow = new MainRow();
                    if (jsonObject.getString("status").equals("1"))
                        mainRow.Status = "Ongoing";
                    mainRow.Type = jsonObject.getString("type");
                    mainRow.Date = jsonObject.getString("date").substring(0, 10);
                    mainRow.Fir_No = jsonObject.getString("id");
                    mainRow.Location = jsonObject.getString("location");
                    mainRow.DOC = jsonObject.getString("doc");
                    mainRows.add(0, mainRow);
                }


                if (mainRows.size() == 0) {

                    frameLayout.setVisibility(View.VISIBLE);
                    (findViewById(R.id.img)).setVisibility(View.INVISIBLE);
                    ((TextView) findViewById(R.id.no_rows_message)).setText("No entries made");

                } else {
                    frameLayout.setVisibility(View.INVISIBLE);
                    RecyclerAdapter adapter = new RecyclerAdapter(mainRows);
                    recyclerView.setAdapter(adapter);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }

            refreshLayout.setRefreshing(false);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 0, Menu.NONE, "Admin").setIcon(R.drawable.ic_supervisor_account_white_24dp).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                startActivity(new Intent(getApplicationContext(), Admin.class));
                return true;
            default:
                return false;
        }
    }

}
