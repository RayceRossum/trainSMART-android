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
    public Person(){

    }
    // constructor
    public Person(int rowid, int person_id, String first_name, String last_name, String national_id, int facility_id, String facility_name){
        this._rowid = rowid;
        this._person_id = person_id;
        this._first_name = first_name;
        this._last_name = last_name;
        this._national_id = national_id;
        this._facility_id = facility_id;
        this._facility_name = facility_name;
    }

    // constructor
    public Person(int person_id, String first_name, String last_name, String national_id, int facility_id, String facility_name){
        this._person_id = person_id;
        this._first_name = first_name;
        this._last_name = last_name;
        this._national_id = national_id;
        this._facility_id = facility_id;
        this._facility_name = facility_name;
    }

    // getting ID
    public int getRowId(){
        return this._rowid;
    }

    // setting id
    public void setRowId(int rowid){
        this._rowid = rowid;
    }

    // getting person_id
    public int getPersonId(){
        return this._person_id;
    }

    // setting id
    public void setPersonId(int person_id){
        this._person_id = person_id;
    }

    // getting first_name
    public String getFirstName(){
        return this._first_name;
    }

    // getting last_name
    public String getLastName(){
        return this._last_name;
    }

    // setting first_name
    public void setFirstName(String first_name){
        this._first_name = first_name;
    }

    // setting last_name
    public void setLastName(String last_name){
        this._last_name = last_name;
    }

    // getting national_id
    public String getNationalId(){
        return this._national_id;
    }

    // setting national_id
    public void setNationalId(String national_id){
        this._national_id = national_id;
    }

    // getting facility_id
    public int getFacilityId(){
        return this._facility_id;
    }

    // setting facility_id
    public void setFacilityId(int facility_id){
        this._facility_id = facility_id;
    }

    // getting facility_name
    public String getFacilityName(){
        return this._facility_name;
    }

    // setting facility_name
    public void setFacilityName(String facility_name){
        this._facility_name = facility_name;
    }
}
