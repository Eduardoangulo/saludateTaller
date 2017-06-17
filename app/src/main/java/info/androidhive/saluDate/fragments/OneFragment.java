package info.androidhive.saluDate.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import info.androidhive.saluDate.adapters.appointmentAdapter;
import info.androidhive.materialtabs.R;
import info.androidhive.saluDate.classes.appointment;


public class OneFragment extends Fragment{

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        ListView rootView= (ListView) view.findViewById(R.id.list);

        appointmentAdapter adapter1=new appointmentAdapter(getActivity(), R.layout.list_appointment);
        adapter1.add(new appointment(R.drawable.user, "Diego Cayo", "Urología"));
        adapter1.add(new appointment(R.drawable.user, "Emerson Carnero", "Cardiología"));
        adapter1.add(new appointment(R.drawable.user, "Percy Moncada", "Pediatría"));
        adapter1.add(new appointment(R.drawable.user, "Sindy Estuchi", "Obstetricia"));

        rootView.setAdapter(adapter1);
        return view;
    }

}
