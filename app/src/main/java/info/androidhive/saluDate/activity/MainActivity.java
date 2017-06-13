package info.androidhive.saluDate.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import info.androidhive.materialtabs.R;
import info.androidhive.saluDate.ConexionService.Person;
import info.androidhive.saluDate.ConexionService.PersonService;
import info.androidhive.saluDate.ConexionService.User;
import retrofit.RestAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editTextUser;
    private TextView prueba;
    private EditText editTextPass;
    public static  final  String BASE_URL="http://34.209.167.194:8080/";
    private String users[];
    private Button btnSimpleTabs;
    private PersonService mpersonService;
    //Prueba
    private Retrofit mRestAdapter;
    private PersonService personService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
        }catch (Exception e){
            e.printStackTrace();
        }

        btnSimpleTabs=(Button) findViewById(R.id.btnIniciarSesion);
        editTextUser=(EditText)findViewById(R.id.edtxtUser);
        editTextPass=(EditText)findViewById(R.id.edtxtPass);
        prueba=(TextView)findViewById(R.id.textView5);
        prueba.setMovementMethod(new ScrollingMovementMethod());





        btnSimpleTabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadJSON();
                /*editTextUser=((EditText)findViewById(R.id.edtxtUser)).getText().toString();
                editTextPass=((EditText)findViewById(R.id.edtxtPass)).getText().toString();
                if (editTextUser.equals("eduardo")&& editTextPass.equals("123456")){
                    startActivity(new Intent(MainActivity.this, MenuPrincipalActivity.class));
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.login_succesful), Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.datos_incorrectos), Toast.LENGTH_SHORT).show();
                }*/

            }
        });
    }

    private void loadJSON() {
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

            Call<Person> loginCall = personService.login(new User(userId, password));
            loginCall.enqueue(new Callback<Person>() {
                @Override
                public void onResponse(Call<Person> call, Response<Person> response) {

                    try {
                        // Reportar causas de error no relacionado con la API
                        Log.d("LoginActivity", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                        return;
                    }

                @Override
                public void onFailure(Call<Person> call, Throwable t) {
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

}

