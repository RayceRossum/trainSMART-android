package com.itech.trainsmart.assessments;

import android.util.Log;

/**
 * Created by rossumg on 9/8/2015.
 */
public class AssessmentsAnswers {

    //private variables
    int _assess_id;
    int _person;
    int _facility;
    String _date_created;
    int _assessment_id;
    int _question;
    String _answer;

    public void dump() {
        Log.d("request!", "dumpAssessmentsAnswers: " +
                        this.get_assess_id() + " " +
                        this.get_person() + " " +
                        this.get_facility() + " " +
                        this.get_date_created() + " " +
                        this.get_assessment_id() + " " +
                        this.get_question() + " " +
                        this.get_answer()
        );
    }

    public int get_assess_id() {
        return _assess_id;
    }

    public void set_assess_id(int _assess_id) {
        this._assess_id = _assess_id;
    }

    public int get_person() {
        return _person;
    }

    public void set_person(int _person) {
        this._person = _person;
    }

    public int get_facility() {
        return _facility;
    }

    public void set_facility(int _facility) {
        this._facility = _facility;
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

    public int get_question() {
        return _question;
    }

    public void set_question(int _question) {
        this._question = _question;
    }

    public String get_answer() {
        return _answer;
    }

    public void set_answer(String _answer) {
        this._answer = _answer;
    }

    public AssessmentsAnswers() {

    }

    public AssessmentsAnswers(int _person, int _facility, String _date_created, int _assessment_id, int _question, String _answer) {

        this._person = _person;
        this._facility = _facility;
        this._date_created = _date_created;
        this._assessment_id = _assessment_id;
        this._question = _question;
        this._answer = _answer;
    }

    public AssessmentsAnswers(int _assess_id, int _person, int _facility, String _date_created, int _assessment_id, int _question, String _answer) {

        this._assess_id = _assess_id;
        this._person = _person;
        this._facility = _facility;
        this._date_created = _date_created;
        this._assessment_id = _assessment_id;
        this._question = _question;
        this._answer = _answer;
    }
}