package com.surveyapp.rayce.surveyapp2;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Rayce on 8/21/2015.
 */

public class DBHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "assessments.db";

    // assessments.db table names
    private static final String TABLE_ASSESSMENTS = "assessments";
    private static final String TABLE_ASSESSMENTS_ANSWERS = "assessments_answers";
    private static final String TABLE_ASSESSMENTS_QUESTIONS = "assessments_questions";
    private static final String TABLE_PERSON = "person";
    private static final String TABLE_PERSON_TO_ASSESSMENTS = "person_to_assessments";

    // assessments table column names
    private static final String ASSESSMENTS_ROWID = "rowid";
    private static final String ASSESSMENTS_ASSESSMENT_ID = "assessment_id";
    private static final String ASSESSMENTS_ASSESSMENT_TYPE = "assessment_type";
    private static final String ASSESSMENTS_STATUS = "status";

    // assessments_answers table column names
    private static final String ASSESSMENTS_ANSWERS_ASSESS_ID = "assess_id";
    private static final String ASSESSMENTS_ANSWERS_PERSON = "person";
    private static final String ASSESSMENTS_ANSWERS_FACILITY = "facility";
    private static final String ASSESSMENTS_ANSWERS_DATE_CREATED = "date_created";
    private static final String ASSESSMENTS_ANSWERS_ASSESSMENT_ID = "assessment_id";
    private static final String ASSESSMENTS_ANSWERS_QUESTION = "question";
    private static final String ASSESSMENTS_ANSWERS_ANSWER = "answer";
    private static final String ASSESSMENTS_ANSWERS_ACTIVE = "active";

    // assessments_questions table column names
    private static final String ASSESSMENTS_QUESTIONS_ROWID = "rowid";
    private static final String ASSESSMENTS_QUESTIONS_ASSESSMENTS_QUESTIONS_ID = "assessments_questions_id";
    private static final String ASSESSMENTS_QUESTIONS_ASSESSMENT_ID = "assessment_id";
    private static final String ASSESSMENTS_QUESTIONS_QUESTION = "question";
    private static final String ASSESSMENTS_QUESTIONS_ITEMORDER = "itemorder";
    private static final String ASSESSMENTS_QUESTIONS_ITEMTYPE = "itemtype";
    private static final String ASSESSMENTS_QUESTIONS_STATUS = "status";

    // person table column names
    private static final String PERSON_ROWID = "rowid";
    private static final String PERSON_PERSON_ID = "person_id";
    private static final String PERSON_FIRST_NAME = "first_name";
    private static final String PERSON_LAST_NAME = "last_name";
    private static final String PERSON_NATIONAL_ID = "national_id";
    private static final String PERSON_FACILITY_ID = "facility_id";
    private static final String PERSON_FACILITY_NAME = "facility_name";

    // person_to_assessments table column names
    private static final String PERSON_TO_ASSESSMENTS_PERSON_TO_ASSESSMENTS_ID = "person_to_assessments_id";
    private static final String PERSON_TO_ASSESSMENTS_PERSON_ID = "person_id";
    private static final String PERSON_TO_ASSESSMENTS_FACILITY_ID = "facility_id";
    private static final String PERSON_TO_ASSESSMENTS_DATE_CREATED = "date_created";
    private static final String PERSON_TO_ASSESSMENTS_ASSESSMENT_ID = "assessment_id";
    private static final String PERSON_TO_ASSESSMENTS_USER_ID = "user_id";
    private static final String PERSON_TO_ASSESSMENTS_STATUS = "status";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("request!", "DBHelper.onCreate");

        try { db.execSQL("delete from assessments;"); } catch(Exception ex) {}

        String CREATE_ASSESSEMNTS_TABLE = "CREATE TABLE IF NOT EXISTS assessments(assessment_id int, assessment_type varchar, status int);";
        db.execSQL(CREATE_ASSESSEMNTS_TABLE);

        //db.execSQL("delete from assessments_answers;");
        String CREATE_ASSESSEMNTS_ANSWERS_TABLE = "CREATE TABLE IF NOT EXISTS assessments_answers(" +
                "assess_id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE, " +
                        "person INTEGER, " +
                        "facility INTEGER, " +
                        "date_created DATETIME, " +
                        "assessment_id INTEGER, " +
                        "question VARCHAR, " +
                        "answer VARCHAR, " +
                        "active CHAR)";
        db.execSQL(CREATE_ASSESSEMNTS_ANSWERS_TABLE);

        try { db.execSQL("delete from assessments_questions;"); } catch(Exception ex) {}

        String CREATE_ASSESSEMNTS_QUESTIONS_TABLE = "CREATE TABLE IF NOT EXISTS assessments_questions(" +
                "assessments_questions_id int, " +
                "assessment_id int, " +
                "question varchar, " +
                "itemorder int, " +
                "itemtype varchar, " +
                "status int);";
        db.execSQL(CREATE_ASSESSEMNTS_QUESTIONS_TABLE);

        try { db.execSQL("delete from person;"); } catch(Exception ex) {}

        String CREATE_PERSON_TABLE = "CREATE TABLE IF NOT EXISTS person(person_id int, first_name varchar, last_name varchar, national_id varchar, facility_id int, facility_name varchar);";
        db.execSQL(CREATE_PERSON_TABLE);

        //db.execSQL("delete from person_to_assessments;");
        String CREATE_PERSON_TO_ASSESSEMNTS_TABLE = "CREATE  TABLE IF NOT EXISTS person_to_assessments(" +
                "person_to_assessments_id INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , " +
                        "person_id INTEGER, " +
                        "facility_id INTEGER, " +
                        "date_created DATETIME, " +
                        "assessment_id INTEGER, " +
                        "user_id INTEGER, " +
                        "status INTEGER);";
        db.execSQL(CREATE_PERSON_TO_ASSESSEMNTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older tables if exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSESSMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSESSMENTS_ANSWERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ASSESSMENTS_QUESTIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON_TO_ASSESSMENTS);

        // Create tables again
        onCreate(db);
    }

    public void helperTest(){

        Log.d("request!", "helperTest ");

        try {
            String databaseName = this.getDatabaseName();
            //SQLiteDatabase db = this.getReadableDatabase();
            //SQLiteDatabase db = this.getWritableDatabase();
            Person person0 = this.getPerson("Gregory", "Rossum", "%");

            //db.execSQL("drop table assessments");
            //db.execSQL("drop table person");
            //db.execSQL("drop table person_to_assessments");
            //db.execSQL("drop table assessments_answers");
            //db.execSQL("drop table assessments_questions");
            Log.d("request!", "helperTest databaseName> " + databaseName );
            Log.d("request!", "helperTest person0> "
                            + person0._rowid + " "
                            + person0._person_id + " "
                            + person0._first_name + " "
                            + person0._last_name + " "
                            + person0._national_id + " "
                            + person0._facility_id + " "
                            + person0._facility_name
            );

            Person person1 = new Person(999, "First", "Last", "national_id", 3, "All Souls' Clinic");
            this.addPerson(person1);

            List<Person> personList = this.getAllPersons();
            for (Person p : personList) {
                Log.d("request!", "helperTest personList> "
                                //+ p._rowid + " "
                                + p._person_id + " "
                                + p._first_name + " "
                                + p._last_name + " "
                                + p._national_id + " "
                                + p._facility_id + " "
                                + p._facility_name
                );
            }

            Log.d("request!", "helperTest personCount> " + this.getPersonsCount());

        } catch ( Exception ex) {
            Log.d("request!", "helperTest catch " + ex.toString());
        }
    }


    public void addPerson(Person person) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PERSON_PERSON_ID, person.getPersonId());
        values.put(PERSON_FIRST_NAME, person.getFirstName());
        values.put(PERSON_LAST_NAME, person.getLastName());
        values.put(PERSON_NATIONAL_ID, person.getNationalId());
        values.put(PERSON_FACILITY_ID, person.getFacilityId());
        values.put(PERSON_FACILITY_NAME, person.getFacilityName());

        db.insert(TABLE_PERSON, null, values);
        db.close();
    }

    public Person getPerson(String person_first_name, String person_last_name, String person_national_id) {

        SQLiteDatabase db = this.getReadableDatabase();

        String[] tableColumns = new String[] {
                PERSON_ROWID, PERSON_PERSON_ID, PERSON_FIRST_NAME, PERSON_LAST_NAME, PERSON_NATIONAL_ID, PERSON_FACILITY_ID, PERSON_FACILITY_NAME
        };

        String whereClause = "1=1 and " +
                PERSON_FIRST_NAME + " like ? and " +
                PERSON_LAST_NAME + " like ? and " +
                PERSON_NATIONAL_ID + " like ?";

        String[] whereArgs = new String [] {
                person_first_name, person_last_name, person_national_id };

        Cursor cursor = db.query(TABLE_PERSON, tableColumns, whereClause, whereArgs, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        Log.d("request!", "getPerson  "
                + cursor.getString(0) + " "
                + cursor.getString(1) + " "
                + cursor.getString(2) + " "
                + cursor.getString(3) + " "
                + cursor.getString(4) + " "
                + cursor.getString(5) + " "
                + cursor.getString(6) + " "
        );

        Person person = new Person(
                Integer.parseInt(cursor.getString(0)),
                Integer.parseInt(cursor.getString(1)),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                Integer.parseInt(cursor.getString(5)),
                cursor.getString(6)
        );
        cursor.close();
        db.close();
        return person;
    }

    public List<Person> getAllPersons() {
        List<Person> personList = new ArrayList<Person>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PERSON;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Person person = new Person();
                //person.setRowId(Integer.parseInt(cursor.getString(0)));
                person.setPersonId(Integer.parseInt(cursor.getString(0)));
                person.setFirstName(cursor.getString(1));
                person.setLastName(cursor.getString(2));
                person.setNationalId(cursor.getString(3));
                person.setFacilityId(Integer.parseInt(cursor.getString(4)));
                person.setFacilityName(cursor.getString(5));

                // Adding person to list
                personList.add(person);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        // return person list
        return personList;
    }


    public int getPersonsCount() {
        Log.d("request!", "getPersonsCount0");
        String countQuery = "SELECT  * FROM " + TABLE_PERSON;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        // return count
        int returnVal = cursor.getCount();
        cursor.close();
        db.close();
        return returnVal;
    }

  /*
public int updatePerson(Person person) {
    SQLiteDatabase db = this.getWritableDatabase();

    ContentValues values = new ContentValues();
    values.put(KEY_NAME, person.getName());
    values.put(KEY_PH_NO, person.getPhoneNumber());

    // updating row
    return db.update(TABLE_PERSON, values, KEY_ID + " = ?",
            new String[] { String.valueOf(person.getID()) });
}

public void deletePerson(Person person) {
    SQLiteDatabase db = this.getWritableDatabase();
    db.delete(TABLE_PERSON, KEY_ID + " = ?",
            new String[] { String.valueOf(person.getID()) });
    db.close();
}
*/

    public void dropDatabase() {

    }


    public void downloadDBData() {

//        Log.d("request!", "load person_to_assessments ");
//        load_person_to_assessments();
//
//        Log.d("request!", "load assessments_answers ");
//        load_assessments_answers();


        Log.d("request!", "downloadDBData getMySQLPersonTable");

        new getMySQLPersonTable(this).execute();
        //new getMySQLAssessmentsQuestionsTable().execute();
        //new getMySQLAssessmentsTable().execute();

    }

    protected void load_person_to_assessments() {

        MainActivity.db.execSQL("delete from person_to_assessments ");
        MainActivity.db.execSQL("insert into person_to_assessments values (1,1,1,\"2015-07-07\",2,1,0);");
        MainActivity.db.execSQL("insert into person_to_assessments values (2,1,1,\"2015-09-07\",2,1,0);");
        MainActivity.db.execSQL("insert into person_to_assessments values (3,1,1,\"2015-11-07\",2,1,0);");
        MainActivity.db.execSQL("insert into person_to_assessments values (4,1,1,\"2015-07-07\",3,1,0);");
        MainActivity.db.execSQL("insert into person_to_assessments values (5,1,1,\"2015-09-07\",3,1,0);");
        MainActivity.db.execSQL("insert into person_to_assessments values (6,1,1,\"2015-07-13\",2,1,0);");
        MainActivity.db.execSQL("insert into person_to_assessments values (7,16,3,\"2015-07-13\",2,1,0);");
        MainActivity.db.execSQL("insert into person_to_assessments values (8,42,417,\"2015-07-16\",4,1,0);");
        MainActivity.db.execSQL("insert into person_to_assessments values (14,42,417,\"2015-07-17\",4,1,0);");
        MainActivity.db.execSQL("insert into person_to_assessments values (15,42,417,\"2015-07-17\",3,1,0);");
        MainActivity.db.execSQL("insert into person_to_assessments values (16,42,417,\"2015-07-17\",2,1,0);");
        MainActivity.db.execSQL("insert into person_to_assessments values (17,42,417,\"2015-07-20\",15,1,0);");
        MainActivity.db.execSQL("insert into person_to_assessments values (18,42,417,\"2015-07-21\",15,1,0);");

    }

    protected void load_assessments_answers() {

        MainActivity.db.execSQL("delete from assessments_answers ");
        MainActivity.db.execSQL("insert into assessments_answers values (3343,1,1,\"2015-07-07\",2,14,\"A\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3345,1,1,\"2015-07-07\",2,16,\"B\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3346,1,1,\"2015-07-07\",2,17,\"C\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3347,1,1,\"2015-07-07\",2,18,\"D\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3348,1,1,\"2015-07-07\",2,19,\"E\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3350,1,1,\"2015-07-07\",2,21,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3351,1,1,\"2015-07-07\",2,22,\"3.2. save 2\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3352,1,1,\"2015-07-07\",2,23,\"3.3. 2015-07-07\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3353,1,1,\"2015-07-07\",2,24,\"3.4.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3354,1,1,\"2015-07-07\",2,25,\"3.5.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3355,1,1,\"2015-07-07\",2,26,\"3.6.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3356,1,1,\"2015-07-07\",2,27,\"3.7.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3357,1,1,\"2015-07-07\",2,28,\"3.8.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3358,1,1,\"2015-07-07\",2,29,\"3.9.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3359,1,1,\"2015-07-07\",2,30,\"3.10\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3361,1,1,\"2015-07-07\",2,32,\"4.1.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3362,1,1,\"2015-07-07\",2,33,\"4.2.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3363,1,1,\"2015-07-07\",2,34,\"4.3.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3364,1,1,\"2015-07-07\",2,36,\"4.5.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3374,1,1,\"2015-07-07\",2,38,\"4.7.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3377,1,1,\"2015-07-07\",2,40,\"5.1.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3378,1,1,\"2015-07-07\",2,35,\"4.4.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3379,1,1,\"2015-07-07\",2,37,\"4.6.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3380,1,1,\"2015-09-07\",2,14,\"A\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3381,1,1,\"2015-09-07\",2,16,\"B\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3382,1,1,\"2015-09-07\",2,17,\"C\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3383,1,1,\"2015-09-07\",2,18,\"D\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3384,1,1,\"2015-09-07\",2,19,\"E\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3385,1,1,\"2015-09-07\",2,21,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3386,1,1,\"2015-09-07\",2,22,\"3.2. save\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3387,1,1,\"2015-09-07\",2,23,\"3.3.  2015-09-07\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3388,1,1,\"2015-09-07\",2,24,\"3.4.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3389,1,1,\"2015-09-07\",2,25,\"3.5.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3390,1,1,\"2015-09-07\",2,26,\"3.6.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3391,1,1,\"2015-09-07\",2,27,\"3.7.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3392,1,1,\"2015-09-07\",2,28,\"3.8.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3393,1,1,\"2015-09-07\",2,29,\"3.9.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3394,1,1,\"2015-09-07\",2,30,\"3.10\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3395,1,1,\"2015-09-07\",2,32,\"4.1.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3396,1,1,\"2015-09-07\",2,33,\"4.2.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3397,1,1,\"2015-09-07\",2,34,\"4.3.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3398,1,1,\"2015-09-07\",2,36,\"4.5.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3399,1,1,\"2015-09-07\",2,38,\"4.7.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3400,1,1,\"2015-09-07\",2,40,\"5.1.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3401,1,1,\"2015-07-07\",3,42,\"A\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3402,1,1,\"2015-07-07\",3,43,\"test comment loooooooooooooooooooooooooooooooooooooooooooooog comment\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3403,1,1,\"2015-07-07\",3,44,\"B\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3404,1,1,\"2015-07-07\",3,46,\"C\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3405,1,1,\"2015-07-07\",3,48,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3406,1,1,\"2015-07-07\",3,50,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3407,1,1,\"2015-07-07\",3,52,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3408,1,1,\"2015-07-07\",3,54,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3409,1,1,\"2015-07-07\",3,56,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3410,1,1,\"2015-07-07\",3,58,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3411,1,1,\"2015-07-07\",3,60,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3412,1,1,\"2015-07-07\",3,62,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3413,1,1,\"2015-07-07\",3,64,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3414,1,1,\"2015-07-07\",3,66,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3415,1,1,\"2015-07-07\",3,68,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3416,1,1,\"2015-09-07\",2,35,\"4.4. \",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3417,1,1,\"2015-09-07\",2,37,\"4.6.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3418,1,1,\"2015-11-07\",2,14,\"A\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3419,1,1,\"2015-11-07\",2,16,\"B\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3420,1,1,\"2015-11-07\",2,17,\"C\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3421,1,1,\"2015-11-07\",2,18,\"D\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3422,1,1,\"2015-11-07\",2,19,\"E\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3423,1,1,\"2015-11-07\",2,21,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3424,1,1,\"2015-11-07\",2,40,\"2015-11-07\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3425,1,1,\"2015-11-07\",2,22,\"3.1.\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3426,1,1,\"2015-11-07\",2,23,\"2015-11-07\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3427,1,1,\"2015-07-07\",3,45,\"test 2\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3428,1,1,\"2015-07-07\",3,47,\"test 3\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3450,1,1,\"2015-07-13\",3,42,\"A\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3449,1,1,\"2015-07-13\",2,40,\"test test test\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3448,1,1,\"2015-07-13\",2,23,\"3.2 2015-07-13\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3480,1,1,\"2015-07-13\",2,22,\"3.1 update\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3446,1,1,\"2015-07-13\",2,21,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3445,1,1,\"2015-07-13\",2,19,\"E\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3444,1,1,\"2015-07-13\",2,18,\"D\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3443,1,1,\"2015-07-13\",2,17,\"C\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3442,1,1,\"2015-07-13\",2,16,\"B\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3441,1,1,\"2015-07-13\",2,14,\"A\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3451,1,1,\"2015-07-13\",3,43,\"comment 1 test looooooooooooooooooooooooooooooooooooooooooooooooooooooooog comment\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3452,1,1,\"2015-07-13\",3,44,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3453,1,1,\"2015-07-13\",3,46,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3454,1,1,\"2015-07-13\",3,48,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3455,1,1,\"2015-07-13\",3,50,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3456,1,1,\"2015-07-13\",3,52,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3457,1,1,\"2015-07-13\",3,54,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3458,1,1,\"2015-07-13\",3,56,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3459,1,1,\"2015-07-13\",3,58,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3460,1,1,\"2015-07-13\",3,60,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3461,1,1,\"2015-07-13\",3,62,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3462,1,1,\"2015-07-13\",3,64,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3463,1,1,\"2015-07-13\",3,66,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3464,1,1,\"2015-07-13\",3,68,\"A\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3465,1,1,\"2015-07-13\",3,69,\"comment 14\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3466,1,1,\"2015-07-07\",3,49,\"test 4\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3467,16,3,\"2015-07-13\",2,14,\"A\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3468,16,3,\"2015-07-13\",2,16,\"B\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3469,16,3,\"2015-07-13\",2,17,\"C\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3470,16,3,\"2015-07-13\",2,18,\"D\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3471,16,3,\"2015-07-13\",2,19,\"E\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3472,16,3,\"2015-07-13\",2,21,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3473,42,417,\"2015-07-16\",4,136,\"F\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3474,42,417,\"2015-07-20\",15,135,\"This is a test answer for q1\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3475,42,417,\"2015-07-20\",15,137,\"This is a textbox answer for q2\",\"Y\");");

//		MainActivity.db.execSQL("insert into assessments_answers values (3476,42,417,\"2015-07-20\",15,138,\"This is a long test answer for q2a
//				This is a long test answer for q2b
//		This is a long test answer for q2c
//		This is a long test answer for q2d
//		This is a long test answer for q2e
//		This is a long test answer for q2f
//		This is a long test answer for q2a
//		T...\",\"Y\");");

        MainActivity.db.execSQL("insert into assessments_answers values (3477,42,417,\"2015-07-21\",15,135,\"textarea q1\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3478,42,417,\"2015-07-21\",15,137,\"-\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3479,42,417,\"2015-07-21\",15,138,\"textbox q2\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3481,42,417,\"2015-07-21\",15,140,\"textarea q3\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3482,42,417,\"2015-07-21\",15,139,\"-\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3483,42,417,\"2015-07-21\",15,141,\"-\",\"Y\");");
        MainActivity.db.execSQL("insert into assessments_answers values (3484,42,417,\"2015-07-21\",15,142,\"F\",\"Y\");");
    }



    public String[][] getQuestionData(int personID, int facilityID, int date, int assessmentID) {
        //String query = "select * from person";
//        String query = "select " +
//                "aq.question, " +
//                "aq.itemtype, " +

//                "(select aa.answer from assessments_answers aa where aa.person = pa.person_id and aa.facility = pa.facility_id and aa.date_created = " +
//                "pa.date_created and a.assessment_id = aq.assessment_id  and aa.question = aq.assessments_questions_id) as answer " +
//                "from person_to_assessments pa " +
//                "join person p on p.person_id = pa.person_id " +
//                "join assessments a on pa.assessment_id = a.assessment_id " +
//                "join assessments_questions aq on a.assessment_id = aq.assessment_id " +
//                "where 1=1 " +
//                " and pa.person_id = " + personID +
//                " and pa.facility_id = " + facilityID +
//                " and pa.date_created = " + "\'2015-07-07\'" +
//                " and pa.assessment_id = " + assessmentID +
//                " and aq.status = 1 " +
//                "order by aq.itemorder; ";
//        Log.d("Query: ", query);

//        Cursor c = MainActivity.db.rawQuery(query, null);
//        while (c.moveToNext()) {
//
//        }
//        c.close();
        int question = 0;
        int itemtype = 1;
        int answer = 2;
        String[][] questionData = new String[30][3];

        String[] itemTypes = {"text", "question110", "questiontext", "questionyesno", "questionmulti"};
        Random r = new Random();

        questionData[0][question] = "What time is it?";
        questionData[0][itemtype] = "title";
        questionData[0][answer] = "3:00PM";


        for (int i=1; i<30; i++) {
            questionData[i][question] = "What time is it?";
            questionData[i][itemtype] = itemTypes[r.nextInt(itemTypes.length)];
            questionData[i][answer] = "3:00PM";
        }

//        keyData[0] = c.getString(0);
//        keyData[1] = c.getString(1);
//        keyData[2] = c.getString(2);
//        keyData[3] = c.getString(3);

//        c.close();
        return questionData;
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




