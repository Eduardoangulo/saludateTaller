package info.androidhive.saluDate.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import info.androidhive.saluDate.ConexionService.api_connection;
import info.androidhive.saluDate.ConexionService.appointmentService;
import info.androidhive.saluDate.adapters.appointmentAdapter;
import info.androidhive.materialtabs.R;
import info.androidhive.saluDate.classes.appointment;
import info.androidhive.saluDate.classes.appointment_processed;
import info.androidhive.saluDate.classes.doctor;
import info.androidhive.saluDate.classes.schedule;
import info.androidhive.saluDate.classes.schedule_doctor;
import info.androidhive.saluDate.classes.speciality;
import info.androidhive.saluDate.classes.speciality_doctor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static info.androidhive.saluDate.ConexionService.VariablesGlobales.LogedID;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.conexion;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.TAG;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.URL_desarrollo;


public class OneFragment extends Fragment{

    private ArrayList<appointment> appointments= new ArrayList<>();
    private ArrayList<speciality_doctor> speciality_doctors= new ArrayList<>();
    private ArrayList<speciality> specialities= new ArrayList<>();
    private ArrayList<doctor> doctors= new ArrayList<>();
    private ArrayList<schedule_doctor> schedule_doctors= new ArrayList<>();
    private ArrayList<schedule> schedules= new ArrayList<>();

    private ListView rootView;
    private appointmentAdapter adapter1;

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
        rootView= (ListView) view.findViewById(R.id.list);
        adapter1=new appointmentAdapter(getActivity(), R.layout.list_appointment);
        conexion = new api_connection(getContext(), TAG, URL_desarrollo);
        getAppointments(conexion.getRetrofit());

