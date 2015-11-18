package com.itech.trainsmart.assessments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static String ALL = "request!";
    public static String TAG = "editTag";
    DBHelper dbHelp;
    private static PersonToAssessments _pToA;
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param pToA Parameter 1.
     //* @param param2 Parameter 2.
     * @return A new instance of fragment EditFragment.
     */


    public static EditFragment newInstance(PersonToAssessments pToA) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();

         _pToA = pToA;


        fragment.setArguments(args);
        return fragment;
    }

    public EditFragment() {
//         Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        //Log.d("request!", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        //Log.d("request!", "onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        //Log.d("request!", "onStop");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Remember the current text, to restore if we later restart.
        MainActivity.configChange = true;
    }

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        Person person = dbHelp.getPerson(_pToA.get_person_id());
        Assessments assessment = dbHelp.getAssessments(_pToA.get_assessment_id());

        Log.d("request!", "editFrag: " +
                person.get_last_name() + ", " +
                person.get_first_name() + " " +
                person.get_national_id() + " " +
                person.get_facility_name() + " " +
                assessment.get_assessment_type());

        getActivity().setTitle(person.get_first_name() + ", " +
                person.get_last_name() + " " +
                _pToA.get_date_created() + " " +
                person.get_facility_name());

        ListView listView = (ListView) view.findViewById(R.id.editListView);

        MultiTypeListAdapter adapter = new MultiTypeListAdapter(this.getActivity(), dbHelp.getEditPageData(_pToA), _pToA);
        listView.setItemsCanFocus(true);
        listView.setAdapter(adapter);

        // Inflate the layout for this fragment
        return view;
    }

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

    public static class ViewHolder implements TextWatcher, com.itech.trainsmart.assessments.DiscreteSeekBar.OnProgressChangeListener, Switch.OnCheckedChangeListener {

        public HashMap _saveData = new HashMap();
        public int position;
        public TextView textView;
        public Switch switchWidget;
        public EditText editText;
        public EditText editText2;
        public com.itech.trainsmart.assessments.DiscreteSeekBar discreteSeekBar;
        public DBHelper dbHelp;
        public PersonToAssessments pToA;
        public List<EditPageObject> pageData;

        public ViewHolder(HashMap<String, Integer> saveData, List<EditPageObject> pageData,  DBHelper dbHelp, PersonToAssessments pToA) {
            this._saveData = saveData;
            this.dbHelp = dbHelp;
            this.pToA = pToA;
            this.pageData = pageData;
        }

        public void afterTextChanged(Editable editable) {

            Integer relativePos = position+1;
            int question_id =  dbHelp.getAssessmentsQuestionsQuestion(pToA.get_assessment_id(), relativePos);
            AssessmentsAnswers assessmentsAnswers = dbHelp.getAssessmentsAnswers(pToA.get_person_id(), pToA.get_facility_id(), pToA.get_date_created(), pToA.get_assessment_id(), question_id );
            // gnr: This logic tests if the event is a "real user" edit or a timing bug when the user fast scrolls
            //      by determining if the new answer value is a possible result of a single character edit of the db value
            if (assessmentsAnswers != null) {
                boolean writeFlag = false;
                if( (editable.length() -  assessmentsAnswers.get_answer().length()) == 1 ||
                        (assessmentsAnswers.get_answer().length() - editable.length()) == 1) {
                    if( (editable.length() -  assessmentsAnswers.get_answer().length()) == 1 && editable.toString().contains(assessmentsAnswers.get_answer()) ) {
                        writeFlag = true;
                    }
                    if( ( assessmentsAnswers.get_answer().length() - editable.length())  == 1 && assessmentsAnswers.get_answer().contains(editable.toString()) ) {
                        writeFlag = true;
                    }
                    if( ( editable.toString().length() != 0 &&
                            assessmentsAnswers.get_answer().length() != 0 &&
                            assessmentsAnswers.get_answer().charAt(0) == editable.toString().charAt(0) &&
                            assessmentsAnswers.get_answer().charAt(assessmentsAnswers.get_answer().length()-1) == editable.toString().charAt(editable.toString().length()-1)) ) {
                        writeFlag = true;
                    }
                    if( editable.toString().equals("") )  {
                        writeFlag = true;
                    }
                    if(writeFlag) {
                        pageData.get(position).set_answer(editable.toString());
                        dbHelp.setEditPageRow(pToA, question_id, editable.toString());
                    }
                }
            } else {
                if( editable.length() == 1 ) {
                    pageData.get(position).set_answer(editable.toString());
                    dbHelp.setEditPageRow(pToA, question_id, editable.toString());
                }
            }
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //Log.d("request!", "beforeTextChanged:editable: " + s +  ":" + start + ":" + count + ":" + after);
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //Log.d("request!", "onTextChanged: " );
        }

        @Override
        public void onProgressChanged(com.itech.trainsmart.assessments.DiscreteSeekBar discreteSeekBar, int progress, boolean fromUser) {
            Log.d("request!", "onProgressChanged: " + progress);

            pageData.get(position).set_answer(convertProgressToStr(progress));
            dbHelp.setEditPageRow(pToA, pageData.get(position).get_assessments_questions_id(), convertProgressToStr(progress));
        }

        @Override
        public void onStartTrackingTouch(com.itech.trainsmart.assessments.DiscreteSeekBar discreteSeekBar) {
        }

        @Override
        public void onStopTrackingTouch(com.itech.trainsmart.assessments.DiscreteSeekBar discreteSeekBar) {
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            pageData.get(position).set_answer(convertCheckedToStr(isChecked));
            dbHelp.setEditPageRow(pToA, pageData.get(position).get_assessments_questions_id(), convertCheckedToStr(isChecked));
        }

        public String convertProgressToStr (int progressInt) {
            String value = "";
            switch (progressInt) {
                case 0 :
                    value = "F";
                    break;
                case 1 :
                    value = "A";
                    break;
                case 2 :
                    value = "B";
                    break;
                case 3 :
                    value = "C";
                    break;
                case 4 :
                    value = "D";
                    break;
                case 5 :
                    value = "E";
                    break;
            }
            return value;
        }

        public String convertCheckedToStr (boolean checked) {
            if (checked) {
                return "A";
            } else {
                return "B";
            }
        }

    }
}
