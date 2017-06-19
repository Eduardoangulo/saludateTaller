package info.androidhive.saluDate.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InetAddress;
import java.util.ArrayList;

import info.androidhive.materialtabs.R;
import info.androidhive.saluDate.ConexionService.patientService;
import info.androidhive.saluDate.ConexionService.api_connection;
import info.androidhive.saluDate.classes.patient;
import info.androidhive.saluDate.classes.user;
import info.androidhive.saluDate.fragments.CustomDialogClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static info.androidhive.saluDate.ConexionService.VariablesGlobales.URL_desarrollo;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.LogedID;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.conexion;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.estado_user;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.TAG;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.pacientes;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.patient1;

public class LoginActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editTextUser;
    private EditText editTextPass;
    private Button btnSimpleTabs;
    private TextView txtOvidarContra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conexion = new api_connection(getApplicationContext(), TAG, URL_desarrollo);
        try {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnSimpleTabs = (Button) findViewById(R.id.btnIniciarSesion);
        editTextUser = (EditText) findViewById(R.id.edtxtUser);
        txtOvidarContra=(TextView)findViewById(R.id.txtOvidarContra);
        txtOvidarContra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlertDialog();
            }
        });

        editTextPass = (EditText) findViewById(R.id.edtxtPass);
        editTextPass.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
        editTextUser.setTransformationMethod(new NumericKeyBoardTransformationMethod());
        editTextPass.setTransformationMethod(new NumericKeyBoardTransformationMethod());

        txtOvidarContra=(TextView)findViewById(R.id.txtOvidarContra);

            obtenerDatos(conexion.getRetrofit());
            Toast.makeText(LoginActivity.this,getResources().getString(R.string.connection_error),Toast.LENGTH_LONG);


        btnSimpleTabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin(pacientes);
                Toast.makeText(LoginActivity.this,getResources().getString(R.string.connection_error),Toast.LENGTH_LONG);
            }
        });
    }
    //Only password keyboard
    private class NumericKeyBoardTransformationMethod extends PasswordTransformationMethod {
        @Override
        public CharSequence getTransformation(CharSequence source, View view) {
            return source;
        }
    }

    //obtiene el array de patients de la api
    private void obtenerDatos(Retrofit retrofit) {
        patientService service = retrofit.create(patientService.class);
        Call<ArrayList<patient>> patientCall = service.obtenerListaPacientes();

        patientCall.enqueue(new Callback<ArrayList<patient>>() {
            @Override
            public void onResponse(Call<ArrayList<patient>> call, Response<ArrayList<patient>> response) {
                if (response.isSuccessful()) {
                    pacientes = response.body();
                    //attemptLogin(pacientes);
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<patient>> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //coje el array de patientas y compara con lo que se ingreso en los campos del login
    private void attemptLogin(ArrayList<patient> pacientes){
        user user1=null;
        patient1=null;
        Log.i(TAG, " ingresado: " + editTextUser.getText());
        for(int i=0; i<pacientes.size(); i++){
            Log.i(TAG, " username/login: " + pacientes.get(i).getPerson().getUser().getUsername());
            if(pacientes.get(i).getPerson().getUser().getUsername().equals(editTextUser.getText().toString())) {
                Log.i(TAG, "usuario correcto");
                patient1=pacientes.get(i);
                user1 = pacientes.get(i).getPerson().getUser();
                break;
            }
        }
        if(user1!=null){
            Log.i(TAG, "NO ES NULO");
            if(editTextPass.getText().toString().equals(user1.getPassword())){
                Log.i(TAG, "contraseña correcta");
                estado_user=true;
                LogedID =patient1.getId();
                patient1.getPerson().setStatus("Conectado");
                Log.i(TAG, " id paciente: " + patient1.getId());
                updateStatus(conexion.getRetrofit(), patient1);
                goMainScreen();
            }
            else{
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.login_fail), Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Log.i(TAG, "ES NULO");
            Toast.makeText(getApplicationContext(), getResources().getString(R.string.login_fail), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateStatus(Retrofit retrofit, patient p){
        Log.i(TAG, " Estado civil del paciente " + p.getCivil_status());
        patientService service = retrofit.create(patientService.class);
        service.guardarStatus(p.getId(),p).enqueue(new Callback<patient>() {

            @Override
            public void onResponse(Call<patient> call, Response<patient> response) {
                try
                {
                    if (response.isSuccessful()){
                        Log.i(TAG, " UPDATED PAPU! ID" + response.body().getId());
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
            public void onFailure(Call<patient> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
            }
        });
    }

    //si esta logeado ir a mainscreen
    private void goMainScreen(){
        Intent intent= new Intent(this, MenuPrincipalActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
    private void showAlertDialog(){
        CustomDialogClass cdd=new CustomDialogClass(LoginActivity.this);
        cdd.show();
    }

}