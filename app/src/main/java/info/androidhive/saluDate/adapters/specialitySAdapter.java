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
import info.androidhive.saluDate.classes.speciality;

/**
 * Created by Luis on 16/06/2017.
 */

public class specialitySAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<speciality> specialities;
    private LayoutInflater inflater;
    public specialitySAdapter(Activity activity, ArrayList<speciality> specialities){
        this.activity=activity;
        this.specialities=specialities;
        inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount(){
        return specialities.size();
    }

    @Override
    public long getItemId(int i){
        return specialities.get(i).getId();
    }

    @Override
    public speciality getItem(int i) {
        return specialities.get(i);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View row= inflater.inflate(R.layout.spinner_row, null);
        TextView spinnerItem=(TextView) row.findViewById(R.id.spinnerItem);
        spinnerItem.setText(specialities.get(i).getName());
        return row;
    }
}
