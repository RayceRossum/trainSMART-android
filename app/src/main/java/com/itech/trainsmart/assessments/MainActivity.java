package com.itech.trainsmart.assessments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.UUID;


public class MainActivity extends ActionBarActivity implements NavigationDrawerCallbacks,
        EditFragment.OnFragmentInteractionListener,
        CreateFragment.OnFragmentInteractionListener,
        DebugFragment.OnFragmentInteractionListener,
        CreatePersonFragment.OnFragmentInteractionListener,
        SearchFragment.OnFragmentInteractionListener,
        RecentFragment.OnFragmentInteractionListener,
        ActionFragment.OnFragmentInteractionListener
{

    public static Boolean LOGGED_IN = false;

    public static String currentFragmentId = "";

    public static String COUNTRY = "mobile_demo";
//    public static String COUNTRY = "zimbabwe";

    public static final String BASE_URL = "http://android.trainingdata.org/";

    public static final String GET_TABLE_URL = BASE_URL + COUNTRY + "/" + "getTable.php";
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_MESSAGE = "message";
    public static SQLiteDatabase db;
    public static String _user = "rossumg";
    public static String _pass = "";
    public static boolean configChange = false;
    public static String TAG = "request!";

    private static LocationManager locationManager;
    private static String provider;
    public static float lat = 0;
    public static float lng = 0;

    public static String deviceId = "";

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        setSupportActionBar(mToolbar);
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.fragment_drawer);
        // Set up the drawer.
        mNavigationDrawerFragment.setup(R.id.fragment_drawer, (DrawerLayout) findViewById(R.id.drawer), mToolbar);
        // populate the navigation drawer
        //mNavigationDrawerFragment.setUserData("Rayce Rossum", "Rayce.Rossum@gmail.com", BitmapFactory.decodeResource(getResources(), R.drawable.avatar));
        //mNavigationDrawerFragment.setUserData("Zimbabwe", "Rayce.Rossum");

        final TelephonyManager tm = (TelephonyManager) this.getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(this.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        deviceId = deviceUuid.toString();
        Log.d("request!", "mainActivity:onCreate:deviceId: " + deviceId);


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } else {
            Log.d(TAG, "location is null");
            //latituteField.setText("Location not available");
            //longitudeField.setText("Location not available");
        }
    }

    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();
        //locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        //locationManager.removeUpdates(this);
    }

    //@Override
    public void onLocationChanged(Location location) {
        lat = (float) (location.getLatitude());
        lng = (float) (location.getLongitude());
        Log.d(TAG, "mainActivity:lat: " + String.valueOf((lat)));
        Log.d(TAG, "mainActivity:lng: " + String.valueOf((lng)));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

        if(MainActivity.configChange == true) { // config change from edit
            MainActivity.configChange = false;
            return;
        }

        Fragment fragment;
        switch(position) {

            case 0:
                fragment = getFragmentManager().findFragmentByTag(CreateFragment.TAG);
                if (fragment == null) {
                    Log.d("request!", "before add create to back stack: " + getFragmentManager().getBackStackEntryCount());
                    fragment = CreateFragment.newInstance();
                    getFragmentManager().beginTransaction().replace(R.id.container, fragment, CreateFragment.TAG).addToBackStack(currentFragmentId).commit();
                    Log.d("request!", "after add create to back stack: " + getFragmentManager().getBackStackEntryCount());
                } else {
                    getFragmentManager().beginTransaction().replace(R.id.container, fragment, CreateFragment.TAG).commit();
                }
                currentFragmentId = "Create";

                break;

            case 1:
                fragment = getFragmentManager().findFragmentByTag(RecentFragment.TAG);
                if (fragment == null) {
                    Log.d("request!", "before add recent to back stack: " + getFragmentManager().getBackStackEntryCount());
                    fragment = RecentFragment.newInstance("main", "");
                    getFragmentManager().beginTransaction().replace(R.id.container, fragment, RecentFragment.TAG).addToBackStack(currentFragmentId).commit();
                    Log.d("request!", "after add recent to back stack: " + getFragmentManager().getBackStackEntryCount());
                } else {
                    getFragmentManager().beginTransaction().replace(R.id.container, fragment, RecentFragment.TAG).commit();
                }
                currentFragmentId = "Recent";

                break;

            case 2:
                fragment = getFragmentManager().findFragmentByTag(SearchFragment.TAG);
                if (fragment == null) {
                    fragment = SearchFragment.newInstance();
                    getFragmentManager().beginTransaction().replace(R.id.container, fragment, SearchFragment.TAG).addToBackStack(currentFragmentId).commit();
                } else {
                    getFragmentManager().beginTransaction().replace(R.id.container, fragment, SearchFragment.TAG).commit();
                }
                currentFragmentId = "Search";

                break;

            case 3:
                fragment = getFragmentManager().findFragmentByTag(ActionFragment.TAG);
                if (fragment == null) {
                    fragment = ActionFragment.newInstance("main", "");
                    getFragmentManager().beginTransaction().replace(R.id.container, fragment, ActionFragment.TAG).addToBackStack(currentFragmentId).commit();
                } else {
                    getFragmentManager().beginTransaction().replace(R.id.container, fragment, ActionFragment.TAG).commit();
                }
                currentFragmentId = "Action";

                break;

            case 4:
                fragment = getFragmentManager().findFragmentByTag(DebugFragment.TAG);
                if (fragment == null) {
                    fragment = DebugFragment.newInstance();
                }
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, DebugFragment.TAG).addToBackStack("").commit();

                break;

            case 34:
//                fragment = getFragmentManager().findFragmentByTag(CreatePersonFragment.TAG);
//                if (fragment == null) {
//                    fragment = CreatePersonFragment.newInstance();
//                }
//                getFragmentManager().beginTransaction().replace(R.id.container, fragment, CreatePersonFragment.TAG).commit();

                break;

            case 5:
                break;


        }
    }

    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen()) {
            mNavigationDrawerFragment.closeDrawer();
            return;
        }

        int count = getFragmentManager().getBackStackEntryCount();
        Log.d("Back!", "onBackPressed: " + count + ", " + currentFragmentId);
//        FragmentManager.BackStackEntry entry = getFragmentManager().getBackStackEntryAt(count-1);
        for (int i = count-1; i > -1; i--){
            Log.d("Back!", "onBackPressed:stack: " + i + ", " + getFragmentManager().getBackStackEntryAt(i).getName());
        }

        Log.d("Back!", "onBackPressed:test: " + currentFragmentId + ", " + getFragmentManager().getBackStackEntryAt(count-1).getName());

        if(currentFragmentId.equals("Recent")) {
            return;
        }

        if (count > 1) {
            getFragmentManager().popBackStack();
            currentFragmentId = getFragmentManager().getBackStackEntryAt(count-1).getName();
        } else {
            Log.d("Back!", "onBackPressed:exit: " + count + ", " + currentFragmentId);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirm exit");
            // Set up the buttons
            builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.super.onBackPressed();
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();
        }

    }

//    @Override
//    public void onBackPressed() {
//        if (mNavigationDrawerFragment.isDrawerOpen())
//            mNavigationDrawerFragment.closeDrawer();
//        else {
//            Log.d("request!", "main:onBackPressed: ");
//            super.onBackPressed();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            //getMenuInflater().inflate(R.menu.main, menu);
           // return true;
        //}
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(int position) {
    }




}
