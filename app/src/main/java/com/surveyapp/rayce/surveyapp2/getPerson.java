package com.surveyapp.rayce.surveyapp2;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by rossumg on 8/1/2015.
 */
class getPerson extends AsyncTask<String, String, String> {

    private boolean LOGGED_IN = false;

    @Override
    protected String doInBackground(String... args) {
        Log.d("request!", "getPerson doInBackground ");

        try {
            //Thread.sleep(4000); // 4 secs
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // TODO Auto-generated method stub
        // Check for success tag
        int success;
        String username = MainActivity._user;
        String password = MainActivity._pass;
        String datatable = "Person";
        try {
            URL url = null;
            try {
                url = new URL(MainActivity.GET_TABLE_URL);
                Log.d("request!", "getPerson person GET_TABLE_URL " + url.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            data += "&" + URLEncoder.encode("datatable", "UTF-8") + "=" + URLEncoder.encode(datatable, "UTF-8");
            JSONParser jsonParser = new JSONParser();
            String reply = jsonParser.makeHttpRequest(url, "POST", data);
            JSONObject json = new JSONObject(reply);
            success = json.getInt(MainActivity.TAG_SUCCESS);
            if (success == 1) {
                LOGGED_IN = true;
                int num_person_recs = json.getInt("number_records");
                JSONArray person_array = json.getJSONArray("posts");
                for (int i = 0; i< person_array.length(); i++) {
                    JSONObject person_rec = person_array.getJSONObject(i);
                    int person_id = person_rec.getInt("person_id");
                    // escape single quotes
                    String first_name = person_rec.getString("first_name");
                    first_name = first_name.replace("'","''");
                    String last_name = person_rec.getString("last_name");
                    last_name = last_name.replace("'","''");
                    int facility_id = person_rec.getInt("facility_id");
                    String facility_name = person_rec.getString("facility_name");
                    facility_name = facility_name.replace("'","''");
                    String personInsert =
                            "insert into person values("
                                    + person_id + ","
                                    + "'" + first_name + "'" + ","
                                    + "'" + last_name + "'" + ","
                                    + facility_id + ","
                                    + "'" + facility_name + "'" + ");";
                    try {
                        //Log.d("request!", "getPerson personInsert " + personInsert.toString());
                       MainActivity.db.execSQL(personInsert.toString());
                    } catch (Exception ex) {
                        Log.d("request!", "getPerson loop exception > " + personInsert);
                    }
                } // foreach
            } else {
                Log.d("request!", "Login Failed");
                LOGGED_IN = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("request!", "person exception>" + e.toString());
        }
        Log.d("request!", "getPerson.doInBackground end");
        return null;
    }
}


