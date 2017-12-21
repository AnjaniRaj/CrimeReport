package com.example.pc.crimereport;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

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

import static com.example.pc.crimereport.ComplainerInfo.dcrows;
import static com.example.pc.crimereport.PoliceInfo.dprows;
import static com.example.pc.crimereport.Police_station.dsrows;

class BackGround extends AsyncTask<String, Void, String> {

    private Context context;
    private static String count, station_location;
    private int flag;


    BackGround(Context context) {
        this.context = context;
        flag = 0;
    }


    @Override
    protected String doInBackground(String... params) {


        switch (params[0]) {
            case "NewDataEntry":

                try {
                    URL url = new URL("http://nimraptnc.netai.net/insert.php");

                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    OutputStream OS = httpURLConnection.getOutputStream();


                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&" +
                            URLEncoder.encode("date", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8") + "&" +
                            URLEncoder.encode("time", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8") + "&" +
                            URLEncoder.encode("loc", "UTF-8") + "=" + URLEncoder.encode(params[4], "UTF-8") + "&" +
                            URLEncoder.encode("suspects", "UTF-8") + "=" + URLEncoder.encode(params[5], "UTF-8") + "&" +
                            URLEncoder.encode("items", "UTF-8") + "=" + URLEncoder.encode(params[6], "UTF-8") + "&" +
                            URLEncoder.encode("weapons", "UTF-8") + "=" + URLEncoder.encode(params[7], "UTF-8") + "&" +
                            URLEncoder.encode("desc", "UTF-8") + "=" + URLEncoder.encode(params[8], "UTF-8") + "&" +
                            URLEncoder.encode("status", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") + "&" +
                            URLEncoder.encode("aadhar", "UTF-8") + "=" +
                            URLEncoder.encode(Integer.toString(Initialiser.sharedPreferences.getInt("Aadhar", 0)), "UTF-8") + "&" +
                            URLEncoder.encode("Police_id", "UTF-8") + "=" + URLEncoder.encode(count, "UTF-8") + "&" +
                            URLEncoder.encode("station_location", "UTF-8") + "=" + URLEncoder.encode(station_location, "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();

                    InputStream IS = httpURLConnection.getInputStream();
                    IS.close();
                    return "Insertion Success!";

                } catch (Exception e) {
                    e.printStackTrace();
                    return "Failure";
                }

            case "Status_true":
                try {

                    URL url = new URL("http://nimraptnc.netai.net/status_true.php");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);


                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("Id", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();

                    InputStream IS = httpURLConnection.getInputStream();
                    IS.close();
                    return "Status Changed";

                } catch (Exception e) {

                    e.printStackTrace();
                    return "Failure";
                }

            case "Status_false":
                try {

                    URL url = new URL("http://nimraptnc.netai.net/status_false.php");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);


                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("Id", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();

                    InputStream IS = httpURLConnection.getInputStream();
                    IS.close();
                    return "Status Changed";

                } catch (Exception e) {

                    e.printStackTrace();
                    return "Failure";
                }

            case "Police_Add":

                try {

                    URL url = new URL("http://nimraptnc.netai.net/add_police.php");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);


                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&" +
                            URLEncoder.encode("Dept", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8") + "&" +
                            URLEncoder.encode("Designation", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8") + "&" +
                            URLEncoder.encode("JYear", "UTF-8") + "=" + URLEncoder.encode(params[4], "UTF-8") + "&" +
                            URLEncoder.encode("Nof_Crimes", "UTF-8") + "=" + URLEncoder.encode(params[5], "UTF-8") + "&" +
                            URLEncoder.encode("Ratio", "UTF-8") + "=" + URLEncoder.encode(params[6], "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = bufferedReader.readLine();
                    bufferedReader.close();
                    inputStream.close();
                    flag = 1;

                    return result.trim();
                } catch (Exception e) {

                    e.printStackTrace();
                    return "Failure";
                }

            case "Station_Add":

                try {

                    URL url = new URL("http://nimraptnc.netai.net/add_station.php");
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);


                    OutputStream OS = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                    String data = URLEncoder.encode("Location", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8") + "&" +
                            URLEncoder.encode("Establishment_Year", "UTF-8") + "=" + URLEncoder.encode(params[2], "UTF-8") + "&" +
                            URLEncoder.encode("No_of_Rooms", "UTF-8") + "=" + URLEncoder.encode(params[3], "UTF-8") + "&" +
                            URLEncoder.encode("Head", "UTF-8") + "=" + URLEncoder.encode(params[4], "UTF-8") + "&" +
                            URLEncoder.encode("No_of_Constables", "UTF-8") + "=" + URLEncoder.encode(params[5], "UTF-8") + "&" +  //skipped 5!!
                            URLEncoder.encode("No_of_Empty", "UTF-8") + "=" + URLEncoder.encode(params[6], "UTF-8") + "&" +       //hehehehe Sorry!!
                            URLEncoder.encode("No_of_Occ", "UTF-8") + "=" + URLEncoder.encode(params[7], "UTF-8");
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    OS.close();

                    InputStream inputStream=httpURLConnection.getInputStream();
                    inputStream.close();
                    flag=41;
                    return params[1];


                } catch (Exception e) {
                    e.printStackTrace();
                    return "Failure";
                }

            case "Station_fetch":
                try {


                    URL url1 = new URL("http://nimraptnc.netai.net/fetch_station.php");
                    HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);

                    OutputStream outputStream = connection.getOutputStream();
                    BufferedWriter lekakh = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String dataf = URLEncoder.encode("Location", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
                    lekakh.write(dataf);
                    lekakh.flush();
                    lekakh.close();
                    outputStream.close();

                    InputStream input = connection.getInputStream();
                    BufferedReader Reader = new BufferedReader(new InputStreamReader(input, "iso-8859-1"));
                    StringBuilder result = new StringBuilder();

                    String line;
                    while ((line = Reader.readLine()) != null) {
                        result.append(line);
                    }
                    Reader.close();
                    input.close();
                    connection.disconnect();
                    flag = 7;

                    return result.toString().trim();

                } catch (Exception e) {

                    e.printStackTrace();
                    return "Failure";
                }

            case "complainer":
                try {
                    URL url = new URL("http://nimraptnc.netai.net/fetch_complainer.php");    //address of the php script here
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);

                    OutputStream outputStream = connection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String data = URLEncoder.encode("Aadhar", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");  //pass the aadhar no
                    bufferedWriter.write(data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();

                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    StringBuilder result = new StringBuilder();

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {  //read the result from the php script
                        result.append(line);                                //and put it in string result
                    }
                    bufferedReader.close();
                    inputStream.close();
                    connection.disconnect();
                    flag = 5;

                    return result.toString().trim();

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }

            case "policeinfo":
                try {
                    URL url = new URL("http://nimraptnc.netai.net/fetch_policeinfo.php");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);

                    OutputStream outputstream = connection.getOutputStream();
                    BufferedWriter lekhak = new BufferedWriter(new OutputStreamWriter(outputstream, "UTF-8"));
                    String data = URLEncoder.encode("Police_id", "UTF-8") + "=" + URLEncoder.encode(params[1], "UTF-8");
                    lekhak.write(data);
                    lekhak.flush();
                    lekhak.close();
                    outputstream.close();

                    InputStream input = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input, "iso-8859-1"));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    reader.close();
                    input.close();
                    connection.disconnect();
                    flag = 8;

                    return result.toString().trim();

                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }

            default:
                return null;
        }
    }

    @Override
    protected void onPostExecute(String aVoid) {

        if (flag == 0)
            Toast.makeText(context, aVoid, Toast.LENGTH_SHORT).show();
        else if (flag == 5) {
            ComplainerInfo.dcrows = new ArrayList<>();
            try {

                JSONObject object = new JSONObject(aVoid);
                JSONArray array = object.getJSONArray("server_response");

                JSONObject single = array.getJSONObject(0);

                dcrows.add(new DetailRow("Name", single.getString("name")));
                dcrows.add(new DetailRow("DOB", single.getString("dob")));
                dcrows.add(new DetailRow("Address", single.getString("address")));
                dcrows.add(new DetailRow("Phone number", single.getString("phone number")));

                DetailAdapter adapter = new DetailAdapter(dcrows);
                ComplainerInfo.detail.setAdapter(adapter);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (flag == 2) {
            dsrows = new ArrayList<>();
            try {

                JSONObject thing = new JSONObject(aVoid);
                JSONArray arrthing = thing.getJSONArray("server_response");

                JSONObject singlething = arrthing.getJSONObject(0);

                dsrows.add(new DetailRow("Head of Station", singlething.getString("head")));
                dsrows.add(new DetailRow("Establishment Year", singlething.getString("estab_year")));
                dsrows.add(new DetailRow("No of Constables", singlething.getString("no_constables")));
                dsrows.add(new DetailRow("No of Rooms", singlething.getString("room")));
                dsrows.add(new DetailRow("No of Empty Cells", singlething.getString("no_empty_cell")));
                dsrows.add((new DetailRow("No of Occupied Cells", singlething.getString("no_occ_cell"))));

                DetailAdapter adapter = new DetailAdapter(dsrows);
                Police_station.detail.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (flag == 8) {
            dprows = new ArrayList<>();
            try {

                JSONObject obj = new JSONObject(aVoid);
                JSONArray a = obj.getJSONArray("server_response");

                JSONObject single = a.getJSONObject(0);

                dprows.add(new DetailRow("ID", single.getString("id")));
                dprows.add(new DetailRow("Name", single.getString("name")));
                dprows.add(new DetailRow("Department", single.getString("department")));
                dprows.add(new DetailRow("Designation", single.getString("designation")));
                dprows.add(new DetailRow("Joining Year", single.getString("joining_year")));
                dprows.add(new DetailRow("No Of Crimes", single.getString("no_of_crimes")));
                dprows.add(new DetailRow("Ratio Solved to Assigned", single.getString("ratio")));

                DetailAdapter adapter = new DetailAdapter(dprows);
                PoliceInfo.detail.setAdapter(adapter);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (flag == 7) {
            Police_station.dsrows = new ArrayList<>();
            try {

                JSONObject obj = new JSONObject(aVoid);
                JSONArray a = obj.getJSONArray("server_response");

                JSONObject single = a.getJSONObject(0);

                dsrows.add(new DetailRow("Police Station",single.getString("location")));
                dsrows.add(new DetailRow("Head", single.getString("head")));
                dsrows.add(new DetailRow("Year of establishment", single.getString("estab_year")));
                dsrows.add(new DetailRow("Number of Constables", single.getString("no_constables")));
                dsrows.add(new DetailRow("Number of rooms", single.getString("room")));
                dsrows.add(new DetailRow("Occupied cells", single.getString("no_empty_cell")));
                dsrows.add(new DetailRow("Empty cells", single.getString("no_occ_cell")));

                DetailAdapter adapter = new DetailAdapter(dsrows);
                Police_station.detail.setAdapter(adapter);

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (flag == 1)
            count = aVoid;
        else if(flag==41)
            station_location = aVoid;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }
}
