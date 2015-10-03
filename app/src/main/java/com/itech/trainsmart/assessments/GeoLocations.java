package com.itech.trainsmart.assessments;

/**
 * Created by rossumg on 9/28/2015.
 */
public class GeoLocations {

    //private variables
    int _rowid;
    float _longitude;
    float _latitude;
    String _device_id;
    String _created_at;
    String _username;
    String _password;

    // Empty constructor
    public GeoLocations() {

        this.set_longitude(MainActivity.lng);
        this.set_latitude(MainActivity.lat);
        this.set_device_id(MainActivity.deviceId);

        this.set_username(MainActivity._user);
        this.set_password(MainActivity._pass);
    }

    public GeoLocations(int _rowid, float _longitude, float _latitude, String _device_id, String _created_at, String _username, String _password) {
        this._rowid = _rowid;
        this._longitude = _longitude;
        this._latitude = _latitude;
        this._device_id = _device_id;
        this._created_at = _created_at;
        this._username = _username;
        this._password = _password;
    }

    public GeoLocations(float _longitude, float _latitude, String _device_id, String _created_at, String _username, String _password) {
        this._longitude = _longitude;
        this._latitude = _latitude;
        this._device_id = _device_id;
        this._created_at = _created_at;
        this._username = _username;
        this._password = _password;
    }

    public int get_rowid() {
        return _rowid;
    }

    public void set_rowid(int _rowid) {
        this._rowid = _rowid;
    }

    public float get_longitude() {
        return _longitude;
    }

    public void set_longitude(float _longitude) {
        this._longitude = _longitude;
    }

    public float get_latitude() {
        return _latitude;
    }

    public void set_latitude(float _latitude) {
        this._latitude = _latitude;
    }

    public String get_device_id() {
        return _device_id;
    }

    public void set_device_id(String _device_id) {
        this._device_id = _device_id;
    }

    public String get_created_at() {
        return _created_at;
    }

    public void set_created_at(String _created_at) {
        this._created_at = _created_at;
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
