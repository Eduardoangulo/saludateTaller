package info.androidhive.saluDate.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import info.androidhive.saluDate.classes.appointment;
import info.androidhive.saluDate.classes.appointmentAdapter;
import info.androidhive.materialtabs.R;


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
        for(int i=0;i<20;i++)
            adapter1.add(new appointment(R.drawable.ic_tab_contacts, "Eduardo Angulo", "Urologia"));

        rootView.setAdapter(adapter1);
        return view;
    }

}
