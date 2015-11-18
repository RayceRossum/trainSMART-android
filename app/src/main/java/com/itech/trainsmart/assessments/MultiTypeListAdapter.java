

package com.itech.trainsmart.assessments;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Rayce on 8/7/2015.
 */


public class MultiTypeListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    public List<EditPageObject> pageData;
    public HashMap saveData = new HashMap();
    public DBHelper dbHelp;
    public PersonToAssessments pToA;

    public MultiTypeListAdapter(Context context, List<EditPageObject> pageData, PersonToAssessments pToA) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.pageData = pageData;
        this.pToA = pToA;
        dbHelp = new DBHelper(context);
    }

    @Override
    public int getCount() {
        return pageData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 6;
    }

    @Override
    public int getItemViewType(int position) {
        int type = -1;

        switch (pageData.get(position)._itemtype) {
            case "text":
                type = 0;
                break;
            case "question110":
                type = 1;
                break;
            case "questiontext":
                type = 2;
                break;
            case "questionyesno":
                type = 3;
                break;
            case "questiontextarea":
                type = 4;
                break;
            case "title":
                type = 5;
                break;
        }

        return type;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        View view = convertView;
        EditFragment.ViewHolder holder;
        int type = getItemViewType(position);
        holder = new EditFragment.ViewHolder(saveData, pageData, dbHelp, pToA);
        holder.position = position;
        //Log.d("request!", "pageData: " + pageData.get(position).get_assessments_questions_id() + ":" + pageData.get(position).get_answer() );
        //holder = new EditFragment.ViewHolder(pageData, position);
        if (convertView == null) {
            switch (type) {
                case 0: //Label
                    view = inflater.inflate(R.layout.edit_label, parent, false);
                    holder.textView = (TextView) view.findViewById(R.id.textq);
                    //holder.textView.setText("Section One");
                    view.setTag(holder);
                    break;

                case 1: //1-10
                    view = inflater.inflate(R.layout.edit_question110, parent, false);
                    holder.textView = (TextView) view.findViewById(R.id.textq);
                    holder.discreteSeekBar = (com.itech.trainsmart.assessments.DiscreteSeekBar) view.findViewById(R.id.discreteSeekBar);
                    holder.discreteSeekBar.setProgress(convertProgressToInt(pageData.get(position).get_answer()));
                    holder.position = position;
//                    holder.seekBar.setOnSeekBarChangeListener(holder);

                    DiscreteSeekBar.OnProgressChangeListener listener = new DiscreteSeekBar.OnProgressChangeListener() {

                        @Override
                        public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                            Log.d("request!", "onProgressChanged:value: " + value + " " + position );

                            pageData.get(position).set_answer(convertProgressToStr(value));
                            dbHelp.setEditPageRow(pToA, pageData.get(position).get_assessments_questions_id(), convertProgressToStr(value));
                        }

                        @Override
                        public void onStartTrackingTouch(DiscreteSeekBar seekBar) {
                        }

                        @Override
                        public void onStopTrackingTouch(DiscreteSeekBar seekBar) {
                        }
                    };
                    holder.discreteSeekBar.setOnProgressChangeListener(listener);

                    view.setTag(holder);
                    break;

                case 2: //Single line editText
                    view = inflater.inflate(R.layout.edit_questiontext, parent, false);
                    holder.textView = (TextView) view.findViewById(R.id.textq);
//                  http://stackoverflow.com/questions/9438676/edittext-in-listview-without-it-recycling-input
//                  holder.editText.setText(pageData.get(position).get_answer());
                    holder.editText = (EditText) view.findViewById(R.id.editText);
                    String _text = pageData.get(position).get_answer();
                    holder.editText.setText(_text == null ? "" : _text);
                    holder.position = position;
                    holder.editText.addTextChangedListener(holder);
                    view.setTag(holder);
                    break;

                case 3: //Switch
                    view = inflater.inflate(R.layout.edit_questionyesno, parent, false);
                    holder.textView = (TextView) view.findViewById(R.id.textq);
                    holder.switchWidget = (Switch) view.findViewById(R.id.yesnoswitch);

                    holder.position = position;
                    String checked = pageData.get(position).get_answer();
                    holder.switchWidget.setChecked(convertStrToChecked(checked));
//                    Log.d("Checked:", checked);
                    holder.switchWidget.setOnCheckedChangeListener(holder);
                    view.setTag(holder);
                    break;

                case 4: //Multi line editText
                    view = inflater.inflate(R.layout.edit_questionmulti, parent, false);
                    holder.textView = (TextView) view.findViewById(R.id.textq);
                    holder.textView.setText(pageData.get(position).get_question());
//                    Log.d("request!", "get_question: " + pageData.get(position).get_question());
                    holder.editText2 = (EditText) view.findViewById(R.id.editText2);
                    String _text2 = pageData.get(position).get_answer();
                    holder.editText2.setText(_text2 == null ? "" : _text2);
                    holder.position = position;
                    holder.editText2.addTextChangedListener(holder);
                    view.setTag(holder);
                    break;

                case 5: //Title
                    view = inflater.inflate(R.layout.edit_title, parent, false);
                    holder.textView = (TextView) view.findViewById(R.id.textq);
                    //holder.textView.setText("Assessment, Rayce Rossum, 8/24/2015, 10132027");
                    view.setTag(holder);
                    break;

            }
        }
        else {
            holder = (EditFragment.ViewHolder) view.getTag();

            switch(type) {
                case 1:
                    holder.discreteSeekBar.setProgress(convertProgressToInt(pageData.get(position).get_answer()));
                    break;
                case 2:
                    holder.editText.setText(pageData.get(position).get_answer());
                    break;
                case 3:
                    holder.switchWidget.setChecked(convertStrToChecked(pageData.get(position).get_answer()));
                    //Log.d("Answer:", pageData.get(position).get_answer());
                    break;
                case 4:
                    holder.editText2.setText(pageData.get(position).get_answer());
                    break;
            }
            holder.position = position;
        }
        holder.textView.setText(pageData.get(position).get_question());
//        Log.d("request!", "get_question: " + pageData.get(position).get_question());
        return view;
    }

    public int convertProgressToInt (String progressStr) {
        int value = -1;
        switch (progressStr) {
            case "F" :
                value = 0;
                break;
            case "A" :
                value = 1;
                break;
            case "B" :
                value = 2;
                break;
            case "C" :
                value = 3;
                break;
            case "D" :
                value = 4;
                break;
            case "E" :
                value = 5;
                break;
        }
        return value;
    }

    public boolean convertStrToChecked (String checked) {
        if (checked.equals("A")) {
            return true;
        } else {
            return false;
        }
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
}

