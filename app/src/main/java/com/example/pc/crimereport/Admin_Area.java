package com.example.pc.crimereport;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
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

public class Admin_Area extends AppCompatActivity {

    RecyclerView recyclerView;
    FrameLayout frameLayout;
    SwipeRefreshLayout refreshLayout;
    ArrayList<MainRow> mainRows;
    final Context context = this;
    ItemTouchHelper.SimpleCallback simpleCallback;
    ItemTouchHelper itemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__area);

        frameLayout = (FrameLayout) findViewById(R.id.frame_no_rows_admin);
        frameLayout.setVisibility(View.INVISIBLE);

        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_admin);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                AsyncFetchAdmin asyncFetch = new AsyncFetchAdmin();
                asyncFetch.execute("Ninad");
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_admin);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(new AdminRecyclerView(context, mainRows));

        simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                AsyncFetchAdmin asyncFetch1 = new AsyncFetchAdmin();
                asyncFetch1.execute("Remove", mainRows.get(viewHolder.getAdapterPosition()).Fir_No);
                mainRows.remove(viewHolder.getAdapterPosition());
                recyclerView.getAdapter().notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        };

        itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        AsyncFetchAdmin asyncFetch = new AsyncFetchAdmin();
        asyncFetch.execute("Ninad");
    }

    class AsyncFetchAdmin extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                frameLayout.setVisibility(View.VISIBLE);
                (findViewById(R.id.img_admin)).setVisibility(View.VISIBLE);
                ((TextView) findViewById(R.id.no_net_message_admin)).setText("No internet");
            }
        }

        @Override
        protected String doInBackground(String... strings) {
            switch (strings[0]) {

                case "Ninad":

                    try {
                        URL url = new URL("http://nimraptnc.netai.net/fetch_all.php");
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                       // connection.setDoOutput(true);
                        connection.setDoInput(true);

                        InputStream inputStream = connection.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                        StringBuilder result = new StringBuilder();
                        String line;
                        while ((line = bufferedReader.readLine()) != null) {
                            result.append(line);
                        }
                        bufferedReader.close();
                        inputStream.close();
                        //connection.disconnect();

                        return result.toString().trim();

                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }

                case "Remove":

                    try {

                        URL url = new URL("http://nimraptnc.netai.net/remove.php");
                        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setDoOutput(true);

                        OutputStream outputStream = httpURLConnection.getOutputStream();
                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                        String data = URLEncoder.encode("Id", "UTF-8") + "=" + URLEncoder.encode(strings[1], "UTF-8");
                        bufferedWriter.write(data);
                        bufferedWriter.flush();
                        bufferedWriter.close();
                        outputStream.close();

                        InputStream IS = httpURLConnection.getInputStream();
                        IS.close();
                        // httpURLConnection.disconnect();       do not use disconnect here !!
                        return "Remove";

                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                default:

                    return null;

            }

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s != null && !s.equals("Remove")) {

                    mainRows = new ArrayList<>();
                    try {
                        JSONObject object = new JSONObject(s);
                        JSONArray array = object.getJSONArray("server_response");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonObject = array.getJSONObject(i);
                            MainRow mainRow = new MainRow();
                            if (jsonObject.getString("status").equals("1"))
                                mainRow.Status = "Ongoing";
                            else if (jsonObject.getString("status").equals("0"))
                                mainRow.Status = "Closed";
                            mainRow.Type = jsonObject.getString("type");
                            mainRow.Date = jsonObject.getString("date").substring(0, 10);
                            mainRow.Fir_No = jsonObject.getString("id");
                            mainRow.Location = jsonObject.getString("location");
                            mainRow.Name = jsonObject.getString("name");
                            mainRow.DOC = jsonObject.getString("doc");
                            mainRow.Police_Name = jsonObject.getString("police_name");
                            mainRow.Police_Id = jsonObject.getString("police_id");
                            mainRow.Aadhar = jsonObject.getString("aadhar");
                            mainRow.Station_Location = jsonObject.getString("station_location");
                            mainRows.add(0, mainRow);
                        }

                        if (mainRows.size() == 0) {
                            frameLayout.setVisibility(View.VISIBLE);
                            (findViewById(R.id.img_admin)).setVisibility(View.INVISIBLE);
                            ((TextView) findViewById(R.id.no_rows_message_admin)).setText("No entries made");
                        } else {
                            frameLayout.setVisibility(View.INVISIBLE);
                            AdminRecyclerView adapter = new AdminRecyclerView(context, mainRows);
                            recyclerView.setAdapter(adapter);


                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    refreshLayout.setRefreshing(false);

            }
        }
    }
}
