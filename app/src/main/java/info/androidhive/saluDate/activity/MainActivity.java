package info.androidhive.saluDate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import info.androidhive.materialtabs.R;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText editTextUser;
    private TextView prueba;
    private EditText editTextPass;
    private String users[];
    private Button btnSimpleTabs;


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
        prueba = (TextView) findViewById(R.id.textView5);
        prueba.setMovementMethod(new ScrollingMovementMethod());

        btnSimpleTabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(MainActivity.this, MenuPrincipalActivity.class));
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.login_succesful), Toast.LENGTH_SHORT).show();

            }
        });
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

