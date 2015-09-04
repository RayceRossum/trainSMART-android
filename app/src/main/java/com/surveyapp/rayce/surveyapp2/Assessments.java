package com.surveyapp.rayce.surveyapp2;

/**
 * Created by rossumg on 9/4/2015.
 */
public class Assessments {

    //private variables
    int _rowid;
    int _assessment_id;
    String _assessment_type;

    public Assessments(int _assessment_id, String _assessment_type) {
        this._assessment_id = _assessment_id;
        this._assessment_type = _assessment_type;
    }

    public Assessments(int _rowid, int _assessment_id, String _assessment_type) {

        this._rowid = _rowid;
        this._assessment_id = _assessment_id;
        this._assessment_type = _assessment_type;
    }

    // Empty constructor
    public Assessments() {
    }

    public String get_assessment_type() {
        return _assessment_type;
    }

    public void set_assessment_type(String _assessment_type) {
        this._assessment_type = _assessment_type;
    }

    public int get_assessment_id() {

        return _assessment_id;
    }

    public void set_assessment_id(int _assessment_id) {
        this._assessment_id = _assessment_id;
    }

    public int get_rowid() {

        return _rowid;
    }

    public void set_rowid(int _rowid) {
        this._rowid = _rowid;
    }
}
