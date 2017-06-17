package info.androidhive.saluDate.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import info.androidhive.saluDate.adapters.appointmentAdapter;
import info.androidhive.materialtabs.R;


public class TwoFragment extends Fragment{

    public TwoFragment() {
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
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        ListView rootView= (ListView) view.findViewById(R.id.list);
        appointmentAdapter adapter=new appointmentAdapter(getActivity(), R.layout.list_appointment);

        rootView.setAdapter(adapter);
        return view;
    }

}
