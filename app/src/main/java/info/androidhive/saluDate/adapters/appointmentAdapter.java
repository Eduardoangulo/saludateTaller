package info.androidhive.saluDate.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import info.androidhive.materialtabs.R;
import info.androidhive.saluDate.classes.appointment_processed;

/**
 * Created by Luis on 05/05/2017.
 */

public class appointmentAdapter extends ArrayAdapter<appointment_processed> {
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
            appointment_processed currentAppointment = getItem(position);

            TextView speciality = (TextView) listItemView.findViewById(R.id.specialtyText);
            TextView doctorN = (TextView) listItemView.findViewById(R.id.doctorText);
            TextView hora = (TextView) listItemView.findViewById(R.id.hourText);
            TextView fecha = (TextView) listItemView.findViewById(R.id.dateText);
            ImageView img = (ImageView) listItemView.findViewById(R.id.pimage);

            speciality.setText(currentAppointment.getSpecialityName());
            doctorN.setText(currentAppointment.getDoctorName());
            hora.setText(currentAppointment.getHour());
            fecha.setText(currentAppointment.getDate());
            //img.setImageResource(currentAppointment.getDoc().getSpecialty().getImg());

            return listItemView;

    }

}
