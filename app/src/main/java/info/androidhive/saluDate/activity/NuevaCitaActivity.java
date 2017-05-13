package info.androidhive.saluDate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import info.androidhive.materialtabs.R;

/**
 * Created by gustavo on 03/05/17.
 */

public class NuevaCitaActivity extends AppCompatActivity{
    private Toolbar toolbar;
    public TextView programar_cita;

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
}
