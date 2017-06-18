package info.androidhive.saluDate.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import info.androidhive.materialtabs.R;
import info.androidhive.saluDate.ConexionService.api_connection;
import info.androidhive.saluDate.ConexionService.appointmentService;
import info.androidhive.saluDate.adapters.doctorSAdapter;
import info.androidhive.saluDate.adapters.scheduleSAdapter;
import info.androidhive.saluDate.classes.appointment;
import info.androidhive.saluDate.classes.doctor;
import info.androidhive.saluDate.classes.schedule;
import info.androidhive.saluDate.classes.schedule_doctor;
import info.androidhive.saluDate.classes.speciality;
import info.androidhive.saluDate.adapters.specialitySAdapter;
import info.androidhive.saluDate.classes.speciality_doctor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static info.androidhive.saluDate.ConexionService.VariablesGlobales.LogedID;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.TAG;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.URL_desarrollo;

/**
 * Created by gustavo on 03/05/17.
 */

public class NuevaCitaActivity extends AppCompatActivity{
    private Toolbar toolbar;
    public TextView programar_cita;
    private api_connection conexion;
    private Spinner specialitySpinner;
    private Spinner doctorSpinner;
    private Spinner scheduleSpinner;
    private Activity context=this;

    private Integer currentSpeciality;
    private Integer currentDoctor;
    private Integer currentSchedule;
    private Integer currentSpecialty_doctor=0;
    private Integer currentSchedule_doctor=0;

