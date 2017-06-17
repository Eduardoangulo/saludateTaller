package info.androidhive.saluDate.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import info.androidhive.materialtabs.R;
import info.androidhive.saluDate.classes.appointment;

/**
 * Created by Luis on 05/05/2017.
 */

public class appointmentAdapter extends ArrayAdapter<appointment> {
    private int r;
    public appointmentAdapter(Activity context, int resource){
            super(context, resource);
            r=resource;
            }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View listItemView = convertView;
            if (listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(r, parent, false);
            }
            appointment currentAppointment = getItem(position);

            TextView specialty = (TextView) listItemView.findViewById(R.id.specialtyText);
            TextView doctorN = (TextView) listItemView.findViewById(R.id.doctorText);
            ImageView img = (ImageView) listItemView.findViewById(R.id.pimage);

            //speciality.setText(currentAppointment.getDoc().getSpecialty().getName());
            doctorN.setText(currentAppointment.getDoc().getPerson().getUser().getFirst_name());
            //img.setImageResource(currentAppointment.getDoc().getSpecialty().getImg());

            return listItemView;

    }

}
