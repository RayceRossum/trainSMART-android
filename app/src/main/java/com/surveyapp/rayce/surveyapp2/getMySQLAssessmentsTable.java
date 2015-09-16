package com.surveyapp.rayce.surveyapp2;

import android.database.sqlite.SQLiteDatabase;
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
class getMySQLAssessmentsTable extends AsyncTask<String, String, String> {

    private boolean LOGGED_IN = false;
    public SQLiteDatabase _db;

    getMySQLAssessmentsTable(DBHelper dbhelp){
        this._db = dbhelp.getWritableDatabase();
        this._db.execSQL("delete from assessments");
    }

    @Override
    protected String doInBackground(String... args) {
        Log.d("request!", "getMySQLAssessmentsTable doInBackground ");

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
        String datatable = "Assessments";
        try {
            URL url = null;
            try {
                url = new URL(MainActivity.GET_TABLE_URL);
                Log.d("request!", "getMySQLAssessmentsTable GET_TABLE_URL " + url.toString());
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
                int num_recs = json.getInt("number_records");
                JSONArray assessments_array = json.getJSONArray("posts");
                for (int i = 0; i< assessments_array.length(); i++) {
                    JSONObject assessments_rec = assessments_array.getJSONObject(i);
                    int assessment_id = assessments_rec.getInt("assessment_id");
                    // escape single quotes
                    String assessment_type = assessments_rec.getString("assessment_type");
                    assessment_type = assessment_type.replace("'","''");
                    int status = assessments_rec.getInt("status");

                    String assessmentInsert =
                            "insert into assessments values("
                                    + assessment_id + ","
                                    + "'" + assessment_type + "'" + ","
                                    + status + ");";
                    try {
                        //Log.d("request!", "getMySQLPersonTable personInsert " + personInsert.toString());
                        _db.execSQL(assessmentInsert.toString());
                    } catch (Exception ex) {
                        Log.d("request!", "getMySQLAssessmentsTable loop exception > " + assessmentInsert);
                    }
                } // foreach
            } else {
                Log.d("request!", "Login Failed");
                LOGGED_IN = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("request!", "assessments exception>" + e.toString());
        }
        Log.d("request!", "getMySQLAssessmentsTable.doInBackground end");
        return null;
    }
}