        return view;
    }
    private void getAppointments(Retrofit retrofit) {
        appointmentService service = retrofit.create(appointmentService.class);
        Call<ArrayList<appointment>> Call = service.obtenerCitas();
        Call.enqueue(new Callback<ArrayList<appointment>>() {
            @Override
            public void onResponse(Call<ArrayList<appointment>> call, Response<ArrayList<appointment>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "Se obtuvieron citas");
                    appointments=response.body();
                    getSpecialityDoctor(conexion.getRetrofit());
                } else {
                        Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<appointment>> call, Throwable t) {
                Log.e(TAG, " onFailure-appointments-fragment: " + t.getMessage());
            }
        });
    }
    private void getSpecialityDoctor(Retrofit retrofit) {
        appointmentService service = retrofit.create(appointmentService.class);
        Call<ArrayList<speciality_doctor>> Call = service.obtenerEspecialidadDoctor();
        Call.enqueue(new Callback<ArrayList<speciality_doctor>>() {
            @Override
            public void onResponse(Call<ArrayList<speciality_doctor>> call, Response<ArrayList<speciality_doctor>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "Se obtuvieron especialidades-doctor");
                    speciality_doctors=response.body();
                    getDoctors(conexion.getRetrofit());
                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<speciality_doctor>> call, Throwable t) {
                Log.e(TAG, " onFailure-fragment: " + t.getMessage());
            }
        });
    }

    private void getDoctors(Retrofit retrofit) {
        appointmentService service = retrofit.create(appointmentService.class);
        Call<ArrayList<doctor>> Call = service.obtenerDoctores();
        Call.enqueue(new Callback<ArrayList<doctor>>() {
            @Override
            public void onResponse(Call<ArrayList<doctor>> call, Response<ArrayList<doctor>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "Se obtuvieron doctores");
                    doctors=response.body();
                    getSpecialties(conexion.getRetrofit());
                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<doctor>> call, Throwable t) {
                Log.e(TAG, " onFailure-Doctors-fragment: " + t.getMessage());
            }
        });
    }
    private void getSpecialties(Retrofit retrofit) {
        appointmentService service = retrofit.create(appointmentService.class);
        Call<ArrayList<speciality>> Call = service.obtenerEspecialidades();
        Call.enqueue(new Callback<ArrayList<speciality>>() {
            @Override
            public void onResponse(Call<ArrayList<speciality>> call, Response<ArrayList<speciality>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "Se obtuvieron especialidades");
                    specialities=response.body();
                    getScheduleDoctor(conexion.getRetrofit());
                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<speciality>> call, Throwable t) {
                Log.e(TAG, " onFailure-Speciality-fragment: " + t.getMessage());
            }
        });
    }

    private void getScheduleDoctor(Retrofit retrofit) {
        appointmentService service = retrofit.create(appointmentService.class);
        Call<ArrayList<schedule_doctor>> Call = service.obtenerDoctorHorario();
        Call.enqueue(new Callback<ArrayList<schedule_doctor>>() {
            @Override
            public void onResponse(Call<ArrayList<schedule_doctor>> call, Response<ArrayList<schedule_doctor>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "Se obtuvieron horarios-doctor");
                    schedule_doctors=response.body();
                    getScheldules(conexion.getRetrofit());
                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<schedule_doctor>> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });
    }
    private void getScheldules(Retrofit retrofit) {
        appointmentService service = retrofit.create(appointmentService.class);
        Call<ArrayList<schedule>> Call = service.obtenerHorarios();
        Call.enqueue(new Callback<ArrayList<schedule>>() {
            @Override
            public void onResponse(Call<ArrayList<schedule>> call, Response<ArrayList<schedule>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "Se obtuvieron horarios");
                    schedules=response.body();
                    placeholder();
                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<schedule>> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });
    }
    private void placeholder(){
        ArrayList<Integer> speciality_doctorsID= new ArrayList<>();
        ArrayList<Integer> specialitiesID= new ArrayList<>();
        ArrayList<Integer> doctorsID= new ArrayList<>();
        ArrayList<Integer> schedule_doctorsID= new ArrayList<>();
        ArrayList<Integer> schedulesID= new ArrayList<>();
        ArrayList<appointment_processed> citas= new ArrayList<>();
        for(int i=0; i<appointments.size(); i++){
            if(appointments.get(i).getPatient()!=LogedID){
                appointments.remove(i);
                i--;
            }
        }
        for(int i=0; i<speciality_doctors.size(); i++){
           speciality_doctorsID.add(speciality_doctors.get(i).getId());
        }
        for(int i=0; i<specialities.size(); i++){
            specialitiesID.add(specialities.get(i).getId());
        }
        for(int i=0; i<doctors.size(); i++){
            doctorsID.add(doctors.get(i).getId());
        }
        for(int i=0; i<schedule_doctors.size(); i++){
            schedule_doctorsID.add(schedule_doctors.get(i).getId());
        }
        for(int i=0; i<schedules.size(); i++){
            schedulesID.add(schedules.get(i).getId());
        }
        Log.i(TAG, appointments.size()+" ");
        for(int i=0; i<appointments.size(); i++){
            schedule_doctor currentScheduleDoctor=schedule_doctors.get(schedule_doctorsID.indexOf(appointments.get(i).getSchedule_doctor()));
            speciality_doctor currentSpecialityDoctor=speciality_doctors.get(speciality_doctorsID.indexOf(appointments.get(i).getSpeciality_doctor()));
            int currentDoctorID=doctorsID.indexOf(currentSpecialityDoctor.getDoctor());
            String doctorName=doctors.get(currentDoctorID).getPerson().getUser().getFirst_name()+" "+doctors.get(currentDoctorID).getPerson().getUser().getLast_name();
            String specialtyName=specialities.get(specialitiesID.indexOf(currentSpecialityDoctor.getSpeciality())).getName();
            String hora=schedules.get(schedulesID.indexOf(currentScheduleDoctor.getSchedule())).getStart_hour();
            String fecha=currentScheduleDoctor.getAvailability_date();
                Log.i(TAG, specialtyName+" "+doctorName+" "+fecha+" "+hora);
           citas.add(new appointment_processed(doctorName, specialtyName, hora, fecha, appointments.get(i).getId()));
        }
        Log.i(TAG, "se generaron citas pa mostrar");

        adapter1.addAll(citas);
        rootView.setAdapter(adapter1);
    }
}
