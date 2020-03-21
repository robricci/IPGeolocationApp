package com.icloud.ricci_roberto;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class SecondActivity extends AppCompatActivity {

    TextView text3, line1, line2, line3, line4, line5,line6, line7,
            line8, line9, line10, line11, line12, line13, line14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle extras = getIntent().getExtras();
        String inputAddress = extras.getString("KEY");

        TextView text1 = (TextView) findViewById(R.id.textView1);
        text1.setText(getIntent().getStringExtra("KEY"));

        String url = "http://ip-api.com/json/" + inputAddress;

        TextView text2 = (TextView) findViewById(R.id.textView2);
       // text2.setText(url);

         line1 = (TextView)findViewById(R.id.line1);
         line2 = (TextView)findViewById(R.id.line2);
         line3 = (TextView)findViewById(R.id.line3);
         line4 = (TextView)findViewById(R.id.line4);
         line5 = (TextView)findViewById(R.id.line5);
         line6 = (TextView)findViewById(R.id.line6);
         line7 = (TextView)findViewById(R.id.line7);
         line8 = (TextView)findViewById(R.id.line8);
         line9 = (TextView)findViewById(R.id.line9);
         line10 = (TextView)findViewById(R.id.line10);
         line11 = (TextView)findViewById(R.id.line11);
         line12 = (TextView)findViewById(R.id.line12);
         line13 = (TextView)findViewById(R.id.line13);
         line14 = (TextView)findViewById(R.id.line14);

        text3 = findViewById(R.id.textView3);




        new JsonTask().execute(url);
    }

    private class JsonTask extends AsyncTask<String, String, String> {


        protected String doInBackground(String... params) {


            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();


                InputStream stream = connection.getInputStream();

                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                    Log.d("Response: ", "> " + line);

                }

                return buffer.toString();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;

        }

        String lat = "";
        String lon = "";
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
           //  String lat = "";
           //  String lon = "";
           // text3.setText(result);
            try {
                JSONObject person = (new JSONObject(result));
                String s1 = person.getString("as");
                line1.setText("as: " + s1);
                String s2 = person.getString("city");
                line2.setText("city: " + s2);
                String s3 = person.getString("country");
                line3.setText("country: " + s3);
                String s4 = person.getString("countryCode");
                line4.setText("countryCode: " + s4);
                String s5 = person.getString("isp");
                line5.setText("isp: " + s5);
                String s6 = person.getString("lat");
                line6.setText("lat: " + s6);
                lat = s6;
                String s7 = person.getString("lon");
                line7.setText("lon: " + s7);
                lon = s7;
                String s8 = person.getString("org");
                line8.setText("org: " + s8);
                String s9 = person.getString("query");
                line9.setText("query: " + s9);
                String s10 = person.getString("region");
                line10.setText("region: " + s10);
                String s11 = person.getString("regionName");
                line11.setText("regionName: " + s11);
                String s12 = person.getString("status");
                line12.setText("status: " + s12);
                String s13 = person.getString("timezone");
                line13.setText("timezone: " + s13);
                String s14 = person.getString("zip");
                line14.setText("zip: " + s14);
/*

                lat.replaceAll(lat, s6);
                lon.replaceAll(lon, s7);


*/
            } catch (JSONException e) {
                e.printStackTrace();
            }

            final Button button = (Button) findViewById(R.id.button1);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String uri = "http://maps.google.com/maps?q=loc:" + lat + "," + lon;
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                    intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                    startActivity(intent);

                }
            });




        }



    }


   // final EditText addressText2 = (EditText) findViewById(R.id.address_edittext_2);
    // final Button button2 = (Button) findViewById(R.id.button2);
}

