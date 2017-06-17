package info.androidhive.saluDate.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import info.androidhive.materialtabs.R;
import info.androidhive.saluDate.classes.schedule;

/**
 * Created by Luis on 16/06/2017.
 */

public class scheduleSAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<schedule> schedules;
    private LayoutInflater inflater;
    public scheduleSAdapter(Activity activity, ArrayList<schedule> schedules){
        this.activity=activity;
        this.schedules=schedules;
        inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount(){
        return schedules.size();
    }

    @Override
    public long getItemId(int i){
        return schedules.get(i).getId();
    }

    @Override
    public schedule getItem(int i) {
        return schedules.get(i);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View row= inflater.inflate(R.layout.spinner_row, null);
        TextView spinnerItem=(TextView) row.findViewById(R.id.spinnerItem);
        spinnerItem.setText(schedules.get(i).getStart_hour()+" - "+schedules.get(i).getFinish_hour());
        return row;
    }
}
