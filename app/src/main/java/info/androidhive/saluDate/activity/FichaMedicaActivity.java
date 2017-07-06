package info.androidhive.saluDate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import info.androidhive.materialtabs.R;
import info.androidhive.saluDate.ConexionService.api_connection;
import info.androidhive.saluDate.ConexionService.patientService;
import info.androidhive.saluDate.model.MedicalRecord;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static info.androidhive.saluDate.ConexionService.VariablesGlobales.LogedID;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.URL_desarrollo;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.TAG;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.conexion;

/**
 * Created by eduardo on 5/1/17.
 */

public class FichaMedicaActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private Switch switch_info;
    private MedicalRecord mr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ficha_medica);

        //INICIO
        conexion = new api_connection(getApplicationContext(), TAG, URL_desarrollo);
        obtenerDatos(conexion.getRetrofit());
        //FIN

        try{
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (Exception e){
            e.printStackTrace();
        }
        switch_info = (Switch)findViewById(R.id.switch_info);
        switch_info.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean bChecked) {
                if (bChecked) {
                    Toast.makeText(FichaMedicaActivity.this, getResources().getString(R.string.switch_info_true), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(FichaMedicaActivity.this, getResources().getString(R.string.switch_info_false), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    //obtener datooos
    private void obtenerDatos(Retrofit retrofit) {
        patientService service = retrofit.create(patientService.class);
        Call<MedicalRecord> patientCall = service.obtenerFichaMedica(LogedID);

        patientCall.enqueue(new Callback<MedicalRecord>() {
            @Override
            public void onResponse(Call<MedicalRecord> call, Response<MedicalRecord> response) {
                if (response.isSuccessful()) {
                    //aca asigna lo cojido al array
                    mr = response.body();
                    displayMedicalRecord(mr);
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, " onResponse: " + response.errorBody());
                    goMainScreen();
                }
            }

            @Override
            public void onFailure(Call<MedicalRecord> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
                goMainScreen();
            }
        });
    }

    private void displayMedicalRecord(MedicalRecord mr){
        ArrayList<TextView> mrLayout= new ArrayList<>();
        int id_image;

        switch (mr.getPatient().getPerson().getGender()){
            case "M":id_image =R.drawable.profile_man;break;
            case "F":id_image =R.drawable.profile_woman;break;
            default:id_image = R.drawable.user;
        }

        ImageView imageView = (ImageView)findViewById(R.id.pimage);
        imageView.setImageResource(id_image);

        //mrLayout.add((ImageView)findViewById(R.id.pimage))
        mrLayout.add((TextView)findViewById(R.id.mrName));
        mrLayout.add((TextView)findViewById(R.id.mrBDay));
        mrLayout.add((TextView)findViewById(R.id.mrOrganos));
        mrLayout.add((TextView)findViewById(R.id.mrWeight));
        mrLayout.add((TextView)findViewById(R.id.mrHeight));
        mrLayout.add((TextView)findViewById(R.id.mrBloodType));
        mrLayout.get(0).setText(mr.getPatient().getPerson().getUser().getFirst_name()+" "+mr.getPatient().getPerson().getUser().getLast_name());
        mrLayout.get(1).setText(mr.getPatient().getPerson().getBorn_date());
        mrLayout.get(2).setText(mr.getHarmful_habits());
        mrLayout.get(3).setText(String.format("%1$.1f kg.",mr.getWeight()));
        mrLayout.get(4).setText(String.format("%1$.2f m.",mr.getHeight()));
        mrLayout.get(5).setText(mr.getBlood_type());



    }
    private void goMainScreen(){
        Intent intent= new Intent(this, MenuPrincipalActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
