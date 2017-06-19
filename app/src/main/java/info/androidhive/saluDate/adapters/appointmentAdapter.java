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
import info.androidhive.saluDate.classes.speciality;

import static info.androidhive.saluDate.ConexionService.VariablesGlobales.speciality_img;

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

            TextView specialty = (TextView) listItemView.findViewById(R.id.specialtyText);
            TextView doctorN = (TextView) listItemView.findViewById(R.id.doctorText);
            TextView hora = (TextView) listItemView.findViewById(R.id.hourText);
            TextView fecha = (TextView) listItemView.findViewById(R.id.dateText);
            ImageView img = (ImageView) listItemView.findViewById(R.id.pimage);

        switch (currentAppointment.getSpecialityName()) {
                case "Urologia": speciality_img=R.drawable.user;
                    ;break;
                case "Cardiologia":speciality_img=R.drawable.saludate_logo_200x45;
                    ;break;
                case "Pediatria":speciality_img=R.mipmap.ic_launcher;
                    ;break;
                case "Obstetricia":speciality_img=R.mipmap.ic_launchrrrr;
                    ;break;
            }
            specialty.setText(currentAppointment.getSpecialityName());
            doctorN.setText(currentAppointment.getDoctorName());
            hora.setText(currentAppointment.getHour());
            fecha.setText(currentAppointment.getDate());
            img.setImageResource(speciality_img);

            return listItemView;

    }

}
