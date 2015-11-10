package com.itech.trainsmart.assessments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Spinner;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SearchFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static String TAG = "searchTag";

    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

    private OnFragmentInteractionListener mListener;
    private AbsListView mListView;

    private ListAdapter mAdapter;
    private DBHelper dbHelp;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     //* @param param1 Parameter 1.
     //* @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    //  public static SearchFragment newInstance(String param1, String param2) {
    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    // public SearchFragment() {
    // Required empty public constructor
    //}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }

        dbHelp = new DBHelper(getActivity());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        getActivity().setTitle(getResources().getString(R.string.searchTitle));

        loadPersonNameDropdown(view);
        loadAssessmentTypeDropdown(view);
        loadNationalIDDropdown(view);
        loadFacilityDropdown(view);

        final ClearableAutoCompleteTextView facilityDropdown = (ClearableAutoCompleteTextView) view.findViewById(R.id.facility);
        final ClearableAutoCompleteTextView nameDropdown = (ClearableAutoCompleteTextView) view.findViewById(R.id.name);
        final ClearableAutoCompleteTextView nationalIdDropdown = (ClearableAutoCompleteTextView) view.findViewById(R.id.nationalid);

        Button btnSearch = (Button) view.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                java.util.Calendar cal = java.util.Calendar.getInstance();
                java.util.Date utilDate = cal.getTime();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                Log.d("request!", "Search button: ");

                String paramPersonId = "";
                String paramAssessmentType = "";
                String paramFacilityName = "";
                String paramNationalID = "";
                Log.d("request!", "Search button name: " + nameDropdown.getText().toString() + "<");

                if (person == null || nameDropdown.getText().toString().equals("")) {
                    Log.d("request!", "Search button name is null: ");
                } else {
                    Log.d("request!", "Search button name: " + nameDropdown.getText().toString());
                    int personId = new Integer(person.get_person_id());
                    paramPersonId = Integer.toString(personId);
                }
                if (assessment == null) {
                    Log.d("request!", "Search button assessment is null: ");
                } else {
                    Log.d("request!", "Search button assessment: " + assessment.get_assessment_type());
                    paramAssessmentType = assessment.get_assessment_type();
                }
                if (facilityDropdown.getText().equals("")) {
                    Log.d("request!", "Search button facility is null: ");
                } else {
                    Log.d("request!", "Search button facilityName: " + facilityDropdown.getText().toString());
                    paramFacilityName = facilityDropdown.getText().toString();
                }
                if (nationalIdDropdown.equals("")) {
                    Log.d("request!", "Search button nationalID is null: ");
                } else {
                    Log.d("request!", "Search button nationalID: " + nationalIdDropdown.getText());
                    paramNationalID = nationalIdDropdown.getText().toString();
                }

                String from_date = "";
                String to_date = "";

                // get ptoa's where params and goto list
                //List<String> searchAssessments = dbHelp.getReadablAssessments(paramPersonId, nationalID, facilityName, paramAssessmentType, from_date, to_date);

                Fragment fragment;
                //fragment = getFragmentManager().findFragmentByTag(RecentFragment.TAG);
                //if (fragment == null) {
                    fragment = RecentFragment.newInstance("search", paramPersonId + ":" + paramNationalID + ":" + paramFacilityName + ":" + paramAssessmentType + ":" + from_date + ":" + to_date + ":");
                //}
                getFragmentManager().beginTransaction().replace(R.id.container, fragment, RecentFragment.TAG).addToBackStack("").commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {}

    public void onNothingSelected(AdapterView<?> arg0) {}

    // TODO: Rename method, update argument and hook method into UI event
    public void onListItemPressed(int position) {
        if (mListener != null) {
            mListener.onFragmentInteraction(position);
        }
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

    private Person person;
    public void loadPersonNameDropdown(View view) {

        List<String> personIDs = dbHelp.getAllPersonIDs();
        // convert to array
        String[] stringArrayPersonID = new String[ personIDs.size() ];
        personIDs.toArray(stringArrayPersonID);

        final ClearableAutoCompleteTextView dropdown = (ClearableAutoCompleteTextView) view.findViewById(R.id.name);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, stringArrayPersonID);
        dropdown.setThreshold(1);
        dropdown.setAdapter(dataAdapter);

        dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int index, long position) {
                String nameText = dropdown.getText().toString();
                String parts[] = {};
                parts = nameText.split(", ");

                String first_name = parts[0].trim();
                String last_name = parts[1].trim();
                String national_id =  parts[2].trim();
                String facility_name = parts[3].trim();
                Log.d("request!", "person selected: " + first_name + "." + last_name + "." + national_id + "." + facility_name + ".");
                person = dbHelp.getPerson(first_name, last_name, national_id, facility_name);
                Log.d("request!", "person_id selected: " + person.get_person_id());
//                Log.d("request!", "first_name selected: " + first_name);
//                Log.d("request!", "last_name selected: " + last_name);
//                Log.d("request!", "national_id selected: " + national_id);
//                Log.d("request!", "facility_name selected: " + facility_name);
            }
        });
    }

    public void loadNationalIDDropdown(View view) {
        String[] nationalIDs = dbHelp.getAllNationalIDs();

        final ClearableAutoCompleteTextView dropdown = (ClearableAutoCompleteTextView) view.findViewById(R.id.nationalid);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, nationalIDs);
        dropdown.setThreshold(1);
        dropdown.setAdapter(dataAdapter);

        dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int index, long position) {
                Log.d("request!", "nationalID selected: " + dropdown.getText());
            }
        });
    }

    public void loadFacilityDropdown(View view) {
        String[] facilityNames = dbHelp.getAllFacilityNames();

        final ClearableAutoCompleteTextView dropdown = (ClearableAutoCompleteTextView) view.findViewById(R.id.facility);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, facilityNames);
        dropdown.setThreshold(1);
        dropdown.setAdapter(dataAdapter);

        dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int index, long position) {
                Log.d("request!", "facility selected: " + dropdown.getText());
            }
        });

    }

    private Assessments assessment = null;
    public void loadAssessmentTypeDropdown(View view) {
        final Spinner dropdown = (Spinner) view.findViewById(R.id.assessment_type);
        dropdown.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String assessmentTypeText  = dropdown.getSelectedItem().toString();
                Log.d("request!", "assessmentTypeText: " + assessmentTypeText + "<");
                // because of the all option, not available in create
                if(!assessmentTypeText.equals("")) {
                    assessment = dbHelp.getAssessments(assessmentTypeText);
                } else assessment = null;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("request!", "spinner nothing selected");
            }
        });

        List<String> assessmentTypes = dbHelp.getAllAssessmentTypes();
        String all = "";
        assessmentTypes.add(0,all);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, assessmentTypes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(dataAdapter);

    }

}
