package com.surveyapp.rayce.surveyapp2;

import android.util.Log;

/**
 * Created by Rayce on 8/21/2015.
 */
public class DBHelper {
    public void downloadDBData(){
        MainActivity.db.execSQL("CREATE TABLE IF NOT EXISTS person(person_id int, first_name varchar, last_name varchar, facility_id int, facility_name varchar);");
        MainActivity.db.execSQL("delete from person;");
        MainActivity.db.execSQL("CREATE TABLE IF NOT EXISTS assessments_questions(" +
                "assessments_questions_id int, " +
                "assessment_id int, " +
                "question varchar, " +
                "itemorder int, " +
                "itemtype varchar, " +
                "status int);");
        MainActivity.db.execSQL("delete from assessments_questions;");
        MainActivity.db.execSQL("CREATE TABLE IF NOT EXISTS assessments(assessment_id int, assessment_type varchar, status int);");
        MainActivity.db.execSQL("delete from assessments;");
        Log.d("request!", "create assessments_answers ");
        //MainActivity.db.execSQL("drop table assessments_answers");
        MainActivity.db.execSQL("CREATE TABLE IF NOT EXISTS assessments_answers(" +
                "assess_id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE, " +
                "person INTEGER, " +
                "facility INTEGER, " +
                "date_created DATETIME, " +
                "assessment_id INTEGER, " +
                "question VARCHAR, " +
                "answer VARCHAR, " +
                "active CHAR);");
        Log.d("request!", "created assessments_answers ");
        MainActivity.db.execSQL("CREATE  TABLE IF NOT EXISTS person_to_assessments(" +
                "person_to_assessments_id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , " +
                "person_id INTEGER, " +
                "facility_id INTEGER, " +
                "date_created DATETIME, " +
                "assessment_id INTEGER, " +
                "user_id INTEGER, " +
                "status INTEGER);");

//                Log.d("request!", "load person_to_assessments ");
//                load_person_to_assessments();
//
//                Log.d("request!", "load assessments_answers ");
//                //load_assessments_answers();

        new getPerson().execute();
        new getAssessmentsQuestions().execute();
        new getAssessments().execute();
    }
}
