package com.itech.trainsmart.assessments;

import android.util.Log;

/**
 * Created by rossumg on 9/4/2015.
 */
public class PersonToAssessments {

    //private variables
    int _rowid;
    int _person_to_assessments_id;
    int _person_id;
    int _facility_id;
    String _date_created;
    int _assessment_id;
    int _user_id;

    // Empty constructor
    public PersonToAssessments() {
    }

    public int get_person_to_assessments_id() {
        return _person_to_assessments_id;
    }

    public void set_person_to_assessments_id(int _person_to_assessments_id) {
        this._person_to_assessments_id = _person_to_assessments_id;
    }

    // constructor
    public PersonToAssessments(int rowid, int person_to_assessments_id, int person_id, int facility_id, String date_created, int assessment_id, int user_id) {
        this._rowid = rowid;
        this._person_to_assessments_id = person_to_assessments_id;
        this._person_id = person_id;
        this._facility_id = facility_id;
        this._date_created = date_created;
        this._assessment_id = assessment_id;
        this._user_id = user_id;
    }

    // constructor

    public PersonToAssessments(int person_to_assessments_id, int person_id, int facility_id, String date_created, int assessment_id, int user_id) {
        this._person_to_assessments_id = person_to_assessments_id;
        this._person_id = person_id;
        this._facility_id = facility_id;
        this._date_created = date_created;
        this._assessment_id = assessment_id;
        this._user_id = user_id;
    }

    public PersonToAssessments(int person_id, int facility_id, String date_created, int assessment_id, int user_id) {
        this._person_id = person_id;
        this._facility_id = facility_id;
        this._date_created = date_created;
        this._assessment_id = assessment_id;
        this._user_id = user_id;
    }

    public void dump() {
        Log.d("request!", "dumpPersonToAssessments: " +
                        this.get_person_to_assessments_id() + " " +
                        this.get_person_id() + " " +
                        this.get_facility_id() + " " +
                        this.get_date_created() + " " +
                        this.get_assessment_id() + " " +
                        this.get_user_id()
             );
    }

    public int get_rowid() {
        return _rowid;
    }

    public void set_rowid(int _rowid) {
        this._rowid = _rowid;
    }

    public int get_person_id() {
        return _person_id;
    }

    public void set_person_id(int _person_id) {
        this._person_id = _person_id;
    }

    public int get_facility_id() {
        return _facility_id;
    }

    public void set_facility_id(int _facility_id) {
        this._facility_id = _facility_id;
    }

    public String get_date_created() {
        return _date_created;
    }

    public void set_date_created(String _date_created) {
        this._date_created = _date_created;
    }

    public int get_assessment_id() {
        return _assessment_id;
    }

    public void set_assessment_id(int _assessment_id) {
        this._assessment_id = _assessment_id;
    }

    public int get_user_id() {
        return _user_id;
    }

    public void set_user_id(int _user_id) {
        this._user_id = _user_id;
    }
}
