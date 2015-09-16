package com.surveyapp.rayce.surveyapp2;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import java.util.List;


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
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static String TAG = "editTag";
    DBHelper dbHelp;
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     //* @param param1 Parameter 1.
     //* @param param2 Parameter 2.
     * @return A new instance of fragment EditFragment.
     */
    // TODO: Rename and change types and number of parameters
  //  public static EditFragment newInstance(String param1, String param2) {
    public static EditFragment newInstance() {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

   // public EditFragment() {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        ListView listView = (ListView)view.findViewById(R.id.editListView);
        PersonToAssessments pToA = dbHelp.getPersonToAssessments(19);
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
        //List<EditPageObject> pageData;
        public HashMap _saveData = new HashMap();
        public DBHelper dbhelp;
        public PersonToAssessments pToA;
        public int position;
        public TextView textView;
        public Switch switchWidget;
        public EditText editText;
        public EditText editText2;
        public SeekBar seekBar;
//        public DBHelper dbHelp;
//        public PersonToAssessments pToA;
//        public List<EditPageObject> pageData;

        public ViewHolder(HashMap<String, Integer> saveData, List<EditPageObject> pageData,  DBHelper dbHelp, PersonToAssessments pToA) {
            this._saveData = saveData;
//            this.dbHelp = dbHelp;
//            this.pToA = pToA;
//            this.pageData = pageData;
        }

        public void afterTextChanged(Editable editable) {
            //Log.d("request!", "afterTextChanged:editable: " + editable.toString());

            _saveData.remove(position);
            _saveData.put(position, editable.toString());
        }

        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub

        }

        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {

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
