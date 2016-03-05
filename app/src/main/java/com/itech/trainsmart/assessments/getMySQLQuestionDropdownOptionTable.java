package com.itech.trainsmart.assessments;

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
class getMySQLQuestionDropdownOptionTable extends AsyncTask<String, String, String> {

    private boolean LOGGED_IN = false;
    public SQLiteDatabase _db;

    getMySQLQuestionDropdownOptionTable(DBHelper dbhelp){
        this._db = dbhelp.getWritableDatabase();
        this._db.execSQL("delete from question_dropdown_option");
    }

    @Override
    protected String doInBackground(String... args) {
        Log.d("request!", "getMySQLQuestionDropdownOptionTable doInBackground ");

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
        String datatable = "QuestionDropdownOption";
        try {
            URL url = null;
            try {
                url = new URL(MainActivity.GET_TABLE_URL);
                Log.d("request!", "getMySQLQuestionDropdownOptionTable GET_TABLE_URL " + url.toString());
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
                JSONArray question_dropdown_option_array = json.getJSONArray("posts");
                for (int i = 0; i< question_dropdown_option_array.length(); i++) {
                    JSONObject question_dropdown_option_rec = question_dropdown_option_array.getJSONObject(i);
                    int assessment_question_id = question_dropdown_option_rec.getInt("assessment_question_id");
                    // escape single quotes
                    String dropdown_option = question_dropdown_option_rec.getString("dropdown_option");
                    dropdown_option = dropdown_option.replace("'","''");

                    String questionDropdownOptionInsert =
                            "insert into question_dropdown_option values("
                                    + assessment_question_id + ","
                                    + "'" + dropdown_option + "'" + ","
                                    + null + ");";
                    try {
                        //Log.d("request!", "getMySQLPersonTable personInsert " + personInsert.toString());
                        _db.execSQL(questionDropdownOptionInsert.toString());
                    } catch (Exception ex) {
                        Log.d("request!", "getMySQLAssessmentsTable loop exception > " + questionDropdownOptionInsert);
                    }
                } // foreach
            } else {
                Log.d("request!", "Login Failed");
                MainActivity._pass = "";
                LOGGED_IN = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("request!", "question_dropdown_option exception>" + e.toString());
        }
        Log.d("request!", "getMySQLQuestionDropdownOptionTable.doInBackground end");
        return null;
    }
}
