package com.surveyapp.rayce.surveyapp2;

/**
 * Created by rossumg on 9/28/2015.
 */
public class GeoLocations {

    //private variables
    int _rowid;
    int _longitude;
    int _latitude;
    String _device_id;
    String _timestamp;
    String _username;
    String _password;

    // Empty constructor
    public GeoLocations() {
    }

    public GeoLocations(int _rowid, int _longitude, int _latitude, String _device_id, String _timestamp, String _username, String _password) {
        this._rowid = _rowid;
        this._longitude = _longitude;
        this._latitude = _latitude;
        this._device_id = _device_id;
        this._timestamp = _timestamp;
        this._username = _username;
        this._password = _password;
    }

    public GeoLocations(int _longitude, int _latitude, String _device_id, String _timestamp, String _username, String _password) {
        this._longitude = _longitude;
        this._latitude = _latitude;
        this._device_id = _device_id;
        this._timestamp = _timestamp;
        this._username = _username;
        this._password = _password;
    }

    public int get_rowid() {
        return _rowid;
    }

    public void set_rowid(int _rowid) {
        this._rowid = _rowid;
    }

    public int get_longitude() {
        return _longitude;
    }

    public void set_longitude(int _longitude) {
        this._longitude = _longitude;
    }

    public int get_latitude() {
        return _latitude;
    }

    public void set_latitude(int _latitude) {
        this._latitude = _latitude;
    }

    public String get_device_id() {
        return _device_id;
    }

    public void set_device_id(String _device_id) {
        this._device_id = _device_id;
    }

    public String get_timestamp() {
        return _timestamp;
    }

    public void set_timestamp(String _timestamp) {
        this._timestamp = _timestamp;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }
}
