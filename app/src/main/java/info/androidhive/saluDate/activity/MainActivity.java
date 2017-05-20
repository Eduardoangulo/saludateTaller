package info.androidhive.saluDate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import info.androidhive.materialtabs.R;
import info.androidhive.saluDate.ConexionService.POJOperson;
import info.androidhive.saluDate.ConexionService.PersonService;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    @NotEmpty(message = "Debe ingresar datos")
    //@Bind(R.id.edtxtUser)
    private String editTextUser;
    //private EditText editTextUser2;
    //@ConfirmPassword
    //@Bind(R.id.edtxtPass)
    private String editTextPass;
    //@Bind(R.id.btnIniciarSesion)
    private Button btnSimpleTabs;
    //public static  final  String BASE_URL="http://jairbarzola.esy.es";
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
        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .build();
        PersonService service= retrofit.create(PersonService.class);
        service.getUser(new retrofit2.Callback<List<POJOperson>>() {
            @Override
            public void onResponse(Call<List<POJOperson>> call, Response<List<POJOperson>> response) {
                editTextUser2=(EditText)findViewById(R.id.edtxtUser);
                editTextUser2.setText(call.toString());
            }

            @Override
            public void onFailure(Call<List<POJOperson>> call, Throwable throwable) {

            }
        });*/
        btnSimpleTabs=(Button) findViewById(R.id.btnIniciarSesion);
        btnSimpleTabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
      //          validator.validate();
                editTextUser=((EditText)findViewById(R.id.edtxtUser)).getText().toString();
                editTextPass=((EditText)findViewById(R.id.edtxtPass)).getText().toString();
                if (editTextUser.equals("eduardo")&& editTextPass.equals("123456")){
                    startActivity(new Intent(MainActivity.this, MenuPrincipalActivity.class));
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.login_succesful), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.datos_incorrectos), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

/*
    @Override
    public void onValidationSucceeded() {


    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }*/
}
