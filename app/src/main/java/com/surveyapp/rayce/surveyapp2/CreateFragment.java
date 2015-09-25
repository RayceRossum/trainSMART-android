package com.surveyapp.rayce.surveyapp2;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Date;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CreateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static String TAG = "createTag";

    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

    private OnFragmentInteractionListener mListener;
    private DBHelper dbHelp;
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     //* @param param1 Parameter 1.
     //* @param param2 Parameter 2.
     * @return A new instance of fragment CreateFragment.
     */
    // TODO: Rename and change types and number of parameters
    //  public static CreateFragment newInstance(String param1, String param2) {
    public static CreateFragment newInstance() {
        CreateFragment fragment = new CreateFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    // public CreateFragment() {
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
        View view = inflater.inflate(R.layout.fragment_create, container, false);

        loadPersonIDDropdown(view);
        loadAssessmentTypeDropdown(view);

        Button btnCreate = (Button) view.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Calendar cal = java.util.Calendar.getInstance();
                java.util.Date utilDate = cal.getTime();
                java.sql.Date sqlDate = new Date(utilDate.getTime());
                Log.d("request!", "create button: " + person.get_person_id() + " " + person.get_facility_id() + " " + sqlDate + " " + assessment.get_assessment_id());
                PersonToAssessments pToANew = new PersonToAssessments(person.get_person_id(), person.get_facility_id(), sqlDate.toString(), assessment.get_assessment_id(), 1);
                pToANew.dump();
                // check for exists
                PersonToAssessments pToADB  = dbHelp.getPersonToAssessments(pToANew.get_person_id(), pToANew.get_facility_id(), pToANew.get_date_created(), pToANew.get_assessment_id());
                Log.d("request!", "create button: check db");
                if(pToADB == null) {
                    Log.d("request!", "create button: not in db");
                    if (dbHelp.addPersonToAssessments(pToANew)) {
                        Log.d("request!", "create button pToA added");
                        Fragment fragment;
                        fragment = getFragmentManager().findFragmentByTag(EditFragment.TAG);
                        if (fragment == null) {
                            PersonToAssessments pToA = dbHelp.getPersonToAssessments(pToANew.get_person_id(), pToANew.get_facility_id(), pToANew.get_date_created(), pToANew.get_assessment_id());
                            pToA.dump();
                            fragment = EditFragment.newInstance(pToA);
                        }
                        getFragmentManager().beginTransaction().replace(R.id.container, fragment, EditFragment.TAG).commit();
                        Log.d("request!", "Assessment Created");
                        Toast.makeText(v.getContext(), "Assessment Created", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("request!", "create button: existing");
                    Fragment fragment;
                    fragment = getFragmentManager().findFragmentByTag(EditFragment.TAG);
                    if (fragment == null) {
                        PersonToAssessments pToA = dbHelp.getPersonToAssessments(pToADB.get_person_to_assessments_id());
                        fragment = EditFragment.newInstance(pToA);
                    }
                    getFragmentManager().beginTransaction().replace(R.id.container, fragment, EditFragment.TAG).commit();
                    Log.d("request!", "Existing Assessment");
                    Toast.makeText(v.getContext(), "Existing Assessment", Toast.LENGTH_LONG).show();
                }
            }
        });

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
    public void loadPersonIDDropdown(View view) {

        List<String> personIDs = dbHelp.getAllPersonIDs();
        // convert to array
        String[] stringArrayPersonID = new String[ personIDs.size() ];
        personIDs.toArray(stringArrayPersonID);

        final AutoCompleteTextView dropdown = (AutoCompleteTextView) view.findViewById(R.id.name);
        //dropdown.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, stringArrayPersonID);
        dropdown.setThreshold(1);
        dropdown.setAdapter(dataAdapter);

        dropdown.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int index, long position) {
                String nameText = dropdown.getText().toString();
                String parts[] = {};
                parts = nameText.split(", ");

                String first_name = parts[0];
                String last_name = parts[1];
                String national_id =  parts[2];
                String facility_name = parts[3];
                Log.d("request!", "person selected: " + first_name + " " + last_name + " " + national_id + " " + facility_name);
                person = dbHelp.getPerson(first_name, last_name, national_id, facility_name);
                Log.d("request!", "person_id selected: " + person.get_person_id());
//                Log.d("request!", "first_name selected: " + first_name);
//                Log.d("request!", "last_name selected: " + last_name);
//                Log.d("request!", "national_id selected: " + national_id);
//                Log.d("request!", "facility_name selected: " + facility_name);
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
                Log.d("request!", "assessmentTypeText: " + assessmentTypeText);
                assessment = dbHelp.getAssessments(assessmentTypeText);


                //Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                Log.d("request!", "assessmentId/Type selected: " + assessment.get_assessment_id() + " " + assessment.get_assessment_type());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.d("request!", "spinner nothing selected");
            }
        });

        List<String> assessmentTypes = dbHelp.getAllAssessmentTypes();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, assessmentTypes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(dataAdapter);

    }

}
