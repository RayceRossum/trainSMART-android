package com.surveyapp.rayce.surveyapp2;

/**
 * Created by rossumg on 8/24/2015.
 */
public class Person {

    //private variables
    int _rowid;
    int _person_id;
    String _first_name;
    String _last_name;
    String _national_id;
    int _facility_id;
    String _facility_name;

    // Empty constructor
    public Person() {

    }

    // constructor
    public Person(int rowid, int person_id, String first_name, String last_name, String national_id, int facility_id, String facility_name) {
        this._rowid = rowid;
        this._person_id = person_id;
        this._first_name = first_name;
        this._last_name = last_name;
        this._national_id = national_id;
        this._facility_id = facility_id;
        this._facility_name = facility_name;
    }

    // constructor
    public Person(int person_id, String first_name, String last_name, String national_id, int facility_id, String facility_name) {
        this._person_id = person_id;
        this._first_name = first_name;
        this._last_name = last_name;
        this._national_id = national_id;
        this._facility_id = facility_id;
        this._facility_name = facility_name;
    }


    public String get_facility_name() {
        return _facility_name;
    }

    public void set_facility_name(String _facility_name) {
        this._facility_name = _facility_name;
    }

    public int get_facility_id() {

        return _facility_id;
    }

    public void set_facility_id(int _facility_id) {
        this._facility_id = _facility_id;
    }

    public String get_national_id() {

        return _national_id;
    }

    public void set_national_id(String _national_id) {
        this._national_id = _national_id;
    }

    public String get_last_name() {

        return _last_name;
    }

    public void set_last_name(String _last_name) {
        this._last_name = _last_name;
    }

    public String get_first_name() {

        return _first_name;
    }

    public void set_first_name(String _first_name) {
        this._first_name = _first_name;
    }

    public int get_person_id() {

        return _person_id;
    }

    public void set_person_id(int _person_id) {
        this._person_id = _person_id;
    }

    public int get_rowid() {

        return _rowid;
    }

    public void set_rowid(int _rowid) {
        this._rowid = _rowid;
    }
}