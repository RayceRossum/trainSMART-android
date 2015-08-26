package com.surveyapp.rayce.surveyapp2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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
        TextView text;
        int type = getItemViewType(position);
        switch (type) {
            case 0:
                if (view == null) {
                    view = inflater.inflate(R.layout.edit_label, parent, false);
                }
                text = (TextView) view.findViewById(R.id.textq);
                text.setText("Section One");
                break;
            case 1:
                if (view == null) {
                    view = inflater.inflate(R.layout.edit_question110, parent, false);
                }
                text = (TextView) view.findViewById(R.id.textq);
                text.setText("Is the candidate good at what they're doing?");
                break;
            case 2:
                if (view == null) {
                    view = inflater.inflate(R.layout.edit_questiontext, parent, false);
                }
                text = (TextView) view.findViewById(R.id.textq);
                text.setText("What does the candidate enjoy doing in their free time?");
            break;
            case 3:
                if (view == null) {
                    view = inflater.inflate(R.layout.edit_questionyesno, parent, false);
                }
                text = (TextView) view.findViewById(R.id.textq);
                text.setText("Does the candidate like cats?");
            break;
            case 4:
                if (view == null) {
                    view = inflater.inflate(R.layout.edit_questionmulti, parent, false);
                }
                text = (TextView) view.findViewById(R.id.textq);
                text.setText("Does the candidate like the smell of bacon?");
            break;
            case 5:
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
