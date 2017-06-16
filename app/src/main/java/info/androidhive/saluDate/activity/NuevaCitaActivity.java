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
import info.androidhive.saluDate.classes.schedule;
import info.androidhive.saluDate.classes.speciality;
import info.androidhive.saluDate.classes.specialitySAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
    private Activity context=this;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevacita);
        specialitySpinner = (Spinner) findViewById(R.id.spinner_Especialidad);
        try{
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (Exception e){
            e.printStackTrace();
        }
        //INICIO
        conexion = new api_connection(getApplicationContext(), TAG, URL_desarrollo);
        getSpecialties(conexion.getRetrofit());

        //FIN
        programar_cita=(TextView) findViewById(R.id.button_programar_cita);
        programar_cita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),getResources().getString(R.string.title_activity_menu_principal),Toast.LENGTH_LONG);
                startActivity(new Intent(NuevaCitaActivity.this, MenuPrincipalActivity.class));
                finish();
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
                    //aca asigna lo cojido al array
                    Log.i(TAG, "Se obtuvieron especialidades");
                    ArrayList<speciality> especialidades = response.body();
                    specialitySAdapter adapter1 = new specialitySAdapter(context , especialidades);
                    specialitySpinner.setAdapter(adapter1);
                    specialitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        /**
                         * Called when a new item is selected (in the Spinner)
                         */
                        public void onItemSelected(AdapterView<?> parent, View view,
                                                   int pos, long id) {
                            Toast.makeText(context, "Hello Toast",Toast.LENGTH_SHORT).show();

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
            public void onFailure(Call<ArrayList<speciality>> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

     /* private void getScheldules(Retrofit retrofit) {


      appointmentService service = retrofit.create(appointmentService.class);
        Call<ArrayList<schedule>> patientCall = service.obtenerHorarios();

        patientCall.enqueue(new Callback<ArrayList<schedule>>() {
            @Override
            public void onResponse(Call<ArrayList<schedule>> call, Response<ArrayList<schedule>> response) {
                if (response.isSuccessful()) {
                    //aca asigna lo cojido al array
                    ArrayList<schedule> horarios = response.body();
                   // for(int i=0)
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
    }*/
}
