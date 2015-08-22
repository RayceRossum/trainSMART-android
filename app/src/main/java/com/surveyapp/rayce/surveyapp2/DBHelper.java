package com.surveyapp.rayce.surveyapp2;

import android.database.Cursor;
import android.util.Log;

/**
 * Created by Rayce on 8/21/2015.
 */
public class DBHelper {


    public void downloadDBData() {
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

    public String[] getKeyData(int personID, int facilityID, int date, int assessmentID) {
        String[] keyData = new String[4];
//        Cursor c = MainActivity.db.rawQuery("select " +
//                "aq.question," +
//                "aq.itemtype" +
//                "(select aa.answer from assessments_answers aa where aa.person = pa.person_id and aa.facility = pa.facility_id and aa.date_created = " +
//                "pa.date_created and a.assessment_id = aq.assessment_id  and aa.question = aq.assessments_questions_id) as answer" +
//                "from person_to_assessments pa" +
//                "join person p on p.person_id = pa.person_id" +
//                "join assessments a on pa.assessment_id = a.assessment_id" +
//                "join assessments_questions aq on a.assessment_id = aq.assessment_id" +
//                "where 1=1" +
//                "and pa.person_id = " + personID +
//                "and pa.facility_id = " + facilityID +
//                "and pa.data_created = " + "2015-07-07" +
//                "and pa.assessment_id = " + assessmentID +
//                "and aq.status = 1" +
//                "order by aq.itemorder", null);

        keyData[0] = "text";
        keyData[1] = "question110";
        keyData[2] = "question110";
        keyData[3] = "question110";

//        keyData[0] = c.getString(0);
//        keyData[1] = c.getString(1);
//        keyData[2] = c.getString(2);
//        keyData[3] = c.getString(3);

//        c.close();
        return keyData;
    }
}

//        select
//        aq.question,
//                aq.itemtype,
//                (select aa.answer from assessments_answers aa where aa.person =
//                pa.person_id and aa.facility = pa.facility_id and aa.date_created =
//                pa.date_created and a.assessment_id = aq.assessment_id  and aa.question
//                = aq.assessments_questions_id) as answer
//        from person_to_assessments pa
//        join person p on p.person_id = pa.person_id
//        join assessments a on pa.assessment_id = a.assessment_id
//        join assessments_questions aq on a.assessment_id = aq.assessment_id
//        where  1=1
//        and pa.person_id = 1
//        and pa.facility_id = 1
//        and pa.date_created = "2015-07-07"
//        and pa.assessment_id = 2
//        and aq.status = 1
//                -- and aa.active = 'Y'
//                -- and aa.question = 14
//        order by aq.itemorder




