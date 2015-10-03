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
 * Created by rossumg on 8/1/2015.
 */
class putMySQLPersonToAssessmentsTable extends AsyncTask<String, String, String> {

    private boolean LOGGED_IN = false;
    public SQLiteDatabase _db;
    DBHelper dbhelp;

    putMySQLPersonToAssessmentsTable(DBHelper dbhelp){
        this.dbhelp = dbhelp;
        this._db = dbhelp.getReadableDatabase();
    }

    @Override
    protected String doInBackground(String... args) {
        Log.d("request!", "putMySQLPersonToAssessmentsTable doInBackground ");

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
        String datatable = "PersonToAssessments";
        try {
            URL url = null;
            try {
                url = new URL(MainActivity.GET_TABLE_URL);
                Log.d("request!", "putMySQLPersonToAssessmentsTable GET_TABLE_URL " + url.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");
            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            data += "&" + URLEncoder.encode("datatable", "UTF-8") + "=" + URLEncoder.encode(datatable, "UTF-8");
//            data += "&" + URLEncoder.encode("rec0", "UTF-8") + "=" + URLEncoder.encode(rec0, "UTF-8");
//            data += "&" + URLEncoder.encode("rec1", "UTF-8") + "=" + URLEncoder.encode(rec1, "UTF-8");

            List<PersonToAssessments> personToAssessmentsList = dbhelp.getAllPersonToAssessments();
            Log.d("request!", "putMySQLPersonToAssessmentsTable build rec: " + personToAssessmentsList.size() );
            data += "&" + URLEncoder.encode("num_recs", "UTF-8") + "=" + URLEncoder.encode(Integer.toString(personToAssessmentsList.size()), "UTF-8");
            int i = 0;
            String[] recs = new String[personToAssessmentsList.size()];
            for (PersonToAssessments poa: personToAssessmentsList) {
                recs[i] =
                    Integer.toString(poa.get_person_id()) + "," +
                    Integer.toString(poa.get_facility_id()) + "," +
                    poa.get_date_created() + "," +
                    Integer.toString(poa.get_assessment_id()) + "," +
                    Integer.toString(poa.get_user_id());

                Log.d("request!", "loop: " + recs[i] + "<");

                //data += "&" + URLEncoder.encode(Integer.toString(i), "UTF-8") + "=" + URLEncoder.encode(rec, "UTF-8");
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
                Log.d("request!", "putMySQLPersonToAssessmentsTable Success: ");
            } else {
                Log.d("request!", "putMySQLPersonToAssessmentsTable: Not Successful");
                MainActivity._pass = "";
                LOGGED_IN = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("request!", "putMySQLPersonToAssessmentsTable exception > " + e.toString());
        }
        Log.d("request!", "putMySQLPersonToAssessmentsTable.doInBackground end");
        return null;
    }
}


