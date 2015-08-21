package com.surveyapp.rayce.surveyapp2;

import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerCallbacks, EditFragment.OnFragmentInteractionListener, CreateFragment.OnFragmentInteractionListener, DebugFragment.OnFragmentInteractionListener {

    public static Boolean LOGGED_IN = false;
    public static final String BASE_URL = "http://android.trainingdata.org/";
    public static final String GET_TABLE_URL = BASE_URL + "getTable.php";
    public static final String TAG_SUCCESS = "success";
    public static final String TAG_MESSAGE = "message";
    public static SQLiteDatabase db;
    static String _user = "rossumg";
    static String _pass = "rossumg";


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
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        Toast.makeText(this, "Menu item selected -> " + position, Toast.LENGTH_SHORT).show();

        //android.support.v4.app.Fragment fragment;
        Fragment fragment;
        switch(position) {
            case 0:
                fragment = getFragmentManager().findFragmentByTag(EditFragment.TAG);
                if (fragment == null) {
                    fragment = EditFragment.newInstance();
                }

                getFragmentManager().beginTransaction().replace(R.id.container, fragment, EditFragment.TAG).commit();

                break;

            case 1:
                fragment = getFragmentManager().findFragmentByTag(CreateFragment.TAG);
                if (fragment == null) {
                    fragment = CreateFragment.newInstance();
                }
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, CreateFragment.TAG).commit();

                break;

            case 4:
                fragment = getFragmentManager().findFragmentByTag(DebugFragment.TAG);
                if (fragment == null) {
                    fragment = DebugFragment.newInstance();
                }
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, CreateFragment.TAG).commit();

                break;

        }
    }


    @Override
    public void onBackPressed() {
        if (mNavigationDrawerFragment.isDrawerOpen())
            mNavigationDrawerFragment.closeDrawer();
        else
            super.onBackPressed();
    }


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
