package com.example.pc.crimereport;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class BGforUserInfo extends AsyncTask<String, Void, String> {
    Context context;
    ProgressDialog pDialog;

    BGforUserInfo(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL url = new URL("http://nimraptnc.netai.net/register.php");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            OutputStream outputStream = connection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String data = URLEncoder.encode("aadhar", "UTF-8") + "=" + URLEncoder.encode(strings[0], "UTF-8") + "&" +
                    URLEncoder.encode("Name", "UTF-8") + "=" + URLEncoder.encode(strings[1], "UTF-8") + "&" +
                    URLEncoder.encode("DOB", "UTF-8") + "=" + URLEncoder.encode(strings[2], "UTF-8") + "&" +
                    URLEncoder.encode("Address", "UTF-8") + "=" + URLEncoder.encode(strings[3], "UTF-8") + "&" +
                    URLEncoder.encode("Phone", "UTF-8") + "=" + URLEncoder.encode(strings[4], "UTF-8");

            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = connection.getInputStream();
            inputStream.close();
            return "Registration Success!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Oops! Something is wrong";

        }
    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }


}
