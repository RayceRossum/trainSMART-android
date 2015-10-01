package com.itech.trainsmart.assessments;

/**
 * Created by rossumg on 9/3/2015.
 */
public class EditPageObject {

    //private variables
    int _rowid;
    int _assessments_questions_id;
    String _question;
    String _itemtype;
    int _itemorder;
    String _answer;



    // Empty constructor
    public EditPageObject() {
    }

    public EditPageObject(int _rowid, String _question, String _itemtype, int _itemorder, String _answer) {
        this._rowid = _rowid;
        this._question = _question;
        this._itemtype = _itemtype;
        this._itemorder = _itemorder;
        this._answer = _answer;
    }

    public EditPageObject(String _question, int _question_id, String _itemtype, int _itemorder, String _answer) {
        this._question = _question;
        this._itemtype = _itemtype;
        this._itemorder = _itemorder;
        this._answer = _answer;
    }

    public int get_assessments_questions_id() {
        return _assessments_questions_id;
    }

    public void set_assessments_questions_id(int _assessments_questions_id) {
        this._assessments_questions_id = _assessments_questions_id;
    }

    public String get_question() {
        return _question;
    }

    public void set_question(String _question) {
        this._question = _question;
    }

    public String get_itemtype() {
        return _itemtype;
    }

    public void set_itemtype(String _itemtype) {
        this._itemtype = _itemtype;
    }

    public int get_itemorder() {
        return _itemorder;
    }

    public void set_itemorder(int _itemorder) {
        this._itemorder = _itemorder;
    }

    public String get_answer() {

        return _answer;
    }

    public void set_answer(String _answer) {
        this._answer = _answer;
    }


}