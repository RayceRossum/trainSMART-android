package com.surveyapp.rayce.surveyapp2;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

class getMySQLAssessmentsQuestionsTable extends AsyncTask<String, String, String> {

    private boolean LOGGED_IN = false;

    @Override
    protected String doInBackground(String... args) {



        Log.d("request!", "getMySQLAssessmentsQuestionsTable.doInBackground ");

        try {
            //Thread.sleep(4000); // 4 secs
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // TODO Auto-generated method stub
        // Check for success tag
        int success;
        String username = MainActivity._user.toString();
        String password = MainActivity._pass.toString();
        String datatable = "AssessmentsQuestions";
        try {
            URL url = null;
            try {
                url = new URL(MainActivity.GET_TABLE_URL);
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
                int num_assessments_questions_recs = json.getInt("number_records");
                JSONArray assessments_questions_array = json.getJSONArray("posts");
                for (int i = 0; i< assessments_questions_array.length(); i++) {
                    JSONObject assessments_questions_rec = assessments_questions_array.getJSONObject(i);
                    int assessments_questions_id = assessments_questions_rec.getInt("assessments_questions_id");
                    // escape single quotes
                    int assessment_id = assessments_questions_rec.getInt("assessment_id");
                    String question = assessments_questions_rec.getString("question");
                    question = question.replace("'","''");
                    int itemorder = assessments_questions_rec.getInt("itemorder");
                    String itemtype = assessments_questions_rec.getString("itemtype");
                    itemtype = itemtype.replace("'","''");
                    int status = assessments_questions_rec.getInt("status");
                    String assessments_questionsInsert =
                            "insert into assessments_questions values("
                                    + assessments_questions_id + ","
                                    + assessment_id + ","
                                    + "'" + question + "'" + ","
                                    + itemorder + ","
                                    + "'" + itemtype + "'" + ","
                                    + status + ");";
                    try {
                        MainActivity.db.execSQL(assessments_questionsInsert.toString());
                    } catch (Exception ex) {
                        Log.d("request!", "getMySQLAssessmentsQuestionsTable loop exception > " + assessments_questionsInsert);
                    }
                } // for

            } else {
                Log.d("request!", "Login Failed");
                LOGGED_IN = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("request!", "assessments_questions exception>" + e.toString());
        }
        Log.d("request!", "getMySQLAssessmentsQuestionsTable.doInBackground end");
        return null;
    }

}