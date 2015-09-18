package com.surveyapp.rayce.surveyapp2;

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
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class

        EditFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static String TAG = "editTag";
    static PersonToAssessments pToA;
    DBHelper dbHelp;
    public static Map<String, Object> _buffer = new HashMap<String, Object>();
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

  //  public static EditFragment newInstance(String param1, String param2) {
    public static EditFragment newInstance(PersonToAssessments pToA) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        EditFragment.pToA = pToA;

        fragment.setArguments(args);
        return fragment;
    }

    public EditFragment() {
//         Required empty public constructor
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("request!", "onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("request!", "onPause");

        for (Map.Entry<String, Object> entry : _buffer.entrySet()) {

            if (entry.getValue() instanceof String) {
                Log.d("request!", "String: " + entry.getValue());
            } else if (entry.getValue() instanceof PersonToAssessments) {
                PersonToAssessments pToA = (PersonToAssessments)entry.getValue();
                Log.d("request!", "pToA: " +
                        pToA.get_person_id() + ":" +
                        pToA.get_facility_id() + ":" +
                        pToA.get_date_created() + ":" +
                        pToA.get_assessment_id());
            } else {
                throw new IllegalStateException("Expecting either String or Class as entry value");
            }
        }

        PersonToAssessments pToA = null;
        int itemOrder = 0;
        String newAnswer = "";
        Set set = _buffer.entrySet();
        Iterator i = set.iterator();
        while(i.hasNext()) {

            Map.Entry entry = (Map.Entry)i.next();
            itemOrder = Integer.parseInt(entry.getValue().toString());
            Map.Entry obj = (Map.Entry)i.next();
            pToA = (PersonToAssessments)obj.getValue();
            Map.Entry answer = (Map.Entry)i.next();
            newAnswer = answer.getValue().toString();

            int question_id =  dbHelp.getAssessmentsQuestionsQuestion(pToA.get_assessment_id(), itemOrder);
            dbHelp.setEditPageRow(pToA, question_id, newAnswer);

        }
        _buffer.clear();

//        Log.d("request!", "itemOrder: " + itemOrder);
//        Log.d("request!", "pToA: " +
//                pToA.get_person_id() + ":" +
//                pToA.get_facility_id() + ":" +
//                pToA.get_date_created() + ":" +
//                pToA.get_assessment_id());
//        Log.d("request!", "newAnswer: " + newAnswer);


    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("request!", "onStop");
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

        ListView listView = (ListView)view.findViewById(R.id.editListView);
//        PersonToAssessments pToA = dbHelp.getPersonToAssessments(19);
//        PersonToAssessments pToA = dbHelp.getPersonToAssessments(person_id, facility_id, date_created, assessment_id);
//        dbHelp.putPersonToAssessments(person_id, facility_id, date_created, assessment_id);
        MultiTypeListAdapter adapter = new MultiTypeListAdapter(this.getActivity(), dbHelp.getEditPageData(pToA), pToA);
//        MultiTypeListAdapter adapter = new MultiTypeListAdapter(this.getActivity(), dbHelp.getQuestionData(1, 1, 1, 2));
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

    public static class ViewHolder implements TextWatcher, SeekBar.OnSeekBarChangeListener, Switch.OnCheckedChangeListener {

        public HashMap _saveData = new HashMap();
        public int position;
        public TextView textView;
        public Switch switchWidget;
        public EditText editText;
        public EditText editText2;
        public SeekBar seekBar;
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

            _saveData.remove(position);
            _saveData.put(position, editable.toString());


            Integer relativePos = position+1;
            //Log.d("request!", "load: " + relativePos + " " + editable.toString());
            _buffer.put("0",editable.toString());
            _buffer.put("1",pToA);
            _buffer.put("2", relativePos.toString());

        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            //Log.d("request!", "beforeTextChanged:editable: " + s +  ":" + start + ":" + count + ":" + after);
            // TODO Auto-generated method stub

        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {

            if(s.toString().equals("")){

            } else {
//                int real_pos = position + 1;
//                Log.d("request!", "onTextChanged:s:" + s + ":" +
//                                //start + ":" + before + ":" + count +
//                                " > " +
//                                //pToA.get_person_id() + " " +
//                                //pToA.get_facility_id() + " " +
//                                //pToA.get_date_created() + " " +
//                                //pToA.get_assessment_id() + " " +
//                                pageData.get(position).get_assessments_questions_id() + ":" +
//                                //position + ":" +
//                                real_pos
//
//                );
            }

            // TODO Auto-generated method stub

        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            _saveData.remove(position);
            _saveData.put(position, convertProgressToStr(progress));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            _saveData.remove(position);
            //Log.d("Checked:", convertCheckedToStr(isChecked));
            String pos = position + "";
            //Log.d("Position:", pos);
            _saveData.put(position, convertCheckedToStr(isChecked));
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
