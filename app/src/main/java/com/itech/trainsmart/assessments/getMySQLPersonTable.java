package com.itech.trainsmart.assessments;

import android.app.NotificationManager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by rossumg on 8/1/2015.
 */
class getMySQLPersonTable extends AsyncTask<String, String, String> {

    private boolean LOGGED_IN = false;
    public Context _context;
    public SQLiteDatabase _db;

    getMySQLPersonTable(Context context, DBHelper dbhelp){
        this._context = context;
        this._db = dbhelp.getWritableDatabase();
        this._db.execSQL("delete from person");
    }

    @Override
    protected String doInBackground(String... args) {
        Log.d("request!", "getMySQLPersonTable doInBackground ");

        try {
            //Thread.sleep(4000); // 4 secs
            Thread.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // TODO Auto-generated method stub
        // Check for success tag
        int success;
        int i = 0;

        String username = MainActivity._user;
        String password = MainActivity._pass;
        Log.d("request!", "getMySQLPersonTable username/password: " + username + " " + password);
        String datatable = "Person";
        try {
            URL url = null;
            try {
                url = new URL(MainActivity.GET_TABLE_URL);
                Log.d("request!", "getMySQLPersonTable GET_TABLE_URL " + url.toString());
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
            int num_person_recs;
            if (success == 1) {
                LOGGED_IN = true;
                num_person_recs = json.getInt("number_records");
                JSONArray person_array = json.getJSONArray("posts");

                NotificationManager mNotifyManager = (NotificationManager) this._context.getSystemService(this._context.NOTIFICATION_SERVICE);
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this._context);

                mBuilder.setContentTitle("Data Download")
                        .setContentText("Download in progress")
                        .setSmallIcon(R.drawable.download);

                int id = 1;

                for (i = 0; i< person_array.length(); i++) {
                    JSONObject person_rec = person_array.getJSONObject(i);
                    int person_id = person_rec.getInt("person_id");
                    // escape single quotes
                    String first_name = person_rec.getString("first_name");
                    first_name = first_name.replace("'","''");
                    String last_name = person_rec.getString("last_name");
                    last_name = last_name.replace("'","''");
                    int facility_id = person_rec.getInt("facility_id");
                    String national_id = person_rec.getString("national_id");
                    String facility_name = person_rec.getString("facility_name");
                    facility_name = facility_name.replace("'","''");
                    String personInsert =
                            "insert into person values("
                                    + person_id + ","
                                    + "'" + first_name + "'" + ","
                                    + "'" + last_name + "'" + ","
                                    + "'" + national_id + "'" + ","
                                    + facility_id + ","
                                    + "'" + facility_name + "'" + ");";
                    try {
                        // no longer used
                        //int progress = (int)((i / (float) num_person_recs) * 100);
                        //publishProgress(Integer.toString(progress));

                        int incr = (int)((i / (float) num_person_recs) * 100);
                        mBuilder.setProgress(100, incr, false);
                        mNotifyManager.notify(id, mBuilder.build());

                        //Log.d("request!", "getMySQLPersonTable personInsert " + personInsert.toString() + " " + i);
                        //Log.d("request!", "getMySQLPersonTable personInsert " + " " + i);
                        _db.execSQL(personInsert.toString());
                    } catch (Exception ex) {
                        Log.d("request!", "getMySQLPersonTable loop exception > " + ex.toString());
                    }
                } // foreach
                mBuilder.setContentText("Download complete (" + i + " records)").setProgress(0, 0, false);
                mNotifyManager.notify(id, mBuilder.build());
            } else {
                Log.d("request!", "Login Failed");
                Toast.makeText(this._context, "Login Failed", Toast.LENGTH_LONG).show();
                MainActivity._pass = "";
                LOGGED_IN = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("request!", "getMySQLPersonTable exception > " + e.toString());
        }
        Log.d("request!", "getMySQLPersonTable.doInBackground end");
        return Integer.toString(i);
    }

// no longer used
//
//    String displayed = "";
//    protected void onProgressUpdate(String... progress) {
//        //Log.d("request!", "onProgressUpdate: " + progress[0]);
//        Toast toast = Toast.makeText(this._context, progress[0] + "% downloaded", Toast.LENGTH_SHORT);
//        if(!displayed.equals(progress[0]) && Integer.parseInt(progress[0])%5 == 0 ){
//            toast.show();
//            displayed = progress[0];
//        }
//    }
//

    protected void onPostExecute(String result) {
        Log.d("request!", "onPostExecute: " + result);
        //Toast.makeText(this._context, "Downloaded " + result + " persons", Toast.LENGTH_LONG).show();
        Toast.makeText(this._context, "Download Complete", Toast.LENGTH_LONG).show();
    }

}


