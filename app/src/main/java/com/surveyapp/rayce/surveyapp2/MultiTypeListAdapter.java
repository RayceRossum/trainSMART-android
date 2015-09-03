package com.surveyapp.rayce.surveyapp2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by Rayce on 8/7/2015.
 */


public class MultiTypeListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private String[][] data;

    public MultiTypeListAdapter(Context context, String[][] data) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.length;
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

        switch (data[position][1]) { // 1 is itemtype
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
            case "questionmulti":
                type = 4;
                break;
            case "title":
                type = 5;
                break;
        }

        return type;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        int type = getItemViewType(position);
        switch (type) {
            case 0: //Label
                EditFragment.labelViewHolder labelHolder;
                if (view == null) {
                    labelHolder = new EditFragment.labelViewHolder();
                    view = inflater.inflate(R.layout.edit_label, parent, false);

                    labelHolder.textView = (TextView) view.findViewById(R.id.textq);
                    labelHolder.textView.setText("Section One");

                    view.setTag(labelHolder);
                } else {
                    labelHolder = (EditFragment.labelViewHolder) view.getTag();
                }

                break;

            case 1: //1-10
                EditFragment.radioViewHolder radioHolder;
                if (view == null) {
                    radioHolder = new EditFragment.radioViewHolder();
                    view = inflater.inflate(R.layout.edit_question110, parent, false);

                    radioHolder.textView = (TextView) view.findViewById(R.id.textq);
                    radioHolder.seekBar = (SeekBar) view.findViewById(R.id.seekBar);

                    radioHolder.textView.setText("Is the candidate good at what they're doing?");

                    view.setTag(radioHolder);
                } else {
                    radioHolder = (EditFragment.radioViewHolder) view.getTag();
                }
                break;

            case 2: //Single line editText
                EditFragment.singleEditViewHolder singleEditHolder;
                if (view == null) {
                    singleEditHolder = new EditFragment.singleEditViewHolder();
                    view = inflater.inflate(R.layout.edit_questiontext, parent, false);

                    singleEditHolder.textView = (TextView) view.findViewById(R.id.textq);
                    singleEditHolder.editText = (EditText) view.findViewById(R.id.editText);

                    singleEditHolder.textView.setText("What does the candidate enjoy doing in their free time?");
                    singleEditHolder.editText.setText("This is a test entry");

                    view.setTag(singleEditHolder);
                } else {
                    singleEditHolder = (EditFragment.singleEditViewHolder) view.getTag();
                }

//                holder.editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                    public void onFocusChange(View v, boolean hasFocus) {
//                        if (!hasFocus) {
//                            final int position = v.getId();
//                            final EditText editText = (EditText) v;
//                            // Update Data here
//                        }
//                    }
//
//                });

                break;
            case 3: //Switch
                EditFragment.switchViewHolder switchHolder;
                if (view == null) {
                    switchHolder = new EditFragment.switchViewHolder();
                    view = inflater.inflate(R.layout.edit_questionyesno, parent, false);

                    switchHolder.textView = (TextView) view.findViewById(R.id.textq);
                    switchHolder.switchWidget = (Switch) view.findViewById(R.id.yesnoswitch);

                    switchHolder.textView.setText("Does the candidate like cats?");

                    view.setTag(switchHolder);
                } else {
                    switchHolder = (EditFragment.switchViewHolder) view.getTag();
                }

                break;

            case 4: //Multi line editText
                EditFragment.multiEditViewHolder multiEditHolder;
                if (view == null) {
                    multiEditHolder = new EditFragment.multiEditViewHolder();
                    view = inflater.inflate(R.layout.edit_questionmulti, parent, false);

                    multiEditHolder.textView = (TextView) view.findViewById(R.id.textq);
                    multiEditHolder.editText2 = (EditText) view.findViewById(R.id.editText2);

                    multiEditHolder.textView.setText("Does the candidate like the smell of bacon?");
                    multiEditHolder.editText2.setText("This is a test entry");

                    view.setTag(multiEditHolder);
                } else {
                    multiEditHolder = (EditFragment.multiEditViewHolder) view.getTag();
                }

//                holder.editText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//                    public void onFocusChange(View v, boolean hasFocus) {
//                        if (!hasFocus) {
//                            final int position = v.getId();
//                            final EditText editText2 = (EditText) v;
//                            // Update Data here
//                        }
//                    }
//                });

                break;


            case 5: //Title
                TextView text;
                if (view == null) {
                    view = inflater.inflate(R.layout.edit_title, parent, false);
                }
                text = (TextView) view.findViewById(R.id.textq);
                text.setText("Assessment, Rayce Rossum, 8/24/2015, 10132027");
                break;

        }
        return view;
    }
}
