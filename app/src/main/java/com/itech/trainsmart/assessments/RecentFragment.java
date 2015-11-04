package com.itech.trainsmart.assessments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecentFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecentFragment extends Fragment implements AbsListView.OnItemClickListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (mListener != null) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.

            int pToA_id = Integer.parseInt(mAdapter.getItem(position).toString().substring(0, mAdapter.getItem(position).toString().indexOf(")")));
            Log.d("request!", "recentFragment onItemClick: " + pToA_id);
            PersonToAssessments pToADB = dbHelp.getPersonToAssessments( pToA_id);

            Fragment fragment;
            fragment = getFragmentManager().findFragmentByTag(EditFragment.TAG);
            if (fragment == null) {
                PersonToAssessments pToA = dbHelp.getPersonToAssessments(pToADB.get_person_to_assessments_id());
                fragment = EditFragment.newInstance(pToA);
            }
            getFragmentManager().beginTransaction().replace(R.id.container, fragment, EditFragment.TAG).addToBackStack("").commit();
            Log.d("request!", "Existing Assessment");
            Toast.makeText(view.getContext(), "Existing Assessment", Toast.LENGTH_LONG).show();
        }
    }

    public void setEmptyText(CharSequence emptyText) {
        View emptyView = mListView.getEmptyView();

        if (emptyView instanceof TextView) {
            ((TextView) emptyView).setText(emptyText);
        }
    }

    public static String TAG = "recentTag";
    public DBHelper dbHelp;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private AbsListView mListView;
    private ListAdapter mAdapter;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment RecentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecentFragment newInstance(String searchAssessments, String searchParams) {
        RecentFragment fragment = new RecentFragment();
        Bundle args = new Bundle();
        args.putString("searchAssessments", searchAssessments);
        args.putString("searchParams", searchParams);
        fragment.setArguments(args);
        return fragment;
    }

    public RecentFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString("searchAssessments");
            mParam2 = getArguments().getString("searchParams");
            Log.d("request!", "recentFragment onCreate param1: " + mParam1.toString());
            Log.d("request!", "recentFragment onCreate param2:>" + mParam2.toString() + "<");
        }

        DBHelper dbHelp = new DBHelper(getActivity());
        this.dbHelp = dbHelp;

        List<String> assessments = new ArrayList<String>();
        if(mParam1.toString().equals("search")){

            // params from searchFragment
            String params = mParam2.toString();
            String parts[] = {};
            parts = params.split(":",6);
    //        Log.d("request!", "param2:length>" + parts.length);
    //        Log.d("request!", "param2:0>" + parts[0]);
    //        Log.d("request!", "param2:1>" + parts[1]);
    //        Log.d("request!", "param2:2>" + parts[2]);
    //        Log.d("request!", "param2:3>" + parts[3]);
    //        Log.d("request!", "param2:4>" + parts[4]);
    //        Log.d("request!", "param2:5>" + parts[5]);

            String person_id = parts[0];
            String nationalID = parts[1];
            String facilityName = parts[2];
            String assessment = parts[3];
            String from_date = parts[4];
            String to_date = parts[5];

//            Log.d("request!", "recentFrag onCreate: " +
//                    person_id + "," +
//                    nationalID + "," +
//                    facilityName + "," +
//                    assessment + "," +
//                    from_date + "," +
//                    to_date + "," );

            assessments = dbHelp.getReadableAssessments(person_id, nationalID, facilityName, assessment, from_date, to_date);
        }else {
            assessments = dbHelp.getReadableRecentAssessments();
        }

        String[] _stringArray = new String[ assessments.size() ];
        assessments.toArray(_stringArray);
        mAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, android.R.id.text1, assessments);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recent, container, false);

        getActivity().setTitle(getResources().getString(R.string.recentTitle));

        // Set the adapter
        mListView = (AbsListView) view.findViewById(android.R.id.list);
        ((AdapterView<ListAdapter>) mListView).setAdapter(mAdapter);

        // Set OnItemClickListener so we can be notified on item clicks
        mListView.setOnItemClickListener(this);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(int position);
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
