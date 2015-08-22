package com.surveyapp.rayce.surveyapp2;

import android.content.Context;
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
    private String[] data;

    public MultiTypeListAdapter(Context context, String[] data) {
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
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        int type = -1;

        switch (data[position]) {
            case "text":
                type = 0;
                break;
            case "question110":
                type = 1;
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
                view = inflater.inflate(R.layout.edit_label, parent, false);
                text = (TextView) view.findViewById(R.id.textq);
                text.setText("Section One");
                break;
            case 1:
                view = inflater.inflate(R.layout.edit_question110, parent, false);
                text = (TextView) view.findViewById(R.id.textq);
                text.setText("Is the candidate good at what they're doing?");
                break;
        }

        return view;
    }
}
