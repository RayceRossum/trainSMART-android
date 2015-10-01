package com.itech.trainsmart.assessments;

/**
 * Created by rossumg on 9/28/2015.
 */
public class GeoLocations {

    //private variables
    int _rowid;
    int _longitude;
    int _latitude;
    String _device_id;
    String _created_at;
    String _username;
    String _password;

    // Empty constructor
    public GeoLocations() {

        // get location
        this.set_longitude(123);
        this.set_latitude(456);

        // _system table ?
        this.set_device_id("789");
        this.set_username("user");
        this.set_password("pass");
    }

    public GeoLocations(int _rowid, int _longitude, int _latitude, String _device_id, String _created_at, String _username, String _password) {
        this._rowid = _rowid;
        this._longitude = _longitude;
        this._latitude = _latitude;
        this._device_id = _device_id;
        this._created_at = _created_at;
        this._username = _username;
        this._password = _password;
    }

    public GeoLocations(int _longitude, int _latitude, String _device_id, String _created_at, String _username, String _password) {
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
