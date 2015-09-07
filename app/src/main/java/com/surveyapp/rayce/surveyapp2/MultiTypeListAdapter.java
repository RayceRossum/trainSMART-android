package com.surveyapp.rayce.surveyapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rayce on 8/7/2015.
 */


public class MultiTypeListAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private String[][] data;
    private List<EditPageObject> pageData;

    public MultiTypeListAdapter(Context context, List<EditPageObject> pageData) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.data = data;
        this.pageData = pageData;
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
        EditFragment.ViewHolder holder;
        int type = getItemViewType(position);
        if (view == null) {
            holder = new EditFragment.ViewHolder();
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
                    holder.seekBar = (SeekBar) view.findViewById(R.id.seekBar);

                    //holder.textView.setText("Is the candidate good at what they're doing?");

                    view.setTag(holder);

                    break;

                case 2: //Single line editText
                    view = inflater.inflate(R.layout.edit_questiontext, parent, false);

                    holder.textView = (TextView) view.findViewById(R.id.textq);
                    holder.editText = (EditText) view.findViewById(R.id.editText);

                    //holder.textView.setText("What does the candidate enjoy doing in their free time?");

                    view.setTag(holder);

                    break;

                case 3: //Switch
                    view = inflater.inflate(R.layout.edit_questionyesno, parent, false);

                    holder.textView = (TextView) view.findViewById(R.id.textq);
                    holder.switchWidget = (Switch) view.findViewById(R.id.yesnoswitch);
                    //holder.textView.setText("Does the candidate like cats?");

                    view.setTag(holder);

                    break;

                case 4: //Multi line editText
                    view = inflater.inflate(R.layout.edit_questionmulti, parent, false);

                    holder.textView = (TextView) view.findViewById(R.id.textq);
                    holder.editText2 = (EditText) view.findViewById(R.id.editText2);
                    //holder.textView.setText("Does the candidate like the smell of bacon?");

                    view.setTag(holder);

                    break;

                case 5: //Title
                    view = inflater.inflate(R.layout.edit_title, parent, false);

                    holder.textView = (TextView) view.findViewById(R.id.textq);
                    //holder.textView.setText("Assessment, Rayce Rossum, 8/24/2015, 10132027");

                    view.setTag(holder);

                    break;

            }
        } else {
            holder = (EditFragment.ViewHolder) view.getTag();
        }

        holder.textView.setText(pageData.get(position)._question);
        //holder.textView.setText(getQuestion(position));
        return view;
    }
}