    ArrayList<speciality_doctor> specialty_doctorIDS= new ArrayList<>();
    ArrayList<schedule_doctor> schedule_doctorIDS= new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevacita);
        try{
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (Exception e){
            e.printStackTrace();
        }

        //Iniciar Spinners--------------------------------------------------
        specialitySpinner = (Spinner) findViewById(R.id.spinner_Especialidad);
        doctorSpinner = (Spinner) findViewById(R.id.spinner_Doctor);
        scheduleSpinner = (Spinner) findViewById(R.id.spinner_Horario);
        //-----------------------------------------------------------------
        //INICIO
        conexion = new api_connection(getApplicationContext(), TAG, URL_desarrollo);
        getSpecialties(conexion.getRetrofit());
        //FIN

        programar_cita=(TextView) findViewById(R.id.button_programar_cita);
        programar_cita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentSchedule_doctor!=0){
                    Log.i(TAG, "Entró a nueva cita");
                    appointment cita= new appointment(currentSchedule_doctor, currentSpecialty_doctor, LogedID, "desde app", "desde app");
                    appointmentService service = conexion.getRetrofit().create(appointmentService.class);
                    Call<appointment> Call = service.crearNuevaCita(cita);
                    Call.enqueue(new Callback<appointment>() {
                        @Override
                        public void onResponse(Call<appointment> call, Response<appointment> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(getApplicationContext(),"Nueva cita creada",Toast.LENGTH_LONG);
                                startActivity(new Intent(NuevaCitaActivity.this, MenuPrincipalActivity.class));
                                finish();
                            } else {
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
                                Log.e(TAG, " onResponse: " + response.errorBody());
                            }
                        }
                        @Override
                        public void onFailure(Call<appointment> call, Throwable t) {
                            Log.e(TAG, " onFailure: " + t.getMessage());
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
    }

    //ESTO ES PARA CONSEGUIR TODAS LAS ESPECIALIDADES
    private void getSpecialties(Retrofit retrofit) {
        appointmentService service = retrofit.create(appointmentService.class);
        Call<ArrayList<speciality>> Call = service.obtenerEspecialidades();
        Call.enqueue(new Callback<ArrayList<speciality>>() {
            @Override
            public void onResponse(Call<ArrayList<speciality>> call, Response<ArrayList<speciality>> response) {
                if (response.isSuccessful()) {
                    //aca asigna lo cojido al array
                    Log.i(TAG, "Se obtuvieron especialidades");
                    final specialitySAdapter adapter1 = new specialitySAdapter(context , response.body());
                    specialitySpinner.setAdapter(adapter1);
                    specialitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view,
                                                   int pos, long id) {
                            currentSpeciality=adapter1.getItem(pos).getId();
                            getSpecialityDoctor(conexion.getRetrofit());
                        }
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } else {
                    //Toast.makeText(getApplicationContext(), getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<speciality>> call, Throwable t) {
                Log.e(TAG, " onFailure-Speciality: " + t.getMessage());
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //ESTO ES PARA CONSEGUIR TABLA INTERMEDIA ESPECIALIDAD-DOCTOR
    private void getSpecialityDoctor(Retrofit retrofit) {
        appointmentService service = retrofit.create(appointmentService.class);
        Call<ArrayList<speciality_doctor>> Call = service.obtenerEspecialidadDoctor();
        Call.enqueue(new Callback<ArrayList<speciality_doctor>>() {
            @Override
            public void onResponse(Call<ArrayList<speciality_doctor>> call, Response<ArrayList<speciality_doctor>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "Se obtuvieron especialidades-doctor");
                    getDoctors(conexion.getRetrofit(), response.body());
                } else {
                    //Toast.makeText(getApplicationContext(), getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<speciality_doctor>> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
                //Toast.makeText(getApplicationContext(), getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //ESTO FILTRA DOCTOR POR ESPECIALIDAD USANDO LA TABLA INTERMEDIA!
    private ArrayList<doctor> filterDoctors(ArrayList<speciality_doctor> speciality_doctors, ArrayList<doctor> doctors){
        ArrayList<Integer> doctorIDS= new ArrayList<>();
        for(int i=0; i<speciality_doctors.size(); i++) {
            if(speciality_doctors.get(i).getSpeciality()==currentSpeciality){
                doctorIDS.add(speciality_doctors.get(i).getDoctor());
                specialty_doctorIDS.add(speciality_doctors.get(i));
            }
        }
        for(int i=0; i<doctors.size(); i++) {
            if(!doctorIDS.contains(doctors.get(i).getId())){
                doctors.remove(i);
                i--;
            }
        }
        return doctors;
    }

    //ESTO ES PA CONSEGUIR TODOS LOS DOCTORES
    private void getDoctors(Retrofit retrofit,  final ArrayList<speciality_doctor> speciality_doctors) {
        appointmentService service = retrofit.create(appointmentService.class);
        Call<ArrayList<doctor>> Call = service.obtenerDoctores();
        Call.enqueue(new Callback<ArrayList<doctor>>() {
            @Override
            public void onResponse(Call<ArrayList<doctor>> call, Response<ArrayList<doctor>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "Se obtuvieron doctores");
                    final doctorSAdapter adapter2 = new doctorSAdapter(context , filterDoctors(speciality_doctors, response.body()));
                    doctorSpinner.setAdapter(adapter2);
                    doctorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view,
                                                   int pos, long id) {
                            currentDoctor=adapter2.getItem(pos).getId();
                            getCurrentSpecialtyDoctor();
                            getScheduleDoctor(conexion.getRetrofit());

                        }
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } else {
                    //Toast.makeText(getApplicationContext(), getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<doctor>> call, Throwable t) {
                Log.e(TAG, " onFailure-Doctors: " + t.getMessage());
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCurrentSpecialtyDoctor(){
        for(int i=0; i<specialty_doctorIDS.size(); i++){
            if(specialty_doctorIDS.get(i).getDoctor()==currentDoctor){
                currentSpecialty_doctor=specialty_doctorIDS.get(i).getId();
                break;
            }
        }
        Log.i(TAG, "Specialty_doctor: "+currentSpecialty_doctor);
    }

    //ESTO ES PA OBTENER LA TABLA INTERMEDIA DOCTOR HORARIO
    private void getScheduleDoctor(Retrofit retrofit) {
        appointmentService service = retrofit.create(appointmentService.class);
        Call<ArrayList<schedule_doctor>> Call = service.obtenerDoctorHorario();
        Call.enqueue(new Callback<ArrayList<schedule_doctor>>() {
            @Override
            public void onResponse(Call<ArrayList<schedule_doctor>> call, Response<ArrayList<schedule_doctor>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "Se obtuvieron horarios-doctor");
                    getScheldules(conexion.getRetrofit(), response.body());
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<schedule_doctor>> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //ESTO ES PARA FILTRAR LOS HORARIOS POR DOCTOR USANDO TABLA INTERMEDIA
    private ArrayList<schedule> filterHorarios(ArrayList<schedule_doctor> schedule_doctors, ArrayList<schedule> schedules){
        ArrayList<Integer> schedulesID= new ArrayList<>();
        for(int i=0; i<schedule_doctors.size(); i++) {
            if(schedule_doctors.get(i).getDoctor()==currentDoctor){
                schedulesID.add(schedule_doctors.get(i).getSchedule());
                schedule_doctorIDS.add(schedule_doctors.get(i));
            }
        }
        for(int i=0; i<schedules.size(); i++) {
            if(!schedulesID.contains(schedules.get(i).getId())){
                schedules.remove(i);
                i--;
            }
        }
        return schedules;
    }

    //ESTO ES PARA OBTENER TODOS LOS HORARIOS
    private void getScheldules(Retrofit retrofit, final ArrayList<schedule_doctor> schedule_doctors) {
      appointmentService service = retrofit.create(appointmentService.class);
        Call<ArrayList<schedule>> Call = service.obtenerHorarios();
        Call.enqueue(new Callback<ArrayList<schedule>>() {
            @Override
            public void onResponse(Call<ArrayList<schedule>> call, Response<ArrayList<schedule>> response) {
                if (response.isSuccessful()) {
                    Log.i(TAG, "Se obtuvieron horarios");
                    final scheduleSAdapter adapter3 = new scheduleSAdapter(context , filterHorarios(schedule_doctors, response.body()));
                    scheduleSpinner.setAdapter(adapter3);
                    scheduleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        public void onItemSelected(AdapterView<?> parent, View view,
                                                   int pos, long id) {
                            currentSchedule=adapter3.getItem(pos).getId();
                            getCurrentScheduleDoctor();

                        }
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<schedule>> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCurrentScheduleDoctor(){
        for(int i=0; i<schedule_doctorIDS.size(); i++){
            if(schedule_doctorIDS.get(i).getSchedule()==currentSchedule){
                currentSchedule_doctor=schedule_doctorIDS.get(i).getId();
                break;
            }
        }
        Log.i(TAG, "Schedule_doctor: "+currentSchedule_doctor);
    }
}
