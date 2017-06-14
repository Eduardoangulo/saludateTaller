package info.androidhive.saluDate.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import info.androidhive.materialtabs.R;
import info.androidhive.saluDate.ConexionService.PersonService;
import info.androidhive.saluDate.classes.person;
import info.androidhive.saluDate.classes.user;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static info.androidhive.saluDate.ConexionService.VariablesGlobales.URL_desarrollo;
import static info.androidhive.saluDate.ConexionService.VariablesGlobales.estado_user;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editTextUser;
    private TextView prueba;
    private EditText editTextPass;
    private String users[];
    private Button btnSimpleTabs;
    private Retrofit retrofit;
    private final String TAG= "PERSONAS";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        } catch (Exception e) {
            e.printStackTrace();
        }

        btnSimpleTabs = (Button) findViewById(R.id.btnIniciarSesion);
        editTextUser = (EditText) findViewById(R.id.edtxtUser);
        editTextPass = (EditText) findViewById(R.id.edtxtPass);


        btnSimpleTabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retrofitLoad(URL_desarrollo);

            }
        });
    }

    //carga el retrofit con determinada url
    private void retrofitLoad(String url){
        if(isOnline()){
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            obtenerDatos();
        }
        else{
            Toast.makeText(getApplicationContext(), "Conectese a Internet", Toast.LENGTH_SHORT).show();
        }

    }

    //obtiene el array de personas de la api
    private void obtenerDatos() {
        PersonService service = retrofit.create(PersonService.class);
        Call<ArrayList<person>> pokemonRespuestaCall = service.obtenerListaPersonas();

        pokemonRespuestaCall.enqueue(new Callback<ArrayList<person>>() {
            @Override
            public void onResponse(Call<ArrayList<person>> call, Response<ArrayList<person>> response) {
                if (response.isSuccessful()) {
                    //aca asigna lo cojido al array
                    ArrayList<person> personas = response.body();
                    attemptLogin(personas);
                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<person>> call, Throwable t) {
                Log.e(TAG, " onFailure: " + t.getMessage());
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.connection_error), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //coje el array de personas y compara con lo que se ingreso en los campos del login
    private void attemptLogin(ArrayList<person> personas){
        user user1=null;
        Log.i(TAG, " ingresado: " + editTextUser.getText());
        for(int i=0; i<personas.size(); i++){
            Log.i(TAG, " username/login: " + personas.get(i).getUser().getUsername());
            if(personas.get(i).getUser().getUsername().equals(editTextUser.getText().toString())) {
                Log.i(TAG, "usuario correcto");
                estado_user=true;
                user1 = personas.get(i).getUser();
                break;
            }
        }
        if(user1!=null){
            Log.i(TAG, "NO ES NULO");
            if(editTextPass.getText().toString().equals(user1.getPassword())&&estado_user){
                Log.i(TAG, "contraseña correcta");
                goMainScreen();
                //startActivity(new Intent(MainActivity.this, MenuPrincipalActivity.class));
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.login_succesful), Toast.LENGTH_SHORT).show();
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

    //creo q es pa ver si hay interweb
    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

    private void goMainScreen(){
        Intent intent= new Intent(this, MenuPrincipalActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
























   /* private void loadJSON() {
        // Crear adaptador Retrofit
        mRestAdapter = new Retrofit.Builder()
                .baseUrl(PersonService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // Crear conexión a la API de SaludMock
        personService = mRestAdapter.create(PersonService.class);

        editTextUser=(EditText)findViewById(R.id.edtxtUser);
        editTextPass=(EditText)findViewById(R.id.edtxtPass);
        btnSimpleTabs=(Button) findViewById(R.id.btnIniciarSesion);

        editTextPass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {

                    if (!isOnline()) {
                        return false;
                    }
                    attemptLogin();
                    return true;
            }
        });
        btnSimpleTabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isOnline()) {
                    return;
                }
                attemptLogin();

            }
        });
    }
    private void attemptLogin() {


        String userId = editTextUser.getText().toString();
        String password = editTextPass.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {

            Call<person> loginCall = personService.login(new user(userId, password));
            loginCall.enqueue(new Callback<person>() {
                @Override
                public void onResponse(Call<person> call, Response<person> response) {

                    try {
                        // Reportar causas de error no relacionado con la API
                        Log.d("LoginActivity", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                        return;
                    }

                @Override
                public void onFailure(Call<person> call, Throwable t) {
                    showLoginError(t.getMessage());
                }
            });
        }
    }

    private boolean isUserIdValid(String userId) {
        return userId.length() == 10;
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private void showLoginError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    private boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();
    }

}*/

