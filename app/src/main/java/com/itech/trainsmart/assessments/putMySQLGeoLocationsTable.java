package com.itech.trainsmart.assessments;

import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by rossumg on 9/28/2015.
 */
class putMySQLGeoLocationsTable extends AsyncTask<String, String, String> {

    private boolean LOGGED_IN = false;
    public SQLiteDatabase _db;
    DBHelper dbhelp;

    putMySQLGeoLocationsTable(DBHelper dbhelp){
        this.dbhelp = dbhelp;
        this._db = dbhelp.getReadableDatabase();
    }

    @Override
    protected String doInBackground(String... args) {
        Log.d("request!", "putMySQLGeoLocationTable doInBackground ");

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
        String datatable = "GeoLocations";
        try {
            URL url = null;
            try {
                url = new URL(MainActivity.GET_TABLE_URL);
                Log.d("request!", "putMySQLGeoLocationsTable GET_TABLE_URL " + url.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            data += "&" + URLEncoder.encode("datatable", "UTF-8") + "=" + URLEncoder.encode(datatable, "UTF-8");

            List<GeoLocations> geoLocationsList = dbhelp.getAllGeoLocations();
            Log.d("request!", "putMySQLGeoLocationsTable build rec: " + geoLocationsList.size() );
            data += "&" + URLEncoder.encode("num_recs", "UTF-8") + "=" + URLEncoder.encode(Integer.toString(geoLocationsList.size()), "UTF-8");
            int i = 0;
            String[] recs = new String[geoLocationsList.size()];
            for (GeoLocations geoLocations: geoLocationsList) {
                recs[i] =
                        Integer.toString(geoLocations.get_longitude()) + "," +
                                Integer.toString(geoLocations.get_latitude()) + "," +
                                geoLocations.get_device_id() + "," +
                                geoLocations.get_created_at() + "," +
                                geoLocations.get_username() + "," +
                                geoLocations.get_password();

                                Log.d("request!", "loop: " + recs[i] + "<");

                data += "&" + URLEncoder.encode("recs"+Integer.toString(i), "UTF-8") + "=" + URLEncoder.encode(recs[i], "UTF-8");
                i++;
            }

            JSONParser jsonParser = new JSONParser();
            String reply = jsonParser.makeHttpRequest(url, "POST", data);
            JSONObject json = new JSONObject(reply);
            success = json.getInt(MainActivity.TAG_SUCCESS);
            if (success == 1) {
                LOGGED_IN = true;
                //int num_person_recs = json.getInt("number_records");
                Log.d("request!", "putMySQLGeoLocationsTable Success: ");
            } else {
                Log.d("request!", "putMySQLGeoLocationTable: Not Successful");
                LOGGED_IN = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("request!", "putMySQLGeoLocationsTable exception > " + e.toString());
        }
        Log.d("request!", "putMySQLGeoLocationsTable.doInBackground end");
        return null;
    }
}


