package info.androidhive.saluDate.classes;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import info.androidhive.materialtabs.R;
/**
 * Created by Luis on 05/05/2017.
 */

public class appointmentAdapter extends ArrayAdapter<appointment> {
    private int r;
    private boolean phrases=false;
    private int color;
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

            specialty.setText(currentAppointment.getSpecialty());
            doctorN.setText(currentAppointment.getDoctorName());
            img.setImageResource(currentAppointment.getImage());

            return listItemView;

    }

}
