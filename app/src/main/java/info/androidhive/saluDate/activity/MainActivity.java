package info.androidhive.saluDate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.Bind;
import info.androidhive.materialtabs.R;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    @NotEmpty(message = "Debe ingresar datos")
    //@Bind(R.id.edtxtUser)
    EditText editTextUser;
    //@ConfirmPassword
    //@Bind(R.id.edtxtPass)
    private EditText editTextPass;
    //@Bind(R.id.btnIniciarSesion)
    private Button btnSimpleTabs;
    Validator validator;
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
        btnSimpleTabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
      //          validator.validate();
                startActivity(new Intent(MainActivity.this, MenuPrincipalActivity.class));
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.login_succesful), Toast.LENGTH_SHORT).show();
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
