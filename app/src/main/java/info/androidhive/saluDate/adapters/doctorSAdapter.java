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
import info.androidhive.saluDate.model.doctor;

/**
 * Created by Luis on 16/06/2017.
 */

public class doctorSAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<doctor> doctors;
    private LayoutInflater inflater;
    public doctorSAdapter(Activity activity, ArrayList<doctor> doctors){
        this.activity=activity;
        this.doctors=doctors;
        inflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount(){
        return doctors.size();
    }

    @Override
    public long getItemId(int i){
        return doctors.get(i).getId();
    }

    @Override
    public doctor getItem(int i) {
        return doctors.get(i);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View row= inflater.inflate(R.layout.spinner_row, null);
        TextView spinnerItem=(TextView) row.findViewById(R.id.spinnerItem);
        spinnerItem.setText(doctors.get(i).getPerson().getUser().getFirst_name()+" "+doctors.get(i).getPerson().getUser().getLast_name());
        return row;
    }
}
