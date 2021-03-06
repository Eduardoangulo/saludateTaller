package info.androidhive.saluDate.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import info.androidhive.saluDate.model.appointment;
import info.androidhive.saluDate.classes.appointment_processed;
import info.androidhive.saluDate.model.doctor;
import info.androidhive.saluDate.model.schedule;
import info.androidhive.saluDate.model.schedule_doctor;
import info.androidhive.saluDate.model.speciality;
import info.androidhive.saluDate.model.speciality_doctor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static info.androidhive.saluDate.ConexionService.VariablesGlobales.LogedID;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.conexion;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.TAG;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.URL_desarrollo;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.historialCitas;

public class OneFragment extends Fragment{

    private ArrayList<appointment> appointments= new ArrayList<>();
    private ArrayList<speciality_doctor> speciality_doctors= new ArrayList<>();
    private ArrayList<speciality> specialities= new ArrayList<>();
    private ArrayList<doctor> doctors= new ArrayList<>();
    private ArrayList<schedule_doctor> schedule_doctors= new ArrayList<>();
    private ArrayList<schedule> schedules= new ArrayList<>();

    private ArrayList<Integer> speciality_doctorsID= new ArrayList<>();
    private ArrayList<Integer> specialitiesID= new ArrayList<>();
    private ArrayList<Integer> doctorsID= new ArrayList<>();
    private ArrayList<Integer> schedule_doctorsID= new ArrayList<>();
    private ArrayList<Integer> schedulesID= new ArrayList<>();
    private ArrayList<Integer> appointmentsID= new ArrayList<>();

    private SwipeRefreshLayout refresh;

    private ListView rootView;
    private appointmentAdapter adapter1;

    public static int posicion;
    public static boolean item_elegido=false;
    public static ArrayList<appointment_processed> citas;

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
        rootView.canScrollVertically(0);
        rootView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        view.setActivated(true);

        refresh= (SwipeRefreshLayout)view.findViewById(R.id.swiperefresh);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshContent();
            }
        });
        refresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));

        adapter1=new appointmentAdapter(getActivity(), R.layout.list_appointment);
        conexion = new api_connection(getContext(), TAG, URL_desarrollo);
        getAppointments(conexion.getRetrofit());

        rootView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                posicion=position;
                view.setSelected(true);
                item_elegido=true;
            }
        });

        return view;
    }
    private void refreshContent(){
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                conexion.retrofitLoad();
                if(conexion.getRetrofit()!=null){
                    getAppointments(conexion.getRetrofit());
                }
                refresh.setRefreshing(false);
            }
        },1500);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment1,menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.deleteAppointment:
                DeleteAppointment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void DeleteAppointment(){
        if(item_elegido){

            int currentAppointment=citas.get(posicion).getId();

            appointments.get(appointmentsID.indexOf(currentAppointment)).setStatus("Cancelado");

            cancelarCita(conexion.getRetrofit(),appointments.get(appointmentsID.indexOf(currentAppointment)));
            item_elegido = false;

            Toast.makeText(rootView.getContext(),"Se elimino la cita seleccionada",Toast.LENGTH_SHORT).show();

            citas.remove(posicion);
            adapter1.addAll(citas);
            rootView.setAdapter(adapter1);

            Intent intent = getActivity().getIntent();
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            getActivity().finish();
            startActivity(intent);
            getActivity().overridePendingTransition(0,0);
        }
        else {
            Toast.makeText(rootView.getContext(),"Elija un elemento de la lista",Toast.LENGTH_SHORT).show();
        }
    }


    private void cancelarCita(Retrofit retrofit, appointment appointment){

        appointmentService service = retrofit.create(appointmentService.class);
        service.cancelarCita(appointment.getId(),appointment).enqueue(new Callback<appointment>() {

            @Override
            public void onResponse(Call<appointment> call, Response<appointment> response) {
                try
                {
                    if (response.isSuccessful()){
                        Log.i(TAG, " Se elimino la fila: " + response.body().getId());
                    } else {
                        Log.e(TAG, " onResponse: " + response.errorBody().toString());
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

            }
            @Override
            public void onFailure(Call<appointment> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });
    }

    public void getAppointments(Retrofit retrofit) {

        if(appointments!=null){
            appointments.clear();
            adapter1.clear();
        }

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

        citas= new ArrayList<>();
        for(int i=0; i<appointments.size(); i++){
            if(appointments.get(i).getPatient()!=LogedID || !appointments.get(i).getStatus().equals("Por Atender")){
                appointments.remove(i);
                i--;
                    Log.i(TAG, "Removio un appointment");
            }
            else{
                appointmentsID.add(appointments.get(i).getId());
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
            citas.add(new appointment_processed(doctorName, specialtyName, fecha, hora, appointments.get(i).getStatus(), appointments.get(i).getId()));
        }
        Log.i(TAG, "se generaron citas pa mostrar");
        historialCitas.addAll(citas);
        adapter1.addAll(citas);
        rootView.setAdapter(adapter1);
    }
}